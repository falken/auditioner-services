package org.auditioner.services.production.member;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.family.member.FamilyMemberDAO;
import org.auditioner.services.production.AuditionNumberGenerator;
import org.auditioner.services.util.ServiceContext;

import static org.auditioner.services.util.CreatedResponse.pathFromString;

@Path("/auditioner/productions/{production_id}/production-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionMemberResource {
    private ProductionMemberDAO productionMemberDAO;
    private ServiceContext serviceContext;
    private AuditionNumberGenerator auditionNumberGenerator;
    private FamilyMemberDAO familyMemberDao;

    public ProductionMemberResource(ServiceContext serviceContext, ProductionMemberDAO productionMemberDAO, AuditionNumberGenerator auditionNumberGenerator, FamilyMemberDAO familyMemberDao) {
        this.serviceContext = serviceContext;
        this.productionMemberDAO = productionMemberDAO;
        this.auditionNumberGenerator = auditionNumberGenerator;
        this.familyMemberDao = familyMemberDao;
    }

    @GET
    @Path("/{production_member_id}")
    public ProductionMember getProductionMember(@PathParam("production_id") long productionId, @PathParam("production_member_id") long productionMemberId) {
        return productionMemberDAO.getProductionMember(productionId, productionMemberId);
    }

    @GET
    public List<ProductionMember> getProductionMembers(@PathParam("production_id") long productionId) {
        return productionMemberDAO.getProductionMembers(productionId);
    }

    @DELETE
    @Path("/{production_member_id}")
    public void deleteProductionMember(@PathParam("production_member_id") long productionMemberId) {
        productionMemberDAO.deleteProductionMember(productionMemberId);
    }

    @PUT
    @Path("/{production_member_id}")
    public void updateProductionMember(@PathParam("production_member_id") long productionMemberId, ProductionMember productionMember) {
        productionMemberDAO.updateProductionMember(productionMemberId, productionMember);
    }

    @POST
    public Response addProductionMember(@PathParam("production_id") long productionId, ProductionMember productionMember) {
        String auditionNumber = generateAuditionNumberFrom(productionId, productionMember);

        productionMember.setAuditionNumber(auditionNumber);

        long productionMemberId = productionMemberDAO.addProductionMember(productionId,productionMember);

        String path = "/auditioner/productions/" + productionId + "/production-members/" + productionMemberId;
        return Response.status(Response.Status.CREATED)
                .entity(pathFromString(path))
                .header("Location", serviceContext.createUriFromPath(path))
                .build();
    }

    private String generateAuditionNumberFrom(@PathParam("production_id") long productionId, ProductionMember productionMember) {
        FamilyMember memberDetails = familyMemberDao.getFamilyMember(productionMember.getFamilyMemberId());
        return auditionNumberGenerator.generate(memberDetails.getAge(), productionId);
    }
}
