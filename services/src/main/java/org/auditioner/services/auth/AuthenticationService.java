package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;

import java.util.Map;

public interface AuthenticationService {
    AdminInitiateAuthResult adminInitiateAuth(String userName, String password);
    AdminCreateUserResult adminCreateUser(String userName, String email);
    AdminRespondToAuthChallengeResult adminRespondToAuthChallenge(String challengeName, Map<String, String> challengeResponses, String session);
}
