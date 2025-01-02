package junittesting.passwordVerifier1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;

import junittesting.passwordVerifier0.TestVerifyPassword;

public class PasswordVerifierTimeTest {

    @TestVerifyPassword
    void onWeekendsThrowsExceptions() {
        var timeProvider = new FakeTimeProvider(DayOfWeek.SATURDAY);
        var varifier = new PasswordVerifierTime(null, timeProvider);

        assertThatThrownBy(() -> varifier.verifyPassword("any value"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("It's the weekend");
    }

    @TestVerifyPassword
    void onWeekdaysPasses() {
        var timeProvider = new FakeTimeProvider(DayOfWeek.MONDAY);
        var varifier = new PasswordVerifierTime(null, timeProvider);
        
        assertThat(varifier.verifyPassword("any value")).hasSize(0);
    }
}
