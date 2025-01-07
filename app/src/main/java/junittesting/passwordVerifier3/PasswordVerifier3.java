package junittesting.passwordVerifier3;

import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;
import junittesting.passwordVerifier2.ComplicatedLogger;

public class PasswordVerifier3 {
    private ComplicatedLogger logger;
    private ConfigurationService configurationService;

    public PasswordVerifier3(ComplicatedLogger logger, ConfigurationService configurationService) {
        this.logger = logger;
        this.configurationService = configurationService;
    }

    public boolean verifyPassword(String password, List<Function<String, VerifyResult>> rules) {
        var failed = rules.stream()
                .map(each -> each.apply(password))
                .filter(each -> !each.passed())
                .toList();
        if (failed.isEmpty()) {
            log("passed");
            return true;
        }

        log("fail");
        return false;
    }

    private void log(String txt) {
        if (configurationService.getLogLevel().equals("Info")) {
            logger.info(txt);
        } else if (configurationService.getLogLevel().equals("Debug")) {
            logger.debug(txt, null);
        }
    }
}