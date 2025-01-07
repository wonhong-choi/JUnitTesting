package junittesting.passwordVerifier3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import junittesting.passwordVerifier2.ComplicatedLogger;

@ExtendWith(MockitoExtension.class)
public class PasswordVerifier3Test {
    @InjectMocks
    PasswordVerifier3 verifier;
    
    @Mock
    ComplicatedLogger complicatedLogger;

    @Mock
    ConfigurationService configurationService;

    @BeforeEach
    private void setUp() {
    }
    
    @Test
    public void testInfoLog() {
        when(configurationService.getLogLevel()).thenReturn("Info");

        var argCaptor = ArgumentCaptor.forClass(String.class);
        verifier.verifyPassword("any value", Collections.emptyList());
        
        verify(complicatedLogger).info(argCaptor.capture());
        assertThat(argCaptor.getValue()).contains("pass");
        verify(complicatedLogger, never()).debug(anyString(), anyString());
    }
}
