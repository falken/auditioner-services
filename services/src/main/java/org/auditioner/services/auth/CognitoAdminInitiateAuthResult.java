package org.auditioner.services.auth;

import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;

public class CognitoAdminInitiateAuthResult extends AdminInitiateAuthResult {

    public CognitoAdminInitiateAuthResult(com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult actualAdminInitiateAuthResult) {
        this.setSession(actualAdminInitiateAuthResult.getSession());
        this.setChallengeName(actualAdminInitiateAuthResult.getChallengeName());
        AuthenticationResult authenticationResult = new AuthenticationResult();
        AuthenticationResultType actualAuthenticationResult = actualAdminInitiateAuthResult.getAuthenticationResult();
        if (actualAuthenticationResult != null) {
            if (actualAuthenticationResult.getAccessToken() != null) {
                authenticationResult.setAccessToken(actualAuthenticationResult.getAccessToken());
            }
            if (actualAuthenticationResult.getExpiresIn() != null) {
                authenticationResult.setExpiresIn(actualAuthenticationResult.getExpiresIn());
            }
            if (actualAuthenticationResult.getIdToken() != null) {
                authenticationResult.setIdToken(actualAuthenticationResult.getIdToken());
            }
            if (actualAuthenticationResult.getRefreshToken() != null) {
                authenticationResult.setRefreshToken(actualAuthenticationResult.getRefreshToken());
            }
            if (actualAuthenticationResult.getTokenType() != null) {
                authenticationResult.setTokenType(actualAuthenticationResult.getTokenType());
            }
        }
        this.setAuthenticationResult(authenticationResult);
        this.setChallengeParameters(actualAdminInitiateAuthResult.getChallengeParameters());
    }
}
