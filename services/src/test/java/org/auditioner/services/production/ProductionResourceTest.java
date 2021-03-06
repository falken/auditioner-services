package org.auditioner.services.production;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.production.Production;
import org.auditioner.services.production.ProductionDAO;
import org.auditioner.services.production.ProductionResource;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;

import java.util.Date;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductionResourceTest extends TestResourceBase {

    private static final ProductionDAO productionDAO = mock(ProductionDAO.class);
    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new ProductionResource(serviceContext,productionDAO));

    private String hostNameRoot;
    @Before
    public void setUp() {

        super.setUp(resources);
        hostNameRoot = "http://lollypops.com";
        serviceContext.getServiceConfiguration().setHostNameRoot(hostNameRoot);
    }

    @Test
    public void getProductionWillReturnProduction(){
        Production production = new Production();
        production.setName("Jane Dane");
        production.setAuditionDate("2010-01-01");
        production.setSeason("Spring 2017");
        production.setLocation("/auditioner/productions/1337");
        when(productionDAO.getProduction(1337L)).thenReturn(production);

        Production actualProduction = simpleGet("/auditioner/productions/1337", Production.class);

        assertEquals(actualProduction.getName(), production.getName());
        assertEquals(actualProduction.getAuditionDate(), production.getAuditionDate());
        assertEquals(actualProduction.getSeason(), production.getSeason());
        assertEquals(actualProduction.getLocation(), production.getLocation());
    }

    @Test
    public void getProductionsWillReturnFamilyList(){
        Production production1 = new Production();
        production1.setName("one");
        production1.setLocation("/auditioner/productions/1");
        Production production2 = new Production();
        production2.setName("two");
        production2.setLocation("/auditioner/productions/2");
        List<Production> productionList = newArrayList(production1,production2);
        when(productionDAO.getProductions()).thenReturn(productionList);

        Response response = simpleGet("/auditioner/productions");

        assertEquals(asJsonString(productionList),getResponseBody(response));
    }

    @Test
    public void deleteProductionRemovesProduction(){
        Response response = simpleDelete("/auditioner/productions/12");

        assertEquals(204,response.getStatus());

        verify(productionDAO).deleteProduction(12L);
    }

    @Test
    public void updateProductionChangesProduction(){
        Production production = new Production();
        production.setName("theName");

        Response response = simplePut("/auditioner/productions/12",production);

        assertEquals(HttpStatus.NO_CONTENT_204,response.getStatus());
        ArgumentCaptor<Production> argument = ArgumentCaptor.forClass(Production.class);
        verify(productionDAO).updateProduction(eq(12L), argument.capture());
        assertThat("theName",equalTo(argument.getValue().getName()));
    }

    @Test
    public void addProductionCreatesProduction(){
        Production production = new Production();
        production.setName("MyName");
        when(productionDAO.addProduction(any(Production.class))).thenReturn(14134L);

        Response response = simplePost("/auditioner/productions",production);

        assertEquals(hostNameRoot+"/auditioner/productions/" + 14134, response.getHeaderString("Location"));
        assertEquals(201,response.getStatus());
    }
}