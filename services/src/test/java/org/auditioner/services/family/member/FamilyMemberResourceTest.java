package org.auditioner.services.family.member;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.FamilyDAO;
import org.auditioner.services.family.FamilyResource;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


public class FamilyMemberResourceTest extends TestResourceBase {

    private static final FamilyMemberDAO familyMemberDAO = mock(FamilyMemberDAO.class);;

    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new FamilyMemberResource(serviceContext, mock(FamilyDAO.class), familyMemberDAO));

    @Before
    public void setUp(){
        super.setUp(resources);
        reset(familyMemberDAO);
    }

    @Test
    public void get() throws Exception {
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFirstName("First Name");
        familyMember.setLastName("Last Name");
        familyMember.setPastRoles("Snow Queen, Dew Drop Fairy");
        when(familyMemberDAO.getFamilyMember(1337L)).thenReturn(familyMember);

        Response response = simpleGet("/auditioner/families/9999/family_member/1337");

        assertEquals(asJsonString(familyMember),getResponseBody(response));
    }

    @Test
    public void put() throws Exception {
        FamilyMember familyMember = new FamilyMember();

        Response response = simplePut("/auditioner/families/9999/family_member/1337", familyMember);

        verify(familyMemberDAO).updateFamilyMember(eq(1337L), any(FamilyMember.class));
        assertEquals(HttpStatus.NO_CONTENT_204,response.getStatus());
    }

    @Test
    public void delete() throws Exception {
        Response response = simpleDelete("/auditioner/families/9999/family_member/1337");

        verify(familyMemberDAO).deleteFamilyMember(1337);
        assertEquals(204,response.getStatus());
    }
}