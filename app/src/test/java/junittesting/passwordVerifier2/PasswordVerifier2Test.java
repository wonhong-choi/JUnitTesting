package junittesting.passwordVerifier2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifier2Test {
    @TestVerifyPassword
    void onWhenAllRulesPassPrintPass() {
        PasswordVerifier2 verifier = new PasswordVerifier2();

        var mockLogger = new Logger() {
            private String written;
            
            @Override
            public void info(String txt) {
                this.written = txt;
            }
        };

        verifier.verifyPassword("any value", Collections.emptyList(), mockLogger);

        assertThat(mockLogger.written).contains("pass");
    }
}
