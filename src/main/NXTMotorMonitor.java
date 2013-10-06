/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

/**
 * Will repot data on:
 *      encoder counts
 *      current speed
 *      current power
 *      direction?
 * @author Teddy Reiss
 */
public class NXTMotorMonitor implements Runnable {

    private RemoteMotor motor_a = Motor.A;
    private RemoteMotor motor_b = Motor.B;
    private RemoteMotor motor_c = Motor.C;
    private ArrayList<MotorData> motorInfo;
    public static final String POWER_EVENT = "power event";
    public static final String SPEED_EVENT = "speed event";
    public static final String ENCODER_EVENT = "encoder event";
    public static final String ALL_EVENT = "all events";
    private ArrayList<NXTMotorListener> powerList;
    private ArrayList<NXTMotorListener> speedList;
    private ArrayList<NXTMotorListener> encoderList;
    
    public NXTMotorMonitor() {
        motorInfo = new ArrayList<MotorData>();
        motorInfo.add(new MotorData(motor_a));
        motorInfo.add(new MotorData(motor_b));
        motorInfo.add(new MotorData(motor_c));
        powerList = new ArrayList<NXTMotorListener>();
        speedList = new ArrayList<NXTMotorListener>();
        encoderList = new ArrayList<NXTMotorListener>();
    }
    
    @Override
    public void run() {
        while(true) {
            for(MotorData data : motorInfo) {
                RemoteMotor motor = data.getMotor();
                int power = motor.getPower();
                int speed = motor.getSpeed();
                int encoderCount = motor.getTachoCount();
                
                if (power != data.getPower()) {
                    notifyPowerListeners(power, motor);
                    data.setPower(power);
                }
                if (speed != data.getSpeed()) {
                    notifySpeedListeners(speed, motor);
                    data.setSpeed(speed);
                }
                if (encoderCount != data.getEncoder()) {
                    notifyEncoderListeners(encoderCount, motor);
                    data.setEncoder(encoderCount);
                }
                Thread.yield();
                /*try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }*/
            }
        }
    }
    
    private void notifyPowerListeners(int power, RemoteMotor motor) {
        for (NXTMotorListener listener : powerList) {
            listener.NXTMotorUpdate(POWER_EVENT, power, motor);
        }
    }
    
    private void notifySpeedListeners(int speed, RemoteMotor motor) {
        for (NXTMotorListener listener : speedList) {
            listener.NXTMotorUpdate(SPEED_EVENT, speed, motor);
        }
    }
    
    private void notifyEncoderListeners(int encoderCount, RemoteMotor motor) {
        for (NXTMotorListener listener : encoderList) {
            listener.NXTMotorUpdate(ENCODER_EVENT, encoderCount, motor);
        }
    }
    
    public void addListener(NXTMotorListener toAdd, String listenerType) {
        if (listenerType.equalsIgnoreCase(POWER_EVENT)) {
            powerList.add(toAdd);
        } else if (listenerType.equalsIgnoreCase(SPEED_EVENT)) {
            speedList.add(toAdd);
        } else if (listenerType.equalsIgnoreCase(ENCODER_EVENT)) {
            encoderList.add(toAdd);
        } else if (listenerType.equalsIgnoreCase(ALL_EVENT)) {
            powerList.add(toAdd);
            speedList.add(toAdd);
            encoderList.add(toAdd);
        } else {
            throw new IllegalArgumentException("Listener type must be one of the constants in this file (see javadoc)");
        }
    }
    public interface NXTMotorListener {
        public void NXTMotorUpdate(String eventType, int eventData, RemoteMotor motor);
    }
    
}
