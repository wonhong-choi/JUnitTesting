package junittesting.passwordVerifier1;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import junittesting.passwordVerifier0.VerifyResult;

public class PasswordVerifierTime {
    private final List<DayOfWeek> WEEKENDS = new ArrayList<>(Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY));

    private List<Function<String, VerifyResult>> rules;
    private Supplier<DayOfWeek> getDayOfWeekSupplier;
    

    public PasswordVerifierTime(List<Function<String, VerifyResult>> rules, Supplier<DayOfWeek> getDayOfWeekSupplier) {
        this.rules = rules;
        this.getDayOfWeekSupplier = getDayOfWeekSupplier;
    }

    public List<String> verifyPassword(String password) {
        if(WEEKENDS.contains(this.getDayOfWeekSupplier.get())){
            throw new RuntimeException("It's the weekend!");
        }

        return Arrays.asList();
    }
}
