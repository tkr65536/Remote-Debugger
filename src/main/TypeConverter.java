package main;

import lejos.nxt.Motor;
import lejos.nxt.SensorConstants;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.Color;
import sensor.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Teddy Reiss
 */
public class TypeConverter {

    /**
     *
     * @param port either S1, S2, S3 or S4
     * @return the SensorPort corresponding to the NAME
     * @throws IllegalArgumentException if the NAME isn't one of the specified
     * names
     */
    public static SensorPort identifySensorPortFromName(String port) {
        if (port.equals(Constants.S1)) {
            return SensorPort.S1;
        } else if (port.equals(Constants.S2)) {
            return SensorPort.S2;
        } else if (port.equals(Constants.S3)) {
            return SensorPort.S3;
        } else if (port.equals(Constants.S4)) {
            return SensorPort.S4;
        } else {
            throw new IllegalArgumentException("Sensor has to be either S1, S2, S3 or S4");
        }
    }

    public static String identifySensorPortName(SensorPort port) {
        if (port.equals(SensorPort.S1)) {
            return Constants.S1;
        } else if (port.equals(SensorPort.S2)) {
            return Constants.S2;
        } else if (port.equals(SensorPort.S3)) {
            return Constants.S3;
        } else if (port.equals(SensorPort.S4)) {
            return Constants.S4;
        } else {
            throw new IllegalArgumentException("Sensor has to be either S1, S2, S3 or S4");
        }
    }

    /**
     * Int to represent what happens when the mode passed is the value "Modes" from the mode box
     */
    public static final int NO_MODE_PASSED = -1;
    /**
     * Converts the NAME of a mode in Constants to an int that the sensor
     * classes use to represent the mode
     *
     * @param mode the mode as a string
     * @return the mode as an int
     * @see main.Constants
     */
    public static int findModeFromString(String mode) {
        if (mode.equalsIgnoreCase(Constants.MODE_ANGLESTEP)) {
            return SensorConstants.MODE_ANGLESTEP;
        } else if (mode.equalsIgnoreCase(Constants.MODE_BOOLEAN)) {
            return SensorConstants.MODE_BOOLEAN;
        } else if (mode.equalsIgnoreCase(Constants.MODE_CELSIUS)) {
            return SensorConstants.MODE_CELSIUS;
        } else if (mode.equalsIgnoreCase(Constants.MODE_FARENHEIT)) {
            return SensorConstants.MODE_FARENHEIT;
        } else if (mode.equalsIgnoreCase(Constants.MODE_PCTFULLSCALE)) {
            return SensorConstants.MODE_PCTFULLSCALE;
        } else if (mode.equalsIgnoreCase(Constants.MODE_PERIODCOUNTER)) {
            return SensorConstants.MODE_PERIODCOUNTER;
        } else if (mode.equalsIgnoreCase(Constants.MODE_RAW)) {
            return SensorConstants.MODE_RAW;
        } else if (mode.equalsIgnoreCase(Constants.MODE_TRANSITIONCNT)) {
            return SensorConstants.MODE_TRANSITIONCNT;
        } else if (mode.equalsIgnoreCase(Constants.MODE_CAPTURE)) {
            return (int) UltrasonicSensor.MODE_CAPTURE;
        } else if (mode.equalsIgnoreCase(Constants.MODE_CONTINUOUS)) {
            return (int) UltrasonicSensor.MODE_CONTINUOUS;
        } else if (mode.equalsIgnoreCase(Constants.MODE_OFF)) {
            return (int) UltrasonicSensor.MODE_OFF;
        } else if (mode.equalsIgnoreCase(Constants.MODE_PING)) {
            return (int) UltrasonicSensor.MODE_PING;
        } else if (mode.equalsIgnoreCase(Constants.MODE_RESET)) {
            return (int) UltrasonicSensor.MODE_RESET;
        } else if (mode.equalsIgnoreCase(Constants.FLOODLIGHT_ON)) {
            return LegoLightSensor.FLOODLIGHT_ON;
        } else if (mode.equalsIgnoreCase(Constants.FLOODLIGHT_OFF)) {
            return LegoLightSensor.FLOODLIGHT_OFF;
        } else if (mode.equalsIgnoreCase(Constants.DB)) {
            return LegoSoundSensor.DB;
        } else if (mode.equalsIgnoreCase(Constants.DBA)) {
            return LegoSoundSensor.DBA;
        } else if (mode.equalsIgnoreCase(UI.DEFAULT_MODE_BOX)) {
            return NO_MODE_PASSED;
        }
        throw new IllegalArgumentException("See javadoc for proper modes");
    }

