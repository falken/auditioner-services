package org.auditioner.services.family.member;

import org.auditioner.services.family.Family;
import org.auditioner.services.family.FamilyDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/auditioner/families/{family_id}/family_member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamilyMemberResource {

    FamilyMemberDAO familyMemberDAO;
    FamilyDAO familyDAO;

    public FamilyMemberResource(FamilyDAO familyDAO,FamilyMemberDAO familyMemberDAO){
        this.familyMemberDAO = familyMemberDAO;
        this.familyDAO = familyDAO;
    }

    @GET
    @Path("/{id}")
    public FamilyMember getFamilyMember(@PathParam("family_id") long familyId,@PathParam("id")long id){
        Family family = familyDAO.getFamily(familyId);
        if(family!=null){
            return familyMemberDAO.getFamilyMemberByFamilyId(id);
        }
        return null;
    }

//    @GET
//    @Path("/family_member")
//    public List<FamilyMember> getFamilyMembers(@PathParam("family_id") long familyId, )
    //get list
    //delete member
    //put member (update)
    //post member (create)
}
