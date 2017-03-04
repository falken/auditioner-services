package org.auditioner.services.util;

import javax.ws.rs.core.Response;

public class CreatedResponse {

    private String location;

    public CreatedResponse() {
        this.location = null;
    }

    public CreatedResponse(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public static CreatedResponse pathFromString(String pathString) {
        return new CreatedResponse(pathString);
    }

    public static Response createdResponse(ServiceContext serviceContext, String path) {
        return Response.status(Response.Status.CREATED)
                .entity(new CreatedResponse(path))
                .header("Location", serviceContext.createUriFromPath(path))
                .build();
    }
}
