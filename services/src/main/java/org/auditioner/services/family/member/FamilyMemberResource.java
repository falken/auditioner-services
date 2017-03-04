package org.auditioner.services.family.member;

import org.auditioner.services.family.Family;
import org.auditioner.services.family.FamilyDAO;
import org.auditioner.services.util.ServiceContext;
import org.eclipse.jetty.server.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auditioner/families/{family_id}/family_member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamilyMemberResource {

    private final ServiceContext serviceContext;

    FamilyMemberDAO familyMemberDAO;
    FamilyDAO familyDAO;

    public FamilyMemberResource(ServiceContext context, FamilyDAO familyDAO, FamilyMemberDAO familyMemberDAO) {
        this.serviceContext = context;
        this.familyMemberDAO = familyMemberDAO;
        this.familyDAO = familyDAO;
    }

    @GET
    @Path("/{id}")
    public FamilyMember getFamilyMember(@PathParam("family_id") long familyId, @PathParam("id") long id) {
        return familyMemberDAO.getFamilyMember(id);
    }

    @PUT
    @Path("/{id}")
    public void updateFamilyMember(@PathParam("id") long id, FamilyMember familyMember) {

        familyMemberDAO.updateFamilyMember(id, familyMember);
    }

    @DELETE
    @Path("/{id}")
    public void deleteFamilyMember(@PathParam("id") long familyMemberId) {
        familyMemberDAO.deleteFamilyMember(familyMemberId);
    }
}
