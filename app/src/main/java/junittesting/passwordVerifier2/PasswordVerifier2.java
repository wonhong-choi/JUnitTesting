package junittesting.passwordVerifier2;

import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifier2 {
    private Logger logger;

    public PasswordVerifier2(Logger logger) {
        this.logger = logger;
    }

    public boolean verifyPassword(String password, List<Function<String, VerifyResult>> rules) {
        var failed = rules.stream()
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