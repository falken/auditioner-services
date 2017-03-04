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
    public static final ResourceTestRule resources = wrapResource(new FamilyResource(serviceContext, familyDAO));
    public static final String FAMILIES_URL = "/auditioner/families";

    private String hostNameRoot;

    @Before
    public void setUp() {
        super.setUp(resources);

        reset(familyDAO);

        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);
    }

    @Test
    public void addFamilyCreatesFamily() {
        long famiyId = 14134L;
        when(familyDAO.addFamily(any(Family.class))).thenReturn(famiyId);
        Family family = familyNamed("MyName");

        Response response = simplePost(FAMILIES_URL, family);

        assertEquals(hostNameRoot + FAMILIES_URL +
                "/" + famiyId, response.getHeaderString("Location"));
        assertEquals(201, response.getStatus());
    }

    @Test
    public void deleteFamilyRemovesFamily() {
        Response response = simpleDelete(urlFor(12L));

        assertEquals(204, response.getStatus());

        verify(familyDAO).deleteFamily(12L);
    }

    @Test
    public void getFamilyWillReturnFamily() {
        Family family = familyNamed("theName");
        family.setLocation(urlFor(12L));
        when(familyDAO.getFamily(12L)).thenReturn(family);

        Family actualFamily = simpleGet(urlFor(12L), Family.class);

        assertEquals(actualFamily.getName(), family.getName());
        assertEquals(actualFamily.getLocation(), family.getLocation());
    }

    @Test
    public void getFamiliesWillReturnFamilyList() {
        Family nelsons = familyNamed("The Nelson's");
        Family trump = familyNamed("Trump Family");

        List<Family> familyList = newArrayList(nelsons, trump);
        when(familyDAO.getFamilies()).thenReturn(familyList);

        Response response = simpleGet(FAMILIES_URL);

        assertEquals(asJsonString(familyList), getResponseBody(response));
    }

    @Test
    public void updateFamilyChangesFamily() {

        Response response = simplePut(urlFor(12L), familyNamed("theName"));

        assertEquals(HttpStatus.NO_CONTENT_204, response.getStatus());
        ArgumentCaptor<Family> argument = ArgumentCaptor.forClass(Family.class);
        verify(familyDAO).updateFamily(eq(12L), argument.capture());
        assertThat("theName", equalTo(argument.getValue().getName()));
    }

    private Family familyNamed(String name) {
        Family family1 = new Family();
        family1.setName(name);
        return family1;
    }

    private String urlFor(long id) {
        return FAMILIES_URL + "/" + id;
    }
}