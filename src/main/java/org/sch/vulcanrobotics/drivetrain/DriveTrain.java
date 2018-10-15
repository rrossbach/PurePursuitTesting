package org.sch.vulcanrobotics.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.sch.vulcanrobotics.Constants;
import org.sch.vulcanrobotics.simulation.SimTalonSRX;

import java.lang.reflect.Constructor;

public class DriveTrain {

    public enum Type { SIMULATED, PHYSICAL};
    public enum States { IDLE, JOYSTICK_DRIVE, FOLLOW_PATH};
    private static DriveTrain instance = null;
    private static Type instanceType = null;
    private IMotorControllerEnhanced leftMaster, leftFollowers[], rightFollowers[], rightMaster;

    // passing in the Class lets us re-use the same code regardless of which actual motor controller
    // classes we're using.  Allow for master & followers to be different so we can have
    // Victor SPX's following Talon SRX masters, etc
    // Limitation is that all masters need to be the same, and all followers need to be the same
    private DriveTrain(Class masterClass, Class followerClass) throws Exception {

        Constructor ctor = masterClass.getConstructor(int.class);
        leftMaster = (IMotorControllerEnhanced) ctor.newInstance(Constants.DriveTrain.kLeftMaster);
        rightMaster = (IMotorControllerEnhanced) ctor.newInstance(Constants.DriveTrain.kRightMaster);

        ctor = followerClass.getConstructor(int.class);
        leftFollowers = new IMotorControllerEnhanced[Constants.DriveTrain.kLeftFollowers.length];
        rightFollowers = new IMotorControllerEnhanced[Constants.DriveTrain.kRightFollowers.length];
        for (int i=0; i<Constants.DriveTrain.kLeftFollowers.length; i++) {
            leftFollowers[i] = (IMotorControllerEnhanced)ctor.newInstance(Constants.DriveTrain.kLeftFollowers[i]);
        }
        for (int i=0; i<Constants.DriveTrain.kRightFollowers.length; i++) {
            rightFollowers[i] = (IMotorControllerEnhanced)ctor.newInstance(Constants.DriveTrain.kRightFollowers[i]);
        }

        // set up master/follower relationships
        for (IMotorControllerEnhanced m : leftFollowers) {
            m.follow(leftMaster);
        }
        for (IMotorControllerEnhanced m : rightFollowers) {
            m.follow(rightMaster);
        }

    }


    // get a DriveTrain instance of the requested type; throw error if one already created but doesn't match
    // our request
    public static DriveTrain getInstance(Type desiredType) throws Exception {

        if (instance != null) {
            if (instanceType == desiredType) return instance;
            System.out.print("DriveTrain: desiredType ");
            System.out.print(desiredType);
            System.out.print(" actualType: ");
            System.out.println(instanceType);
            throw new Exception("DriveTrain: desiredType does not match existing type");
        }

        // instantiate the motor controllers (simulated or physical depending on requested type)
        if (desiredType == Type.PHYSICAL) instance = new DriveTrain(TalonSRX.class, TalonSRX.class);
        else if (desiredType == Type.SIMULATED) instance = new DriveTrain(SimTalonSRX.class, SimTalonSRX.class);
        else throw new Exception("DriveTrain: requested type neither SIMULATED nor PHYSICAL");

        instanceType = desiredType;
        return instance;
    }

    // return the current DriveTrain instance type
    public Type getInstanceType() {
        return instanceType;
    }

}
