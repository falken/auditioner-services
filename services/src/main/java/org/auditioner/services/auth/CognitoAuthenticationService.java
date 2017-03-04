package org.auditioner.services.auth;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CognitoAuthenticationService implements AuthenticationService {

    static final String CLIENT_ID = "5arcj0lrinm657auifjd0r94og";
    static final String USER_POOL_ID = "us-east-1_vmUUwBDEg";
    AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    public CognitoAuthenticationService() {
        this.awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider("auditioner-services"))
                .withRegion("us-east-1")
                .build();
    }

    @Override
    public CognitoAdminInitiateAuthResult adminInitiateAuth(String userName, String password) {
        AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest();
        adminInitiateAuthRequest.setClientId(CLIENT_ID);
        adminInitiateAuthRequest.setUserPoolId(USER_POOL_ID);
        Map<String, String> authParameters = new HashMap<String, String>();
        authParameters.put("USERNAME", userName);
        authParameters.put("PASSWORD", password);
        adminInitiateAuthRequest.setAuthParameters(authParameters);
        adminInitiateAuthRequest.setAuthFlow("ADMIN_NO_SRP_AUTH");
        AdminInitiateAuthResult adminInitiateAuthResult = awsCognitoIdentityProvider.adminInitiateAuth(adminInitiateAuthRequest);
        return new CognitoAdminInitiateAuthResult(adminInitiateAuthResult);
    }

//    @Override
    public AdminCreateUserResult adminCreateUser(String userName, String email) {
        AdminCreateUserRequest adminCreateUserRequest = new AdminCreateUserRequest();
        adminCreateUserRequest.setUsername(userName);
        Collection<AttributeType> userAttributes = new ArrayList<AttributeType>();
        AttributeType emailAttribute = new AttributeType();
        emailAttribute.setName("email");
        emailAttribute.setValue(email);
        userAttributes.add(emailAttribute);
        AttributeType emailVerifiedAttribute = new AttributeType();
        emailVerifiedAttribute.setName("email_verified");
        emailVerifiedAttribute.setValue("true");
        userAttributes.add(emailVerifiedAttribute);
        System.out.println(userAttributes);
        adminCreateUserRequest.setUserAttributes(userAttributes);
        adminCreateUserRequest.setUserPoolId(USER_POOL_ID);
        return awsCognitoIdentityProvider.adminCreateUser(adminCreateUserRequest);
    }

//    @Override
    public AdminRespondToAuthChallengeResult adminRespondToAuthChallenge(String challengeName, Map<String, String> challengeResponses, String session) {
        AdminRespondToAuthChallengeRequest adminRespondToAuthChallengeRequest = new AdminRespondToAuthChallengeRequest();
        adminRespondToAuthChallengeRequest.setChallengeName(challengeName);
        adminRespondToAuthChallengeRequest.setChallengeResponses(challengeResponses);
        adminRespondToAuthChallengeRequest.setSession(session);
        adminRespondToAuthChallengeRequest.setUserPoolId(USER_POOL_ID);
        adminRespondToAuthChallengeRequest.setClientId(CLIENT_ID);
        return awsCognitoIdentityProvider.adminRespondToAuthChallenge(adminRespondToAuthChallengeRequest);
    }

//    @Override
    public ForgotPasswordResult forgotPassword(String userName) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setUsername(userName);
        forgotPasswordRequest.setClientId(CLIENT_ID);
        return awsCognitoIdentityProvider.forgotPassword(forgotPasswordRequest);
    }

//    @Override
    public AdminDeleteUserResult adminDeleteUser(String userName) {
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest();
        adminDeleteUserRequest.setUsername(userName);
        adminDeleteUserRequest.setUserPoolId(USER_POOL_ID);
        return awsCognitoIdentityProvider.adminDeleteUser(adminDeleteUserRequest);
    }

//    @Override
    public ConfirmForgotPasswordResult confirmForgotPassword(String userName, String password, String confirmationCode) {
        ConfirmForgotPasswordRequest confirmForgotPasswordRequest = new ConfirmForgotPasswordRequest();
        confirmForgotPasswordRequest.setUsername(userName);
        confirmForgotPasswordRequest.setPassword(password);
        confirmForgotPasswordRequest.setConfirmationCode(confirmationCode);
        confirmForgotPasswordRequest.setClientId(CLIENT_ID);
        return awsCognitoIdentityProvider.confirmForgotPassword(confirmForgotPasswordRequest);
    }
}
