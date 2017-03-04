package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

public interface AuthenticationService {
    AdminInitiateAuthResult adminInitiateAuth(String userName, String password);
    AdminCreateUserResult adminCreateUser(String userName, String email);
}
