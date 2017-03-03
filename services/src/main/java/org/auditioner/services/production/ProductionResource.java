package org.auditioner.services.production;


import org.auditioner.services.family.Family;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/auditioner/productions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionResource {

    private ProductionDAO productionDAO;

    public ProductionResource(ProductionDAO productionDAO) {
        this.productionDAO = productionDAO;
    }

    @GET
    @Path("/{production_id}")
    public Production getProduction(@PathParam("production_id") long productionId){
        return productionDAO.getProduction(productionId);
    }

    @GET
    public List<Production> getProductions(){
        return productionDAO.getProductions();
    }

    @POST
    public Response addProduction(Production production)
    {
        long productionId = productionDAO.addProduction(production);

        return Response.created(URI.create("/auditioner/productions/" + productionId)).build();
    }

    @PUT
    @Path("/{production_id}")
    public void updateProduction(@PathParam("production_id") long productionId, Production production)
    {
        productionDAO.updateProduction(productionId, production);
    }

    @DELETE
    @Path("/{production_id}")
    public void deleteProduction(@PathParam("production_id") long productionId)
    {
        productionDAO.deleteProduction(productionId);
    }
}