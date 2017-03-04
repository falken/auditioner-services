package org.auditioner.services.auth;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
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
//        AttributeType emailVerifiedAttribute = new AttributeType();
//        emailAttribute.setName("email_verified");
//        emailAttribute.setValue("true");
//        userAttributes.add(emailVerifiedAttribute);
        adminCreateUserRequest.setUserAttributes(userAttributes);
        adminCreateUserRequest.setUserPoolId("us-east-1_vmUUwBDEg");
        return awsCognitoIdentityProvider.adminCreateUser(adminCreateUserRequest);
    }


}
