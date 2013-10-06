package main;


import lejos.nxt.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Teddy Reiss
 */
public class Main {

    public static void main(String[] args) {
       new UI();
        /*System.out.println("Printing out various stats");

        LightSensor light = new LightSensor(SensorPort.S1, true);
        TouchSensor touch = new TouchSensor(SensorPort.S2);
        UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S3);
        while (true) {
            //System.out.println("Tacho " + Motor.B.getTachoCount() + " " + Motor.C.getTachoCount());
            System.out.println("LightSensor " + getSensorReading(light));

            System.out.println(getSensorReading(touch));

            System.out.println(getSensorReading(ultra));
        }*/
    }

    public static int getSensorReading(Object sensor) {
        if (sensor instanceof LightSensor) {
            LightSensor light = (LightSensor) sensor;
            return light.getLightValue();
        } else if (sensor instanceof TouchSensor) {
            TouchSensor touch = (TouchSensor) sensor;
            return touch.isPressed() ? 1 : 0;
        } else if (sensor instanceof UltrasonicSensor) {
            UltrasonicSensor ultra = (UltrasonicSensor) sensor;
            return ultra.getDistance();
        } else {
            return -1;
        }
    }
}
