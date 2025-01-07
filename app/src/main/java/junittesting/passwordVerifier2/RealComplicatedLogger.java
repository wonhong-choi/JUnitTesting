package junittesting.passwordVerifier2;

public class RealComplicatedLogger implements ComplicatedLogger {

    @Override
    public void info(String txt) {
        // do some complecated operation
    }

    @Override
    public void debug(String txt, Object object) {
        // do some complecated operation
    }

    @Override
    public void warn(String txt) {
        // do some complecated operation
    }

    @Override
    public void error(String txt, String location, String stackTrace) {
        // do some complecated operation
    }

}
