package org.auditioner.services.auth;

import org.auditioner.services.util.ServiceContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auditioner/authentication")
@Produces( MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CognitioAuthenticationResource {
    private ServiceContext serviceContext;
    private CognitoAuthenticationService cognitoAuthenticationService;

    public CognitioAuthenticationResource( ServiceContext serviceContext, CognitoAuthenticationService cognitoAuthenticationService) {
        this.serviceContext = serviceContext;
        this.cognitoAuthenticationService = cognitoAuthenticationService;
    }

    @POST
    public AdminInitiateAuthResult intiateAuth(AuthenticationUser authenticationUser) {
        return cognitoAuthenticationService.adminInitiateAuth(authenticationUser.getUserName(), authenticationUser.getPassword());
    }
}
