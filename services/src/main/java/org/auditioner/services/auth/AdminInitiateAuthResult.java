package org.auditioner.services.auth;

import java.util.Map;

public class AdminInitiateAuthResult {
    private AuthenticationResult authenticationResult;
    private String challengeName;
    private Map<String, String> challengeParameters;
    private String session;

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public Map<String, String> getChallengeParameters() {
        return challengeParameters;
    }

    public void setChallengeParameters(Map<String, String> challengeParameters) {
        this.challengeParameters = challengeParameters;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
