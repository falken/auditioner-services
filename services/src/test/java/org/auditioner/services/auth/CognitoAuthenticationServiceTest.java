package org.auditioner.services.auth;

import org.auditioner.services.TestResourceBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CognitoAuthenticationServiceTest extends TestResourceBase {
    @Test
    public void testAdminInitiateAuth() {
        new CognitoAuthenticationService().adminInitiateAuth("tbrobston", "Blipblip123!");
    }

    @Test
    public void testAdminCreateUser() {
        new CognitoAuthenticationService().adminCreateUser("tbrobston", "anthonybrobston@gmail.com");
    }

    @Test
    public void testAdminRespondToAuthChallenge() {
        String challengeName = "NEW_PASSWORD_REQUIRED";
        Map<String, String> challengeResponses = new HashMap<>();
        challengeResponses.put("USERNAME", "tbrobston");
        challengeResponses.put("NEW_PASSWORD", "Blipblip123!");
        String session = "k0oUA0MXRSKnQi--YbiqsBA6kQjhSsTvQcBpq2JZCdU0-V7JyJ1UkyDeEtD0PtQCaFXzeFcLMzri1vYTLoIozfqE-njBDqE3StnLBnL5jO2n11QdYajPxLr26AatWHxmlR_uIq4RBIp9sWWaVF8GWUsHDCt4IEea-a8Im2JgmFzWQ-aGCa7cLgc2rn0Tw6srUc5yst4kcmT8XBcWlyxoaCLjxnWdAEHGp0erX787SSncsOR95EEnLZ_W-AI0MWzUqrFR38BtY431IG0PP6QRV7-eTK0BN0SQcLMbp011V4Rb2DSCEPYUCTpxz-SqDjd3QjmPnt2xKC6l5mjherY8iFUt0jeo3WiFMjWHtpDHdsGmD_upc3p1j32lRT7qgWSMnXliMhw_rbR_cCG-Q3yZUs674VRhBH7tFJRD_qNXUziy32puYFv4RtwgVQ2ouBGzUaVq0E37TorWP8PDBkaAwJtjX-KDC-I_Q-dUmlTSP5QnpIdgn59f1XKguAEqjK-pX6hj0PAWAbQNQg_YfXqhSEtZxIqe7GGkvRt9VgRtiqVbwp--MjUuzZTT4Ulkxy0Pjn0wfyIUVduYdhCbOCKZ1IDwXguvkoMM7IMb3RA16aCUYQC9vLeeWYBQviwBwf068r_L6_ULZZQoQFklG7-eZcgSuxTLRwPfNCNQUXraL9POhzofrGWjVvLESZiBN60OUQzylA";
        new CognitoAuthenticationService().adminRespondToAuthChallenge(challengeName, challengeResponses, session);
    }
}