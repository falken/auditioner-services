package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.auditioner.services.TestResourceBase;
import org.auditioner.services.family.FamilyDAO;
import org.auditioner.services.family.member.FamilyMemberDAO;
import org.auditioner.services.family.member.FamilyMemberResource;
import org.auditioner.services.util.ServiceContext;
import org.auditioner.services.util.ServiceContextConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CognitioAuthenticationResourceTest extends TestResourceBase {
    private static final CognitoAuthenticationService cognito = mock (CognitoAuthenticationService.class);
    private CognitioAuthenticationResource resource;
    private AuthUser authUser;

    private static final ServiceContext serviceContext = new ServiceContext(new ServiceContextConfiguration());

    @ClassRule
    public static final ResourceTestRule resources = wrapResource(new CognitioAuthenticationResource(serviceContext, cognito ));

    private  String hostNameRoot;

    @Before
    public void setUp(){
        super.setUp(resources);
        authUser = new AuthUser("acgass", "test");
        hostNameRoot = "http://lollypops.com";
        authUser.setLocation(hostNameRoot + "/auditioner/authentication");
    }

    @Test
    public void authResourceInitiateAuthCallCognitioAuthenticationService(){
        AdminInitiateAuthResult result = new AdminInitiateAuthResult();

        when(cognito.adminInitiateAuth(anyString(), anyString())).thenReturn(result);
        assertEquals(asJsonString(result), simplePost("/auditioner/authentication", authUser));

    }

}