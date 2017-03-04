package org.auditioner.services.production.member;

import org.auditioner.services.family.Family;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/auditioner/production-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionMemberResource {
    private ProductionMemberDAO productionMemberDAO;

    public ProductionMemberResource(ProductionMemberDAO productionMemberDAO) {
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
    public void deleteFamily(@PathParam("production_member_id") long productionMemberId)
    {
        productionMemberDAO.deleteProductionMember(productionMemberId);
    }

    @PUT
    @Path("/{production_member_id}")
    public void updateFamily(@PathParam("production_member_id") long productionMemberId, ProductionMember productionMember)
    {
        productionMemberDAO.updateProductionMember(productionMemberId, productionMember);
    }
}
