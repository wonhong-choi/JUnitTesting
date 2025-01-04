package junittesting.passwordVerifier2;

public interface ComplecatedLogger extends Logger{

    public void info(String txt);

    public void debug(String txt, Object object);

    public void warn(String txt);

    public void error(String txt, String location, String stackTrace);

}
