
package main;

import lejos.nxt.remote.RemoteMotor;

/**
 * Stores data on:
 *  encoder counts, power, speed
 * allows for saving and then comparing to new data
 * @author Teddy Reiss
 */
public class MotorData {
    private RemoteMotor motor;
    private int encoder;
    private int power;
    private int speed;
    
    public MotorData(RemoteMotor motor) {
        this.motor = motor;
        encoder = motor.getTachoCount();
        power = motor.getPower();
        speed = motor.getSpeed();
    }

    /**
     * @return the encoder
     */
    public int getEncoder() {
        return encoder;
    }

    /**
     * @param encoder the encoder to set
     */
    public void setEncoder(int encoder) {
        this.encoder = encoder;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the motor
     */
    public RemoteMotor getMotor() {
        return motor;
    }
}
