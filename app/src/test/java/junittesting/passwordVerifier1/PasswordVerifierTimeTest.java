package junittesting.passwordVerifier1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;

import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifierTimeTest {

    @TestVerifyPassword
    void onWeekendsThrowsExceptions() {
        var varifier = new PasswordVerifierTime();
        assertThatThrownBy(() -> varifier.verifyPassword("any value", null, DayOfWeek.SUNDAY))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("It's the weekend");
    }
}
