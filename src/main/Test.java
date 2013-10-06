package main;


import lejos.nxt.Motor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teddy Reiss
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello");
        while(true) {
            System.out.println(Motor.B.getTachoCount() + " " + Motor.C.getTachoCount());
        }
    }
}
