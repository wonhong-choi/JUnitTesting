package junittesting.passwordVerifier2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifier2Test {
    @TestVerifyPassword
    void onWhenAllRulesPassPrintPass() {
        var mockLogger = new Logger() {
            private String written;
            
            @Override
            public void info(String txt) {
                this.written = txt;
            }
        };

        PasswordVerifier2 verifier = new PasswordVerifier2(mockLogger, Collections.emptyList());


        verifier.verifyPassword("any value");

        assertThat(mockLogger.written).contains("pass");
    }
}
