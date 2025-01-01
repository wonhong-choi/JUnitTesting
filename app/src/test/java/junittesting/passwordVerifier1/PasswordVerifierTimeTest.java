package junittesting.passwordVerifier1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junittesting.passwordVerifier0.TestVerifyPassword;


public class PasswordVerifierTimeTest {
    private final List<DayOfWeek> WEEKENDS = new ArrayList<>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

    @TestVerifyPassword
    void onWeekendsThrowsExceptions() {
        if(WEEKENDS.contains(LocalDate.now().getDayOfWeek())){
            var varifier = new PasswordVerifierTime();
            assertThatThrownBy(() -> varifier.verifyPassword("any value", null))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("It's the weekend");
        }
    }
}
