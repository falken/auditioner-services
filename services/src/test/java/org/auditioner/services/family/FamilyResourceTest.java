package org.auditioner.services.family;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class FamilyResourceTest extends TestResourceBase {

    private static final FamilyDAO familyDAO = mock(FamilyDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new FamilyResource(familyDAO));

    @Before
    public void setUp() {
        super.setUp(resources);

        reset(familyDAO);
    }

    @Test
    public void addFamilyCreatesFamily(){

        when(familyDAO.addFamily(any(Family.class))).thenReturn(14134L);

        Family family = new Family();
        family.setName("MyName");

        Response response = simplePost("/auditioner/family",family);

        assertEquals(201,response.getStatus());
        assertEquals("http://localhost:9998/auditioner/family/14134",response.getLocation().toString());
    }

    @Test
    public void deleteFamilyRemovesFamily(){

        Response response = simpleDelete("/auditioner/family/12");

        assertEquals(204,response.getStatus());

        verify(familyDAO).deleteFamily(12L);
    }

    @Test
    public void updateFamilyChangesFamily(){

        Family family = new Family();
        family.setName("theName");

        Response response = simplePut("/auditioner/family/12",family);

        assertEquals(HttpStatus.NO_CONTENT_204,response.getStatus());

        ArgumentCaptor<Family> argument = ArgumentCaptor.forClass(Family.class);

        verify(familyDAO).updateFamily(eq(12L), argument.capture());

        assertThat("theName",equalTo(argument.getValue().getName()));
    }
}