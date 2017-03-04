package org.auditioner.services.production.member;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.production.Production;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class ProductionMemberResourceTest extends TestResourceBase {

    private static final ProductionMemberDAO productionMemberDAO = mock(ProductionMemberDAO.class);
    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new ProductionMemberResource(serviceContext, productionMemberDAO));

    private String hostNameRoot;

    @Before
    public void setUp() {
        super.setUp(resources);

        reset(productionMemberDAO);

        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);
    }

    @Test
    public void getProductionMemberWillReturnProductionMember() {
        ProductionMember productionMember = new ProductionMember();
        productionMember.setFamilyMemberFirstName("First Name");
        productionMember.setFamilyMemberLastName("Last Name");
        productionMember.setRequestedRoles("Snow Queen, Dew Drop Fairy");
        productionMember.setAuditionNumber("3");
        productionMember.setLocation("/auditioner/production-members/1337");
        when(productionMemberDAO.getProductionMember(1337L)).thenReturn(productionMember);

        Response response = simpleGet("/auditioner/production-members/1337");

        assertEquals(asJsonString(productionMember), getResponseBody(response));
    }

    @Test
    public void getProductionMembersWillReturnProductionMembersList() {
        ProductionMember productionMember1 = new ProductionMember();
        productionMember1.setFamilyMemberFirstName("Jane");
        productionMember1.setFamilyMemberLastName("Doe");
        productionMember1.setAuditionNumber("2");
        productionMember1.setRehearsalConflicts("something");
        productionMember1.setLocation("/auditioner/production-members/1");
        ProductionMember productionMember2 = new ProductionMember();
        productionMember1.setFamilyMemberFirstName("John");
        productionMember1.setFamilyMemberLastName("Smith");
        productionMember1.setAuditionNumber("3");
        productionMember1.setRehearsalConflicts("something other thing");
        productionMember2.setLocation("/auditioner/production-members/2");
        List<ProductionMember> productionMemberList = Arrays.asList(productionMember1, productionMember2);
        when(productionMemberDAO.getProductionMembers()).thenReturn(productionMemberList);

        Response response = simpleGet("/auditioner/production-members");

        assertEquals(asJsonString(productionMemberList), getResponseBody(response));
    }

    @Test
    public void deleteProductionMemberRemovesProductionMember() {
        Response response = simpleDelete("/auditioner/production-members/12");

        assertEquals(204, response.getStatus());

        verify(productionMemberDAO).deleteProductionMember(12L);
    }

    @Test
    public void updateProductionMemberChangesProductionMember() {
        ProductionMember productionMember = new ProductionMember();
        productionMember.setFamilyMemberFirstName("Jane");
        productionMember.setFamilyMemberLastName("Doe");
        productionMember.setAuditionNumber("2");
        productionMember.setRehearsalConflicts("something");
        productionMember.setLocation("/auditioner/production-members/12");

        Response response = simplePut("/auditioner/production-members/12", productionMember);

        assertEquals(HttpStatus.NO_CONTENT_204, response.getStatus());
        ArgumentCaptor<ProductionMember> argument = ArgumentCaptor.forClass(ProductionMember.class);
        verify(productionMemberDAO).updateProductionMember(eq(12L), argument.capture());
        assertThat("Jane", equalTo(argument.getValue().getFamilyMemberFirstName()));
        assertThat("Doe", equalTo(argument.getValue().getFamilyMemberLastName()));
        assertThat("2", equalTo(argument.getValue().getAuditionNumber()));
        assertThat("something", equalTo(argument.getValue().getRehearsalConflicts()));
    }

    @Test
    public void addProductionMemberCreatesProductionMember() {
        ProductionMember productionMember = new ProductionMember();
        when(productionMemberDAO.addProductionMember(any(ProductionMember.class))).thenReturn(14134L);

        Response response = simplePost("/auditioner/production-members", productionMember);

        assertEquals(hostNameRoot + "/auditioner/production-members" + 14134, response.getHeaderString("Location"));
        assertEquals(201, response.getStatus());
    }
}