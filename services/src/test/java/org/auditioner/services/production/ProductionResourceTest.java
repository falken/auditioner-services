package org.auditioner.services.production;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.production.Production;
import org.auditioner.services.production.ProductionDAO;
import org.auditioner.services.production.ProductionResource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductionResourceTest extends org.auditioner.services.family.TestResourceBase {

    private static final ProductionDAO productionDAO = mock(ProductionDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new ProductionResource(productionDAO));

    @Before
    public void setUp() {
        super.setUp(resources);
    }

    @Test
    public void getProductionWillReturnProduction(){
        Production production = new Production();
        production.setName("Jane Dane");
        production.setAuditionDate(new Date());
        production.setSeason("Spring 2017");
        production.setLocation("/auditioner/productions/1337");
        when(productionDAO.getProduction(1337L)).thenReturn(production);

        Production actualProduction = simpleGet("/auditioner/productions/1337", Production.class);

        assertEquals(actualProduction.getName(), production.getName());
        assertEquals(actualProduction.getAuditionDate(), production.getAuditionDate());
        assertEquals(actualProduction.getSeason(), production.getSeason());
        assertEquals(actualProduction.getLocation(), production.getLocation());
    }
}