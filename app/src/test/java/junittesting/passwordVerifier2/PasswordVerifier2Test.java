package junittesting.passwordVerifier2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifier2Test {
    @TestVerifyPassword
    void onWhenAllRulesPassPrintPass() {
        class MockLogger extends RealComplecatedLogger{
            private String infoWritten;

            @Override
            public void info(String txt) {
                this.infoWritten = txt;
            }
        }
        
        var mockLogger = new MockLogger();
        
        PasswordVerifier2 verifier = new PasswordVerifier2(mockLogger, Collections.emptyList());

        verifier.verifyPassword("any value");

        assertThat(mockLogger.infoWritten).contains("pass");
    }
}
