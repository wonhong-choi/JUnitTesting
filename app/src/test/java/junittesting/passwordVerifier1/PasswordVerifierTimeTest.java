package junittesting.passwordVerifier1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifierTimeTest {

    @TestVerifyPassword
    void onWeekendsThrowsExceptions() {
        var varifier = new PasswordVerifierTime();
        Supplier<DayOfWeek> alwaysSunday = () -> DayOfWeek.SUNDAY;
        assertThatThrownBy(() -> varifier.verifyPassword("any value", null, alwaysSunday))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("It's the weekend");
    }
}
