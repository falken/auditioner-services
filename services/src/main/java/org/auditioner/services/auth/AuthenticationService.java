package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.*;

import java.util.Map;

public interface AuthenticationService {
    AdminInitiateAuthResult adminInitiateAuth(String userName, String password);
    AdminCreateUserResult adminCreateUser(String userName, String email);
    AdminRespondToAuthChallengeResult adminRespondToAuthChallenge(String challengeName, Map<String, String> challengeResponses, String session);
    ForgotPasswordResult forgotPassword(String userName);
    AdminDeleteUserResult adminDeleteUser(String userName);
    ConfirmForgotPasswordResult confirmForgotPassword(String userName, String password, String confirmationCode);
}
