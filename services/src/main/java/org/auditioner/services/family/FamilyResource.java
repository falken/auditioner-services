package org.auditioner.services.family;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/auditioner/families")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamilyResource {


    private FamilyDAO familyDAO;

    public FamilyResource(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    @GET
    public List<Family> getFamilies(){
        return familyDAO.getFamilies();
    }

    @POST
    public Response addFamily(Family family)
    {
        long familyId = familyDAO.addFamily(family);

        return Response.created(URI.create("/auditioner/families/" + familyId)).build();
    }

    @GET
    @Path("/{family_id}")
    public Family getFamily(@PathParam("family_id") long familyId){
        return familyDAO.getFamily(familyId);
    }


    @PUT
    @Path("/{family_id}")
    public void updateFamily(@PathParam("family_id") long familyId, Family family)
    {
        familyDAO.updateFamily(familyId, family);
    }


    @DELETE
    @Path("/{family_id}")
    public void deleteFamily(@PathParam("family_id") long familyId)
    {
        familyDAO.deleteFamily(familyId);
    }
}
