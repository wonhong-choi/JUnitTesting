package junittesting.passwordVerifier0;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PasswordVerifierTest {
    PasswordVerifier verifier = new PasswordVerifier();
    Function<String, VerifyResult> fakeRule = (password) -> {
        return new VerifyResult(false, "fake reason");
    };
    List<String> errors;

    @BeforeEach
    void addFakeRule(){
        verifier.addRule(fakeRule);
        errors = verifier.verifyPassword("any value");
    }
    
    @Tag("testVerifyPassword")
    @Test
    void givenAFailingRuleReturnsErrors() {

        assertTrue(errors.getFirst().contains("fake reason"));
    }

    @Tag("testVerifyPassword")
    @Test
    void givenAFailingRuleHasExactlyOneError() {

        assertTrue(errors.size() == 1);
    }
}
