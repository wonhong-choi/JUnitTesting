package junittesting.passwordVerifier0;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OneUpperCaseRuleTest {
    
    @Test
    void givenNoUppercase(){
        var rule = new OneUpperCaseRule();
        var result = rule.apply("abc");
        assertEquals(false, result.passed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc", "aBc"})
    void givenOneUppercase(String password){
        var rule = new OneUpperCaseRule();
        var result = rule.apply(password);
        assertEquals(true, result.passed());
    }
}
