import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SVG_3012_Docker_PipelineTest {

    // ---------- timeCal tests ----------

    @Test
    public void testTimeCal_normalValues() {
        // time = distance / speed = 100 / 50 = 2.0
        assertEquals(2.0, SVG_3012_Docker_Pipeline.timeCal(50, 100), 0.0001);
    }

    @Test
    public void testTimeCal_speedZero_returnsZero() {
        assertEquals(0, SVG_3012_Docker_Pipeline.timeCal(0, 100), 0.0001);
    }

    @Test
    public void testTimeCal_distanceZero_returnsZero() {
        assertEquals(0, SVG_3012_Docker_Pipeline.timeCal(50, 0), 0.0001);
    }

    @Test
    public void testTimeCal_distanceNegative_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SVG_3012_Docker_Pipeline.timeCal(50, -10);
        });
    }

    @Test
    public void testTimeCal_speedNegative_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SVG_3012_Docker_Pipeline.timeCal(-50, 100);
        });
    }

    @Test
    public void testTimeCal_decimalValues() {
        // time = 12.5 / 2.5 = 5.0
        assertEquals(5.0, SVG_3012_Docker_Pipeline.timeCal(2.5, 12.5), 0.0001);
    }

    // ---------- buildReport tests ----------

    @Test
    public void testBuildReport_normalValues() {
        String expected = "distance: 100.0 ,time: 2.0, speed: 50.0";
        assertEquals(expected, SVG_3012_Docker_Pipeline.buildReport(50, 100));
    }

    @Test
    public void testBuildReport_zeroSpeed() {
        String expected = "distance: 100.0 ,time: 0.0, speed: 0.0";
        assertEquals(expected, SVG_3012_Docker_Pipeline.buildReport(0, 100));
    }

    @Test
    public void testBuildReport_zeroDistance() {
        String expected = "distance: 0.0 ,time: 0.0, speed: 50.0";
        assertEquals(expected, SVG_3012_Docker_Pipeline.buildReport(50, 0));
    }

    @Test
    public void testBuildReport_negativeDistance_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SVG_3012_Docker_Pipeline.buildReport(50, -100);
        });
    }

    @Test
    public void testBuildReport_negativeSpeed_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SVG_3012_Docker_Pipeline.buildReport(-50, 100);
        });
    }

    // ---------- validateInputs tests ----------

    @Test
    public void testValidateInputs_validValues_noException() {
        assertDoesNotThrow(() -> SVG_3012_Docker_Pipeline.validateInputs(50, 100));
    }

    @Test
    public void testValidateInputs_zeroValues_noException() {
        assertDoesNotThrow(() -> SVG_3012_Docker_Pipeline.validateInputs(0, 0));
    }

    @Test
    public void testValidateInputs_negativeSpeed_throwsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> SVG_3012_Docker_Pipeline.validateInputs(-10, 100)
        );
        assertTrue(exception.getMessage().contains("Speed cannot be negative"));
    }

    @Test
    public void testValidateInputs_negativeDistance_throwsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> SVG_3012_Docker_Pipeline.validateInputs(50, -10)
        );
        assertTrue(exception.getMessage().contains("Distance cannot be negative"));
    }

    @Test
    public void testValidateInputs_bothNegative_throwsExceptionForSpeedFirst() {
        // speed is validated first, so its exception should fire
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> SVG_3012_Docker_Pipeline.validateInputs(-5, -10)
        );
        assertTrue(exception.getMessage().contains("Speed cannot be negative"));
    }
}