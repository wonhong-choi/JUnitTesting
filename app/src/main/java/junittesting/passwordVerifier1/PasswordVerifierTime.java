package junittesting.passwordVerifier1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifierTime {
    private final List<DayOfWeek> WEEKENDS = new ArrayList<>(Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY));

    public List<String> verifyPassword(String password, List<Function<String, VerifyResult>> rules) {
        var dayOfWeek = LocalDate.now().getDayOfWeek();

        if(WEEKENDS.contains(dayOfWeek)){
            throw new RuntimeException("It's the weekend!");
        }

        return null;
    }
}
