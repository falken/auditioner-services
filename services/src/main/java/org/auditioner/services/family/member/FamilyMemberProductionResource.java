package org.auditioner.services.family.member;

import org.auditioner.services.production.member.ProductionMember;
import org.auditioner.services.util.ServiceContext;

import org.auditioner.services.production.member.ProductionMemberDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.auditioner.services.util.CreatedResponse.pathFromString;

@Path("/auditioner/families/{family_id}/production_family_member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamilyMemberProductionResource {

    private final ServiceContext serviceContext;

    ProductionMemberDAO productionMemberDAO;

    public FamilyMemberProductionResource(ServiceContext context, ProductionMemberDAO productionMemberDAO) {
        this.serviceContext = context;
        this.productionMemberDAO = productionMemberDAO;

    }

//    @GET
//    @Path("/{id}")
//    public FamilyMember getFamilyMember(@PathParam("family_id") long familyId, @PathParam("id") long id) {
//        return familyMemberDAO.getFamilyMember(familyId,id);
//    }

    @GET
    @Path("/{family_memberid}")
    public ProductionMember getFamilyMember(@PathParam("family_memberid") long familyMemberId) {
        return productionMemberDAO.getMemberProductions(familyMemberId);
    }

//    @PUT
//    @Path("/{id}")
//    public void updateFamilyMember(@PathParam("family_id") long familyId,@PathParam("id") long id, FamilyMember familyMember) {
//
//        familyMemberDAO.updateFamilyMember(familyId, id, familyMember);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void deleteFamilyMember(@PathParam("family_id") long familyId,@PathParam("id") long familyMemberId) {
//        familyMemberDAO.deleteFamilyMember(familyId,familyMemberId);
//    }
//
//    @POST
//    public javax.ws.rs.core.Response addFamilyMember(@PathParam("family_id") long familyId, FamilyMember familyMember){
//        long familyMemberId = familyMemberDAO.addFamilyMember(familyId, familyMember);
//
//        String path = "/auditioner/families/"+ familyId + "/family_member/" + familyMemberId;
//        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.CREATED)
//                .entity(pathFromString(path))
//                .header("Location", serviceContext.createUriFromPath(path))
//                .build();
//    }
//
//    @GET
//    public List<FamilyMember> getFamilyMembers(@PathParam("family_id") long familyId){
//        return familyMemberDAO.getFamilyMembers(familyId);
//    }
//
//    @GET
//    public List<FamilyMember> getFamilyMembers(@PathParam("family_id") long familyId){
//        return familyMemberDAO.getFamilyMembers(familyId);
//    }
}
