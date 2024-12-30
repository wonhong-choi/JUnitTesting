package junittesting.passwordVerifier0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PasswordVerifier {
    private List<Function<String, VerifyResult>> rules;

    public PasswordVerifier() {
        this.rules = new ArrayList<>();
    }

    public void addRule(Function<String, VerifyResult> rule) {
        this.rules.add(rule);
    }

    public List<String> verifyPassword(String password) {
        if (this.rules.size() == 0) {
            throw new RuntimeException("There are no rules configured");
        }
        
        return this.rules.stream()
                .filter(rule -> !rule.apply(password).passed())
                .map(rule -> rule.apply(password).reason())
                .toList();
    }
}
