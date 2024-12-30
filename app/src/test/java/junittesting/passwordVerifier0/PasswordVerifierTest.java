package junittesting.passwordVerifier0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;

public class PasswordVerifierTest {

    @TestVerifyPassword
    void withAFailingRuleReturnsErrors() {
        var varifier = makeVerifierWithFailedRule("fake reason");
        var errors = varifier.verifyPassword("any value");
        assertTrue(errors.getFirst().contains("fake reason"));
    }

    @TestVerifyPassword
    void withAFailingRuleHasExactlyOneError() {
        var varifier = makeVerifierWithFailedRule("fake reason");
        var errors = varifier.verifyPassword("any value");
        assertEquals(1, errors.size());
    }

    @TestVerifyPassword
    void withAPassingRuleHasNoErrors() {
        var verifier = makeVerifierWithPassingRule();
        var errors = verifier.verifyPassword("any value");
        assertEquals(0, errors.size());
    }

    @TestVerifyPassword
    void withAFailingAndPassingRuleHasOneError() {
        var verifier = makeVerifierWithFailedRule("fake reason");
        verifier.addRule(makePassingRule());
        var errors = verifier.verifyPassword("any value");
        assertEquals(1, errors.size());
    }

    @TestVerifyPassword
    void withAFailingAndPassingRuleErrorTextBelongsToFailedRule() {
        var verifier = makeVerifierWithFailedRule("fake reason");
        verifier.addRule(makePassingRule());
        var errors = verifier.verifyPassword("any value");
        assertTrue(errors.getFirst().contains("fake reason"));
    }

    @TestVerifyPassword
    void withNoRulesThrowsException() {
        var verifier = makeVerifier();
        assertThrows(RuntimeException.class, () -> {
            verifier.verifyPassword("any value");
        });
    }

    private PasswordVerifier makeVerifierWithPassingRule() {
        var verifier = makeVerifier();
        verifier.addRule(makePassingRule());
        return verifier;
    }

    private PasswordVerifier makeVerifierWithFailedRule(String reason) {
        var verifier = makeVerifier();
        Function<String, VerifyResult> fakeRule = (password) -> {
            return new VerifyResult(false, reason);
        };
        verifier.addRule(fakeRule);
        return verifier;
    }

    private PasswordVerifier makeVerifier() {
        return new PasswordVerifier();
    }

    private Function<String, VerifyResult> makePassingRule() {
        Function<String, VerifyResult> fakeRule = (password) -> {
            return new VerifyResult(true, "");
        };
        return fakeRule;
    }
}
