package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import org.auditioner.services.TestResourceBase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Ignore
public class CognitoAuthenticationServiceTest extends TestResourceBase {
    @Test
    public void testAdminInitiateAuth() {
        new CognitoAuthenticationService().adminInitiateAuth("tbrobston", "3VgCdW6#");
    }

    @Test
    public void testAdminCreateUser() {
        new CognitoAuthenticationService().adminCreateUser("tbrobston", "anthonybrobston@gmail.com");
    }

    @Test
    public void testAdminRespondToAuthChallenge() {
        String challengeName = "NEW_PASSWORD_REQUIRED";
        Map<String, String> challengeResponses = new HashMap<>();
        challengeResponses.put("USERNAME", "tbrobston");
        challengeResponses.put("NEW_PASSWORD", "Blipblip123!");
        String session = "Jos3U1I3kaQOwkA8PtsHYUm_TL1pLCAMV9NqZCsvmiKluRtu6j4kbfbt4gGHiLjWkwnfURqRpfIOdgzMkuFTk6Y-reKcp-5jMewCLFU8_zENZxZInZdCFmbb8jKwA8-hgqVbczZn_-hpKc8q_UIjrYjQpjk9deVBcBYgUwXSBbLOJSDhnz6dcQYPbpY60vJLrbbD-f8qdc0Ui46RhQ0fuLtf968SQGwTxYbuV47HPA_RCGjjcj7iM0lyntdys5OlTNNaaPguohyoQvhhgztuoyCvnNz4Ark0O0iJ92xy_hz6ZTqo2Za9bHIgyk2phgFRjeG3ol2EB0gaeWnk1ftlvsid96RmLJJBfQn0oYpisU7DV3EKeOAnzSM10N_kDixPAT8l1h1_6CZn4T3LqDIgaBt8Jtda9JS6rhhRnNB0k7SHumBDr2PVLCzsPD1jawDPHJEmFXsUG-oXp3VzAO-4jcpN5_y1-zUyH_Zt8uUx0HDh3zkxcUYNzqLYmt5173-kdPziujwpEvGH95qN-65KY3bE5tq1Wbtuxm7O50B1AtaB4aOG7hBpa5BcW6tWBQHRWjjL-se0ON3ohMZH5QhCrZk6OaRToIyfIcyChkiw6-aOhUHix4l1G3YOOWajFSkanzDoz50rO7jD_WUw9i_gIykOHhBUyFNmsUAG1qnkkJ3XaXyXh-Ix3wr4YvxFGhACDgYgUegj0Zg";
        new CognitoAuthenticationService().adminRespondToAuthChallenge(challengeName, challengeResponses, session);
    }

    @Test
    public void testForgotPassword() {
        new CognitoAuthenticationService().forgotPassword("tbrobston");
    }

    @Test
    public void testAdminDeleteUser() {
        new CognitoAuthenticationService().adminDeleteUser("tbrobston");
    }

    @Test
    public void testConfirmForgotPassword() {
        new CognitoAuthenticationService().confirmForgotPassword("tbrobston", "Superawesomepassword123!", "996105");
    }
}