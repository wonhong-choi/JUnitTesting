package junittesting.passwordVerifier0;

import java.util.List;
import java.util.function.Function;

public class PasswordVerifier {
    public List<String> verifyPassword(String password, List<Function<String, VerifyResult>> rules) {
        return rules.stream()
                .filter(rule -> !rule.apply(password).passed())
                .map(rule -> rule.apply(password).reason())
                .toList();
    }
}
