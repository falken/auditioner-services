package org.auditioner.services.family.member;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.FamilyDAO;
import org.auditioner.services.family.FamilyResource;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


public class FamilyMemberResourceTest extends TestResourceBase {

    private static final FamilyMemberDAO familyMemberDAO = mock(FamilyMemberDAO.class);;

    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new FamilyMemberResource(serviceContext, familyMemberDAO));
    private String hostNameRoot;

    @Before
    public void setUp(){
        super.setUp(resources);
        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);

        reset(familyMemberDAO);
    }

    @Test
    public void get() throws Exception {
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFirstName("First Name");
        familyMember.setLastName("Last Name");
        familyMember.setPastRoles("Snow Queen, Dew Drop Fairy");
        //when(familyMemberDAO.getFamilyMember(1337L)).thenReturn(familyMember);

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

    @Test
    public void addFamilyCreatesFamily(){
        when(familyMemberDAO.addFamilyMember(eq(9999L), any(FamilyMember.class))).thenReturn(14134L);
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFirstName("MyName");

        Response response = simplePost("/auditioner/families/9999/family_member/",familyMember);

        Assert.assertEquals(hostNameRoot + "/auditioner/families/9999/family_member/" + 14134, response.getHeaderString("Location"));
        Assert.assertEquals(201,response.getStatus());
    }

    @Test
    public void getFamiliesWillReturnFamilyList(){
        FamilyMember familyMember1 = new FamilyMember();
        familyMember1.setFirstName("one");
        familyMember1.setLocation("/auditioner/families/9999/");
        FamilyMember familyMember2 = new FamilyMember();
        familyMember2.setFirstName("two");
        familyMember2.setLocation("/auditioner/families/9999/");
        List<FamilyMember> familyMemberList = newArrayList(familyMember1,familyMember2);
        //when(familyMemberDAO.getFamilyMembers()).thenReturn(familyMemberList);

        Response response = simpleGet("/auditioner/families/9999/family_member");
        Assert.assertEquals(asJsonString(familyMemberList),getResponseBody(response));
    }
}