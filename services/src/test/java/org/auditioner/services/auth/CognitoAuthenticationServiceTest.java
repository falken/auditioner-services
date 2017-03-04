package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import org.auditioner.services.TestResourceBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CognitoAuthenticationServiceTest extends TestResourceBase {
    @Test
    public void testAdminInitiateAuth() {
        new CognitoAuthenticationService().adminInitiateAuth("tbrobston", "Blipblip123!");
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
        String session = "lPAtuLrvyJ0jaoc2AiC-z_JMwHOA0xfYXvM4QcCU6b2FCbQaajkJPbpKqHdnabqkvPZfpGeFcEGod0-u_VI896hXzpg46zmEF1Qu9DCuwX6Pwc-VePUgzFBbwJn0x-5mHRK38ICPgMqimeww0voMS-E4oa8_OZbwXE3PJ7sH5s8imcEBITQAcatmBE0epvtxXV0alinWBT6lMobXi8mtJKk6ZK5X8BL415Pg-ycWNxRmLwB3z8UiWIy2-OMIbjq5ROWFFJT7rKF-ZL0gsSxJmVPbVNqXtpjwW8fCrzIPfkAH-SiGBBBy3coHBJ8flx6OspAg4yszlWgUzlbNWGAP6jML4bivIC8pQwInAX0ZsH2VzwxQ1RXjiqpXcmeeV_ZYoO4kFuh-xhgti2TcWiGwRpvNfgPM27vpyCuwXRs7J2qwr8orn_9lJ82J_KvzLPA32djZ_Xts14m_BkvpQaSW2AnFPT7DtJ2CELpN8TYzPADgxVS-A7thQyVN3TJPrmvDjFpO7RB8nQUu6qci5-MieFpzizKaVjHqUG283Jkp_oXXfAZU41OFqNdb0ddg3ZvqFz0EEXQ8uCKa-ahArRwQ9Lqr2MEEzwaVwh4x7BmnHf83ox3m-Juj_xhrLygEeIKuM-PEuOulYJrg3FMWgTnUuys29AF3tEnwaIFSf5Ff5hzKTK2E34zMdvtVs9lTjEPSt4ttMSGjBONh0XML";
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
}