package org.auditioner.services.family;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class FamilyResourceTest extends TestResourceBase {

    private static final FamilyDAO familyDAO = mock(FamilyDAO.class);
    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new FamilyResource(serviceContext,familyDAO));

    private String hostNameRoot;
    @Before
    public void setUp() {
        super.setUp(resources);

        reset(familyDAO);

        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);
    }

    @Test
    public void addFamilyCreatesFamily(){

        when(familyDAO.addFamily(any(Family.class))).thenReturn(14134L);

        Family family = new Family();
        family.setName("MyName");

        Response response = simplePost("/auditioner/families",family);


        assertEquals(hostNameRoot+"/auditioner/families/" + 14134, response.getHeaderString("Location"));
        assertEquals(201,response.getStatus());
    }

    @Test
    public void deleteFamilyRemovesFamily(){

        Response response = simpleDelete("/auditioner/families/12");

        assertEquals(204,response.getStatus());

        verify(familyDAO).deleteFamily(12L);
    }

    @Test
    public void getFamilyWillReturnFamily(){
        Family family = new Family();
        family.setName("theName");
        family.setLocation("/auditioner/families/12");

        when(familyDAO.getFamily(12L)).thenReturn(family);

        Family actualFamily = simpleGet("/auditioner/families/12",Family.class);

        assertEquals(actualFamily.getName(),family.getName());
        assertEquals(actualFamily.getLocation(),family.getLocation());
    }

    @Test
    public void getFamiliesWillReturnFamilyList(){
        Family family1 = new Family();
        family1.setName("one");
        family1.setLocation("/auditioner/families/1");
        Family family2 = new Family();
        family2.setName("two");
        family2.setLocation("/auditioner/families/2");

        List<Family> familyList = newArrayList(family1,family2);

        when(familyDAO.getFamilies()).thenReturn(familyList);

        Response response = simpleGet("/auditioner/families");

        assertEquals(asJsonString(familyList),getResponseBody(response));
    }

    @Test
    public void updateFamilyChangesFamily(){

        Family family = new Family();
        family.setName("theName");

        Response response = simplePut("/auditioner/families/12",family);

        assertEquals(HttpStatus.NO_CONTENT_204,response.getStatus());

        ArgumentCaptor<Family> argument = ArgumentCaptor.forClass(Family.class);

        verify(familyDAO).updateFamily(eq(12L), argument.capture());

        assertThat("theName",equalTo(argument.getValue().getName()));
    }
}