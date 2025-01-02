package junittesting.passwordVerifier1;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifierTime {
    private final List<DayOfWeek> WEEKENDS = new ArrayList<>(Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY));

    private List<Function<String, VerifyResult>> rules;
    private TimeProvider timeProvider;
    

    public PasswordVerifierTime(List<Function<String, VerifyResult>> rules, TimeProvider timeProvider) {
        this.rules = rules;
        this.timeProvider = timeProvider;
    }

    public List<String> verifyPassword(String password) {
        if(WEEKENDS.contains(this.timeProvider.getDay())){
            throw new RuntimeException("It's the weekend!");
        }

        return Arrays.asList();
    }
}
