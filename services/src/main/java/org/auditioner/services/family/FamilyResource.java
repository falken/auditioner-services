package org.auditioner.services.family;

import org.auditioner.services.util.ServiceContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.auditioner.services.util.CreatedResponse.pathFromString;

@Path("/auditioner/families")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamilyResource {


    private ServiceContext serviceContext;
    private FamilyDAO familyDAO;

    public FamilyResource(ServiceContext serviceContext, FamilyDAO familyDAO) {
        this.serviceContext = serviceContext;
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

        String path = "/auditioner/families/" + familyId;
        return Response.status(Response.Status.CREATED)
                .entity(pathFromString(path))
                .header("Location", serviceContext.createUriFromPath(path))
                .build();
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
