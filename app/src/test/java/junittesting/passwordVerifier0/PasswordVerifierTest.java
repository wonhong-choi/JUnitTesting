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
            Function<String, VerifyResult> fakeRule = (password) -> {
                return new VerifyResult(false, "fake reason");
            };
            verifier.addRule(fakeRule);
            errors = verifier.verifyPassword("any value");
        }

        @TestVerifyPassword
        void givenAFailingRuleReturnsErrors() {
            assertTrue(errors.getFirst().contains("fake reason"));
        }

        @Test
        void givenAFailingRuleHasExactlyOneError() {
            assertEquals(1, errors.size());
        }
    }

    @Nested
    class WithAPassingRule {

        @BeforeEach
        void addFakeRule() {
            Function<String, VerifyResult> fakeRule = (password) -> {
                return new VerifyResult(true, "");
            };
            verifier.addRule(fakeRule);
            errors = verifier.verifyPassword("any value");
        }

        @TestVerifyPassword
        void hasNoErrors() {
            assertEquals(0, errors.size());
        }
    }

    @Nested
    class WithAFailingAndPassingRule {

        @BeforeEach
        void addFakeRules() {
            Function<String, VerifyResult> fakeRulePass = (password) -> {
                return new VerifyResult(true, "fake success");
            };
            Function<String, VerifyResult> fakeRuleFail = (password) -> {
                return new VerifyResult(false, "fake reason");
            };

            verifier.addRule(fakeRulePass);
            verifier.addRule(fakeRuleFail);
            errors = verifier.verifyPassword("any value");
        }

        @TestVerifyPassword
        void hasOneError() {
            assertEquals(1, errors.size());
        }

        @TestVerifyPassword
        void errorTextBelongsToFailedRule() {
            assertTrue(errors.getFirst().contains("fake reason"));
        }
    }

}
