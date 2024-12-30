package junittesting.passwordVerifier0;

import java.util.function.Function;

public class OneUpperCaseRule implements Function<String, VerifyResult> {

    @Override
    public VerifyResult apply(String password) {
        return new VerifyResult(!password.toLowerCase().equals(password), "at least one upper case needed");
    }
}
