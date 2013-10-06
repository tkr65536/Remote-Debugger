package main;

import java.util.ArrayList;
import sensor.HTIRSeekerV2;
import sensor.LegoColorSensor;
import sensor.LegoLightSensor;
import sensor.LegoSoundSensor;
import sensor.LegoTouchSensor;
import sensor.LegoUltrasonicSensor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Lots and Lots of useful Constants
 * @author Teddy Reiss
 */
public class Constants {
    private Constants() {}
    private static ArrayList<String> sensorTypeNameList;
    
    public static ArrayList<String> getSensorTypes () {
        if (sensorTypeNameList == null) {
            populateList();
        }
        return sensorTypeNameList;
    }
    //
    protected static void populateList() {
        sensorTypeNameList = new ArrayList<String>();
        sensorTypeNameList.add(LegoTouchSensor.NAME);
        sensorTypeNameList.add(LegoColorSensor.NAME);
        sensorTypeNameList.add(LegoLightSensor.NAME);
        sensorTypeNameList.add(LegoUltrasonicSensor.NAME);
        sensorTypeNameList.add(LegoSoundSensor.NAME);
        sensorTypeNameList.add(HTIRSeekerV2.NAME);
    }
    
    public static void addSensorType(String newName) {
        if (sensorTypeNameList == null) {
            populateList();
        }
        sensorTypeNameList.add(newName);
    }
    
    /**
     * String Constant for Sensor Port 1
     * @see lejos.nxt.SensorPort#S1
     */
    public final static String S1 = "S1";
    /**
     * String Constant for Sensor Port 2
     * @see lejos.nxt.SensorPort#S2
     */
    public final static String S2 = "S2";
    /**
     * String Constant for Sensor Port 3
     * @see lejos.nxt.SensorPort#S3
     */
    public final static String S3 = "S3";
    /**
     * String Constant for Sensor Port 4
     * @see lejos.nxt.SensorPort#S4
     */
    public final static String S4 = "S4";
    
    public final static String A = "A";
    public final static String B = "B";
    public final static String C = "C";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_ANGLESTEP
     */
    public final static String MODE_ANGLESTEP = "MODE_ANGLESTEP";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_BOOLEAN
     */
    public final static String MODE_BOOLEAN = "MODE_BOOLEAN";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_BOOLEAN
     */
    public final static String MODE_CELSIUS = "MODE_CELSIUS";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_FARENHEIT
     */
    public final static String MODE_FARENHEIT = "MODE_FARENHEIT";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_PCTFULLSCALE
     */
    public final static String MODE_PCTFULLSCALE = "MODE_PCTFULLSCALE";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_PERIODCOUNTER
     */
    public final static String MODE_PERIODCOUNTER = "MODE_PERIODCOUNTER";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_RAW
     */
    public final static String MODE_RAW = "MODE_RAW";
    /**
     * String constant for type conversion.
     * @see lejos.nxt.SensorConstants#MODE_TRANSITIONCNT
     */
    public final static String MODE_TRANSITIONCNT = "MODE_TRANSITIONCNT";
    /**
     * String constant for type conversion
     * @see lejos.nxt.SoundSensor
     */
    public final static String SOUND_SENSOR = "LEGO Sound Sensor";
    //Ultrasonic Sensor Modes
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor#MODE_CAPTURE
     */
    public final static String MODE_CAPTURE = "MODE_CAPTURE";
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor#MODE_CONTINUOUS
     */
    public final static String MODE_CONTINUOUS = "MODE_CONTINUOUS";
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor#MODE_OFF
     */
    public final static String MODE_OFF = "MODE_OFF";
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor#MODE_PING
     */
    public final static String MODE_PING = "MODE_PING";
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor#MODE_RESET
     */
    public final static String MODE_RESET = "MODE_RESET";
    //Light Sensor Modes
    /**
     * String constant for type conversion
     * @see sensor.LegoLightSensor#FLOODLIGHT_ON
     */
    public final static String FLOODLIGHT_ON = "Floodlight On";
    /**
     * String constant for type conversion
     * @see sensor.LegoLightSensor#FLOODLIGHT_OFF
     */
    public final static String FLOODLIGHT_OFF = "Floodlight Off";
    public final static String DB = "DB";
    public final static String DBA = "DBA";
    public final static String COLOR_RED = "Red Floodlight";
    public final static String COLOR_BLUE = "Blue Floodlight";
    public final static String COLOR_GREEN = "Green Floodlight";
    public final static String COLOR_NONE = "No Floodlight";
    public final static String MOTOR_SPEED = "Speed";
    public final static String MOTOR_POWER = "Power";
    public final static String MOTOR_ENCODER = "Encoder Count";
}