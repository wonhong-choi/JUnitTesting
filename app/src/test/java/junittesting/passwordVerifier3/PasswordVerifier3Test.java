package junittesting.passwordVerifier3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import junittesting.passwordVerifier0.VerifyResult;
import junittesting.passwordVerifier2.ComplicatedLogger;

@ExtendWith(MockitoExtension.class)
public class PasswordVerifier3Test {
    @InjectMocks
    PasswordVerifier3 verifier;
    
    @Mock
    ComplicatedLogger complicatedLogger;
    @Mock
    ConfigurationService configurationService;
    @Mock
    MaintenanceWindow maintenanceWindow;
    @Spy
    List<Function<String, VerifyResult>> rules = Collections.emptyList();

    @BeforeEach
    private void setUp() {
        when(configurationService.getLogLevel()).thenReturn("Info");
    }
    
    @Test
    public void testInfoLog() {
        var argCaptor = ArgumentCaptor.forClass(String.class);
        verifier.verifyPassword("any value");
        
        verify(complicatedLogger).info(argCaptor.capture());
        assertThat(argCaptor.getValue()).contains("pass");
        verify(complicatedLogger, never()).debug(anyString(), anyString());
    }

    @Test
    public void testUnderMaintenanceLog() {
        when(maintenanceWindow.isUnderMaintenance()).thenReturn(true);
        var argCaptor = ArgumentCaptor.forClass(String.class);
        
        verifier.verifyPassword("any value");
        
        verify(complicatedLogger, never()).debug(anyString(), anyString());
        verify(complicatedLogger).info(argCaptor.capture());
        assertThat(argCaptor.getValue()).contains("Under Maintenance");
    }
}