    public static String findNameFromMode(int mode) {
        switch (mode) {
            case UltrasonicSensor.MODE_CAPTURE:
                return Constants.MODE_CAPTURE;
            case UltrasonicSensor.MODE_CONTINUOUS:
                return Constants.MODE_CONTINUOUS;
            case UltrasonicSensor.MODE_OFF:
                return Constants.MODE_OFF;
            case UltrasonicSensor.MODE_PING:
                return Constants.MODE_PING;
            case UltrasonicSensor.MODE_RESET:
                return Constants.MODE_RESET;
            default:
                return null;
        }

        /*if (mode == UltrasonicSensor.MODE_CAPTURE) {
         return Constants.MODE_CAPTURE;
         } else if (mode == UltrasonicSensor.MODE_CONTINUOUS) {
         return Constants.MODE_CONTINUOUS;
         } else if (mode == UltrasonicSensor.MODE_OFF) {
         return Constants.MODE_OFF;
         } else if (mode == UltrasonicSensor.MODE_PING) {
         return Constants.MODE_PING;
         } else if (mode == UltrasonicSensor.MODE_RESET) {
         return Constants.MODE_RESET;
         } else {
         return null;
         }*/
    }

    public static Sensor identifySensorTypeFromName(String selectedType, SensorPort port) {
        if (selectedType.equals(LegoTouchSensor.NAME)) {
            return new LegoTouchSensor(port);
        } else if (selectedType.equals(LegoLightSensor.NAME)) {
            return LegoLightSensor.getInstance(port);
        } else if (selectedType.equals(LegoSoundSensor.NAME)) {
            return new LegoSoundSensor(port);
        } else if (selectedType.equals(LegoUltrasonicSensor.NAME)) {
            return new LegoUltrasonicSensor(port);
        } else if (selectedType.equals(LegoColorSensor.NAME)) {
            return new LegoColorSensor(port);
        } else if (selectedType.equals(HTIRSeekerV2.NAME)) {
            return new HTIRSeekerV2(port);
        }            else {
            return null;
        }
    }
    
    public static RemoteMotor getMotorFromPortName(String port) {
        if (port.equalsIgnoreCase(Constants.A)) {
            return Motor.A;
        } else if (port.equalsIgnoreCase(Constants.B)) {
            return Motor.B;
        } else if (port.equalsIgnoreCase(Constants.C)) {
            return Motor.C;
        } else {
            return null;
        }
    }

    public static String convertColorToString(int colorID) {
        switch (colorID) {
            case Color.BLACK:
                return "Black";
            case Color.BLUE:
                return "Blue";
            case Color.CYAN:
                return "Cyan";
            case Color.DARK_GRAY:
                return "Dark Gray";
            case Color.GRAY:
                return "Gray";
            case Color.GREEN:
                return "Green";
            case Color.LIGHT_GRAY:
                return "Light Gray";
            case Color.MAGENTA:
                return "Magenta";
            case Color.NONE:
                return "No Color";
            case Color.ORANGE:
                return "Orange";
            case Color.PINK:
                return "Pink";
            case Color.RED:
                return "Red";
            case Color.WHITE:
                return "White";
            case Color.YELLOW:
                return "Yellow";
            default:
                return "No Color";
        }
    }

    public static int getRowFromMotorPort(char id) {
        if (("" + id).equals(Constants.A)) {
            return 0;
        } else if (("" + id).equals(Constants.B)) {
            return 1;
        } else if (("" + id).equals(Constants.C)) {
            return 2;
        } else {
            return -1;
        }
    }
}