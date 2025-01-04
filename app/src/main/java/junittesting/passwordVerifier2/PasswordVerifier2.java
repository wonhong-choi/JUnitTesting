package junittesting.passwordVerifier2;

import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifier2 {
    private ComplecatedLogger logger;
    private List<Function<String, VerifyResult>> rules;

    public PasswordVerifier2(ComplecatedLogger logger, List<Function<String, VerifyResult>> rules) {
        this.logger = logger;
        this.rules = rules;
    }

    public boolean verifyPassword(String password) {
        var failed = this.rules.stream()
                .map(each -> each.apply(password))
                .filter(each -> !each.passed())
                .toList();
        if (failed.isEmpty()) {
            this.logger.info("passed");
            return true;
        }

        this.logger.info("fail");
        return false;
    }
}