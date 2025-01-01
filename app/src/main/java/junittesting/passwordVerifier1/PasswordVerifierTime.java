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

    public List<String> verifyPassword(String password, List<Function<String, VerifyResult>> rules, Supplier<DayOfWeek> getDaySupplier) {
        if(WEEKENDS.contains(getDaySupplier.get())){
            throw new RuntimeException("It's the weekend!");
        }

        return null;
    }
}
