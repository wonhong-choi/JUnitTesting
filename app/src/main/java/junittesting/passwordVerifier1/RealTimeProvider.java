package junittesting.passwordVerifier1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RealTimeProvider implements TimeProvider{

    @Override
    public DayOfWeek getDay() {
        return LocalDate.now().getDayOfWeek();
    }

}
