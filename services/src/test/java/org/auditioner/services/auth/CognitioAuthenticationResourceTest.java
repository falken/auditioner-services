package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CognitioAuthenticationResourceTest extends TestResourceBase {
    private static final CognitoAuthenticationService cognitoAuthenticationService = mock(CognitoAuthenticationService.class);
    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());
    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new CognitioAuthenticationResource(serviceContext, cognitoAuthenticationService));
    private String hostNameRoot;
    private String location;

    @Before
    public void setUp(){
        super.setUp(resources);
        hostNameRoot = "http://lollypops.com";
        location = hostNameRoot + "/auditioner/authentication";
        reset(cognitoAuthenticationService);
    }

    @Test
    public void authResourceInitiateAuthCallCognitioAuthenticationService(){
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUserName("acgass");
        authenticationUser.setPassword("test");
        authenticationUser.setLocation(location);
        CognitoAdminInitiateAuthResult cognitoAdminInitiateAuthResult = new CognitoAdminInitiateAuthResult(new AdminInitiateAuthResult());
        cognitoAdminInitiateAuthResult.setChallengeName("blah");
        when(cognitoAuthenticationService.adminInitiateAuth(authenticationUser.getUserName(), authenticationUser.getPassword())).thenReturn(cognitoAdminInitiateAuthResult);

        Response actual = simplePost("/auditioner/authentication", authenticationUser);

        assertEquals(asJsonString(cognitoAdminInitiateAuthResult), getResponseBody(actual));
    }
}