package org.sch.vulcanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;

public class MyRobot extends IterativeRobot {

    // this is a conflicting comment
    @Override
    public void robotInit() {
        // here's dev1 changing robotInit:  deleted call to superclass ctor
    }

    @Override
    public void autonomousInit() {
        // here's dev2 updating autonomousInit() in parallel with dev1's changes
        //super.autonomousInit();
        System.out.println("Hello from autonInit()!");
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
    }
}
