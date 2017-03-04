package org.auditioner.services.auth;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CognitoAuthenticationService implements AuthenticationService {

    @Override
    public AdminInitiateAuthResult adminInitiateAuth(String userName, String password) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
        AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest();
        adminInitiateAuthRequest.setClientId("5arcj0lrinm657auifjd0r94og");
        adminInitiateAuthRequest.setUserPoolId("us-east-1_vmUUwBDEg");
        Map<String, String> authParameters = new HashMap<String, String>();
        authParameters.put("USERNAME", userName);
        authParameters.put("PASSWORD", password);
        adminInitiateAuthRequest.setAuthParameters(authParameters);
        adminInitiateAuthRequest.setAuthFlow("ADMIN_NO_SRP_AUTH");
        return awsCognitoIdentityProvider.adminInitiateAuth(adminInitiateAuthRequest);
    }

    @Override
    public AdminCreateUserResult adminCreateUser(String userName, String email) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
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
        adminCreateUserRequest.setUserPoolId("us-east-1_vmUUwBDEg");
        return awsCognitoIdentityProvider.adminCreateUser(adminCreateUserRequest);
    }

    @Override
    public AdminRespondToAuthChallengeResult adminRespondToAuthChallenge(String challengeName, Map<String, String> challengeResponses, String session) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
        AdminRespondToAuthChallengeRequest adminRespondToAuthChallengeRequest = new AdminRespondToAuthChallengeRequest();
        adminRespondToAuthChallengeRequest.setChallengeName(challengeName);
        adminRespondToAuthChallengeRequest.setChallengeResponses(challengeResponses);
        adminRespondToAuthChallengeRequest.setClientId("5arcj0lrinm657auifjd0r94og");
        adminRespondToAuthChallengeRequest.setSession(session);
        adminRespondToAuthChallengeRequest.setUserPoolId("us-east-1_vmUUwBDEg");
        return awsCognitoIdentityProvider.adminRespondToAuthChallenge(adminRespondToAuthChallengeRequest);
    }

    @Override
    public ForgotPasswordResult forgotPassword(String userName) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setUsername(userName);
        forgotPasswordRequest.setClientId("5arcj0lrinm657auifjd0r94og");
        return awsCognitoIdentityProvider.forgotPassword(forgotPasswordRequest);
    }

    @Override
    public AdminDeleteUserResult adminDeleteUser(String userName) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest();
        adminDeleteUserRequest.setUsername(userName);
        adminDeleteUserRequest.setUserPoolId("us-east-1_vmUUwBDEg");
        return awsCognitoIdentityProvider.adminDeleteUser(adminDeleteUserRequest);
    }

    @Override
    public ConfirmForgotPasswordResult confirmForgotPassword(String userName, String password, String confirmationCode) {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("auditioner-services");
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion("us-east-1")
                .build();
        ConfirmForgotPasswordRequest confirmForgotPasswordRequest = new ConfirmForgotPasswordRequest();
        confirmForgotPasswordRequest.setUsername(userName);
        confirmForgotPasswordRequest.setPassword(password);
        confirmForgotPasswordRequest.setConfirmationCode(confirmationCode);
        confirmForgotPasswordRequest.setClientId("5arcj0lrinm657auifjd0r94og");
        return awsCognitoIdentityProvider.confirmForgotPassword(confirmForgotPasswordRequest);
    }
}
