package junittesting.passwordVerifier0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PasswordVerifierTest {
    PasswordVerifier verifier = new PasswordVerifier();
    List<String> errors;

    @Nested
    class WithAFailingRule {

        @BeforeEach
        void addFakeRule() {
        }

        @TestVerifyPassword
        void returnsErrors() {
            var varifier = makeVerifierWithFailedRule("fake reason");
            var errors = varifier.verifyPassword("any value");
            assertTrue(errors.getFirst().contains("fake reason"));
        }

        @Test
        void hasExactlyOneError() {
            var varifier = makeVerifierWithFailedRule("fake reason");
            var errors = varifier.verifyPassword("any value");
            assertEquals(1, errors.size());
        }
    }

    @Nested
    class WithAPassingRule {

        @TestVerifyPassword
        void hasNoErrors() {
            var verifier = makeVerifierWithPassingRule();
            var errors = verifier.verifyPassword("any value");
            assertEquals(0, errors.size());
        }
    }

    @Nested
    class WithAFailingAndPassingRule {

        @TestVerifyPassword
        void hasOneError() {
            var verifier = makeVerifierWithFailedRule("fake reason");
            verifier.addRule(makePassingRule());
            var errors = verifier.verifyPassword("any value");
            assertEquals(1, errors.size());
        }

        @TestVerifyPassword
        void errorTextBelongsToFailedRule() {
            var verifier = makeVerifierWithFailedRule("fake reason");
            verifier.addRule(makePassingRule());
            var errors = verifier.verifyPassword("any value");
            assertTrue(errors.getFirst().contains("fake reason"));
        }
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
