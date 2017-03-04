package org.auditioner.services.production.member;

import org.auditioner.services.family.Family;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import org.auditioner.services.util.ServiceContext;

import static org.auditioner.services.util.CreatedResponse.pathFromString;

@Path("/auditioner/production-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionMemberResource {
    private ProductionMemberDAO productionMemberDAO;
    private ServiceContext serviceContext;

    public ProductionMemberResource(ServiceContext serviceContext, ProductionMemberDAO productionMemberDAO) {
        this.serviceContext = serviceContext;
        this.productionMemberDAO = productionMemberDAO;
    }

    @GET
    @Path("/{production_member_id}")
    public ProductionMember getProductionMember(@PathParam("production_member_id") long productionMemberId){
        return productionMemberDAO.getProductionMember(productionMemberId);
    }

    @GET
    public List<ProductionMember> getProductionMembers(){
        return productionMemberDAO.getProductionMembers();
    }

    @DELETE
    @Path("/{production_member_id}")
    public void deleteProductionMember(@PathParam("production_member_id") long productionMemberId)
    {
        productionMemberDAO.deleteProductionMember(productionMemberId);
    }

    @PUT
    @Path("/{production_member_id}")
    public void updateProductionMember(@PathParam("production_member_id") long productionMemberId, ProductionMember productionMember)
    {
        productionMemberDAO.updateProductionMember(productionMemberId, productionMember);
    }

    @POST
    public Response addProductionMember(ProductionMember productionMember)
    {
        long productionMemberId = productionMemberDAO.addProductionMember(productionMember);

        String path = "/auditioner/production-members" + productionMemberId;
        return Response.status(Response.Status.CREATED)
                .entity(pathFromString(path))
                .header("Location", serviceContext.createUriFromPath(path))
                .build();
    }
}
