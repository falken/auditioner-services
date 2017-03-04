package org.auditioner.services.production;


import org.auditioner.services.family.Family;
import org.auditioner.services.util.ServiceContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.auditioner.services.util.CreatedResponse.pathFromString;

@Path("/auditioner/productions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionResource {

    private ServiceContext serviceContext;
    private ProductionDAO productionDAO;

    public ProductionResource(ServiceContext serviceContext, ProductionDAO productionDAO) {
        this.serviceContext = serviceContext;
        this.productionDAO = productionDAO;
    }

    @GET
    @Path("/{production_id}")
    public Production getProduction(@PathParam("production_id") long productionId) {
        return productionDAO.getProduction(productionId);
    }

    @GET
    public List<Production> getProductions() {
        return productionDAO.getProductions();
    }

    @POST
    public Response addProduction(Production production) {
        long productionId = productionDAO.addProduction(production);

        String path = "/auditioner/productions/" + productionId;
        return Response.status(Response.Status.CREATED)
                .entity(pathFromString(path))
                .header("Location", serviceContext.createUriFromPath(path))
                .build();
    }

    @PUT
    @Path("/{production_id}")
    public void updateProduction(@PathParam("production_id") long productionId, Production production) {
        productionDAO.updateProduction(productionId, production);
    }

    @DELETE
    @Path("/{production_id}")
    public void deleteProduction(@PathParam("production_id") long productionId) {
        productionDAO.deleteProduction(productionId);
    }
}