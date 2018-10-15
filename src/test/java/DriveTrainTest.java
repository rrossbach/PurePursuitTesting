import org.junit.Test;
import org.sch.vulcanrobotics.drivetrain.DriveTrain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DriveTrainTest {


    @Test
    public void createSimDrive() {

        // try creating without specifying type, should exception
        boolean exceptionThrown = false;
        try {
            System.out.println("testing DriveTrain.getInstance(null), should throw exception");
            DriveTrain d = DriveTrain.getInstance(null);
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        assertTrue("should have thrown exception", exceptionThrown);

        // try creating simulation type, should succeed.  2nd call to getInstance should return same (existing)
        // simulated instance;  subsequent call to getInstance() for PHYSICAL type should throw exception since it
        // doesn't match the existing simulated instance
        exceptionThrown = false;
        try {
            System.out.println("testing DriveTrain.getInstance(Type.SIMULATED), should succeed");
            DriveTrain d = DriveTrain.getInstance(DriveTrain.Type.SIMULATED);
            assertNotNull("DriveTrain.getInstance(Type.SIMULATED) failed", d);
            System.out.println("testing 2nd call to getInstance, should return existing simulated drive train object");
            DriveTrain e = DriveTrain.getInstance(DriveTrain.Type.SIMULATED);
            assertEquals("2nd getInstance() should have returned existing instance", d, e);
            System.out.println("testing DriveTrain.getInstance(Type.PHYSICAL when SIMULATED instance already exists, should throw exception");
            DriveTrain f = DriveTrain.getInstance(DriveTrain.Type.PHYSICAL);
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
        }

        assertTrue("should have thrown drive type mismatch exception", exceptionThrown);
    }
}
