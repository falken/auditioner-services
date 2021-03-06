package org.auditioner.services.production.member;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.family.member.FamilyMemberDAO;
import org.auditioner.services.production.AuditionNumberGenerator;
import org.auditioner.services.production.Production;
import org.auditioner.services.production.ProductionDAO;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ProductionMemberResourceTest extends TestResourceBase {

    private static final FamilyMemberDAO familyMemberDao = mock(FamilyMemberDAO.class);
    private static final ProductionMemberDAO productionMemberDAO = mock(ProductionMemberDAO.class);
    private static final ProductionDAO productionDAO = mock(ProductionDAO.class);
    private static final AuditionNumberGenerator auditionNumberGenerator = mock(AuditionNumberGenerator.class);

    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new ProductionMemberResource(serviceContext, productionMemberDAO, auditionNumberGenerator, familyMemberDao, productionDAO));

    private String hostNameRoot;
    private ProductionMember jane;
    private FamilyMember janesDetails;
    private static final long PRODUCTION_ID = 9999L;

    @Before
    public void setUp() {
        super.setUp(resources);

        reset(productionMemberDAO);
        reset(productionDAO);
        reset(auditionNumberGenerator);

        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);

        Production nutcracker = new Production();
        nutcracker.setName("Nutcracker");
        nutcracker.setAgeCutoffDate("2016-10-1");

        when(productionDAO.getProduction(PRODUCTION_ID)).thenReturn(nutcracker);

        jane = new ProductionMember();
        jane.setFamilyMemberFirstName("Jane");
        jane.setFamilyMemberLastName("Doe");
        jane.setAuditionNumber("2");
        jane.setRehearsalConflicts("something");
        jane.setLocation("/auditioner/productions/9999/production-members/1");

        janesDetails = new FamilyMember();
        janesDetails.setBirthDate("2010-10-01");

    }

    @Test
    public void getProductionMemberWillReturnProductionMember() {
        when(productionMemberDAO.getProductionMember(PRODUCTION_ID, 1337L)).thenReturn(jane);

        Response response = simpleGet("/auditioner/productions/9999/production-members/1337");

        assertEquals(asJsonString(jane), getResponseBody(response));
    }

    @Test
    public void getProductionMembersWillReturnProductionMembersList() {
        ProductionMember john = new ProductionMember();
        john.setFamilyMemberFirstName("John");
        john.setFamilyMemberLastName("Smith");
        john.setAuditionNumber("3");
        john.setRehearsalConflicts("something other thing");
        john.setLocation("/auditioner/productions/9999/production-members/2");
        List<ProductionMember> productionMemberList = Arrays.asList(jane, john);
        when(productionMemberDAO.getProductionMembers(PRODUCTION_ID)).thenReturn(productionMemberList);

        Response response = simpleGet("/auditioner/productions/9999/production-members");

        assertEquals(asJsonString(productionMemberList), getResponseBody(response));
    }

    @Test
    public void deleteProductionMemberRemovesProductionMember() {
        Response response = simpleDelete("/auditioner/productions/9999/production-members/12");

        assertEquals(204, response.getStatus());

        verify(productionMemberDAO).deleteProductionMember(12L);
    }

    @Test
    public void updateProductionMemberChangesProductionMember() {
        Response response = simplePut("/auditioner/productions/9999/production-members/12", jane);

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
        when(productionMemberDAO.addProductionMember(eq(PRODUCTION_ID),any(ProductionMember.class))).thenReturn(14134L);
        when(familyMemberDao.getFamilyMember(any(Long.class))).thenReturn(janesDetails);


        Response response = simplePost("/auditioner/productions/" + PRODUCTION_ID + "/production-members/", jane);

        assertEquals(hostNameRoot + "/auditioner/productions/" + PRODUCTION_ID + "/production-members/" + 14134, response.getHeaderString("Location"));
        assertEquals(201, response.getStatus());
    }

    @Test
    public void addingProductionMemberRetrievesAgeFromFamilyMember() {
        jane.setFamilyMemberId(100L);

        simplePost("/auditioner/productions/9999/production-members/", jane);

        verify(familyMemberDao).getFamilyMember(100L);
    }

    @Test
    public void addingProductionMemberPopulatesAuditionNumberFromFamilyMemberDetails() {

        final long familyMemberId = 1L;

        jane.setFamilyMemberId(familyMemberId);


        when(familyMemberDao.getFamilyMember(familyMemberId)).thenReturn(janesDetails);
        when(auditionNumberGenerator.generate(PRODUCTION_ID,6)).thenReturn("600");

        simplePost("/auditioner/productions/" + PRODUCTION_ID + "/production-members/", jane);

        ArgumentCaptor<ProductionMember> argument = ArgumentCaptor.forClass(ProductionMember.class);
        verify(productionMemberDAO).addProductionMember(eq(PRODUCTION_ID),argument.capture());
        assertThat(argument.getValue().getAuditionNumber(),equalTo("600"));
    }

}