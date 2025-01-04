package junittesting.passwordVerifier2;

import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifier2 {
    public boolean verifyPassword(String password, List<Function<String, VerifyResult>> rules, Logger logger) {
        var failed = rules.stream()
                .map(each -> each.apply(password))
                .filter(each -> !each.passed())
                .toList();
        if (failed.isEmpty()) {
            logger.info("passed");
            return true;
        }

        logger.info("fail");
        return false;
    }
}
