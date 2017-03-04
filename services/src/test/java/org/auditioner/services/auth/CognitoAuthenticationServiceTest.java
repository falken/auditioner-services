package org.auditioner.services.auth;

import org.auditioner.services.TestResourceBase;
import org.junit.Test;

import static org.junit.Assert.*;

public class CognitoAuthenticationServiceTest extends TestResourceBase {
    @Test
    public void testAdminInitiateAuth() {
        new CognitoAuthenticationService().adminInitiateAuth("tbrobston", "h/ul79Wa");
    }
    @Test
    public void testAdminCreateUser() {
        new CognitoAuthenticationService().adminCreateUser("tbrobston", "anthonybrobston@gmail.com");
    }
}