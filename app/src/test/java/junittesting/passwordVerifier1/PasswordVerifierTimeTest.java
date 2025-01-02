package junittesting.passwordVerifier1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifierTimeTest {

    @TestVerifyPassword
    void onWeekendsThrowsExceptions() {
        Supplier<DayOfWeek> alwaysSunday = () -> DayOfWeek.SUNDAY;
        var varifier = new PasswordVerifierTime(null, alwaysSunday);

        assertThatThrownBy(() -> varifier.verifyPassword("any value"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("It's the weekend");
    }
}
