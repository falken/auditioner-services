package org.auditioner.services.production.member;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.production.Production;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductionMemberResourceTest extends TestResourceBase {

    private static final ProductionMemberDAO productionMemberDAO = mock(ProductionMemberDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new ProductionMemberResource(productionMemberDAO));

    @Before
    public void setUp() {
        super.setUp(resources);
    }

    @Test
    public void getProductionMemberWillReturnProductionMember(){
        ProductionMember productionMember = new ProductionMember();
        productionMember.setFamilyMemberFirstName("Frist Name");
        productionMember.setFamilyMemberLastName("Last Name");
        productionMember.setRequestedRoles("Snow Queen, Dew Drop Fairy");
        productionMember.setAuditionNumber("3");
        productionMember.setLocation("/auditioner/productions-member/1337");
        when(productionMemberDAO.getProductionMember(1337L)).thenReturn(productionMember);
        System.out.println(productionMember);
        Response response = simpleGet("/auditioner/productions-member/1337");
        assertEquals(asJsonString(productionMember),getResponseBody(response));
    }
}