package org.auditioner.services.auth;

import java.util.Map;

public interface AuthenticationService {
    CognitoAdminInitiateAuthResult adminInitiateAuth(String userName, String password);
//    AdminCreateUserResult adminCreateUser(String userName, String email);
//    AdminRespondToAuthChallengeResult adminRespondToAuthChallenge(String challengeName, Map<String, String> challengeResponses, String session);
//    ForgotPasswordResult forgotPassword(String userName);
//    AdminDeleteUserResult adminDeleteUser(String userName);
//    ConfirmForgotPasswordResult confirmForgotPassword(String userName, String password, String confirmationCode);
}
