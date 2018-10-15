import com.ctre.phoenix.motorcontrol.ControlMode;
import org.junit.Test;
import org.sch.vulcanrobotics.simulation.SimTalonSRX;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class SimTalonSRXTest {

    @Test
    public void defaultMode() {
        SimTalonSRX t = new SimTalonSRX(2);
        assertEquals("expected device id 2", 2, t.getDeviceID());
        assertEquals("expected ControlMode.PercentOutput", ControlMode.PercentOutput, t.getControlMode());
    }

    @Test
    public void followerModeUsingSet() {
        SimTalonSRX master = new SimTalonSRX(1),
                    follower = new SimTalonSRX(2);
        System.out.println("setting 2 to follow 1 via set()");
        // SimTalonSRX doesn't implement this way to set follower, so should have no effect on control mode
        follower.set(ControlMode.Follower, master.getDeviceID());
        assertEquals("expected ControlMode.PercentOutput on 1", ControlMode.PercentOutput, master.getControlMode());
        assertEquals("expected ControlMode.PercentOutput on 2", ControlMode.PercentOutput, follower.getControlMode());
    }

    @Test
    public void followerModeUsingFollow() {
        SimTalonSRX master = new SimTalonSRX(1),
                    follower = new SimTalonSRX(2);
        System.out.println("setting 2 to follow 1 via follow()");
        follower.follow(master);
        assertEquals("expected ControlMode.PercentOutput on 1", ControlMode.PercentOutput, master.getControlMode());
        assertEquals("epected ControlMode.Follower on 2", ControlMode.Follower, follower.getControlMode());
    }
}
