package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import org.auditioner.services.util.ServiceContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auditioner/authentication")
@Produces( MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CognitioAuthenticationResource {

    private CognitoAuthenticationService cognitoAuthenticationService;
    private ServiceContext serviceContext;

    public CognitioAuthenticationResource( ServiceContext serviceContext, CognitoAuthenticationService cognitoAuthenticationService) {
        this.cognitoAuthenticationService = cognitoAuthenticationService;
        this.serviceContext = serviceContext;
    }

    @POST
    public void intiateAuth(AuthUser authUser) {

    }
}
