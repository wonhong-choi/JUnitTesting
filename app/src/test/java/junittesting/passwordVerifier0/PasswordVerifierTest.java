package junittesting.passwordVerifier0;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PasswordVerifierTest {
    @Tag("testVerifyPassword")
    @Test
    void givenAFailingRuleReturnsErrors() {
        Function<String, VerifyResult> fakeRule = (password) -> {
            return new VerifyResult(false, "fake reason");
        };

        PasswordVerifier verifier = new PasswordVerifier();
        var errors = verifier.verifyPassword("any value", Arrays.asList(fakeRule));

        assertTrue(errors.getFirst().contains("fake reason"));
    }
}
