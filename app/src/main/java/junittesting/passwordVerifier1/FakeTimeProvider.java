package junittesting.passwordVerifier1;

import java.time.DayOfWeek;

public class FakeTimeProvider implements TimeProvider {
    private DayOfWeek dayOfWeek;

    public FakeTimeProvider(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public DayOfWeek getDay() {
        return this.dayOfWeek;
    }

}
