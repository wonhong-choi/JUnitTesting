package junittesting.passwordVerifier2;

import java.text.MessageFormat;

public class ComplecatedLogger implements Logger {

    public void info(String txt) {
        System.out.println(MessageFormat.format("INFO: {0}", txt));
    }

}
