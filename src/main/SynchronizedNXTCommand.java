/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import lejos.nxt.remote.InputValues;
import lejos.nxt.remote.NXTCommRequest;
import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.OutputState;

/**
 *
 * @author Teddy Reiss
 */
public class SynchronizedNXTCommand extends NXTCommand{
    	public SynchronizedNXTCommand(NXTCommRequest nxtComm) {
		super(nxtComm);
	}

        //Start Sensor Methods (ie reading)
    @Override
    public synchronized InputValues getInputValues(int port) throws IOException {
        return super.getInputValues(port); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized byte setInputMode(int port, int sensorType, int sensorMode) throws IOException {
        return super.setInputMode(port, sensorType, sensorMode); //To change body of generated methods, choose Tools | Templates.
    }
            @Override
        public synchronized byte [] LSGetStatus(byte port) throws IOException{
            return super.LSGetStatus(port);
        }

    @Override
    public synchronized byte[] LSRead(byte port) throws IOException {
        return super.LSRead(port); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized byte LSWrite(byte port, byte[] txData, byte rxDataLength) throws IOException {
        return super.LSWrite(port, txData, rxDataLength); //To change body of generated methods, choose Tools | Templates.
    }

    //Start Motor Methods (ie writing)
    @Override
    public synchronized OutputState getOutputState(int port) throws IOException {
        return super.getOutputState(port); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized byte setOutputState(int port, byte power, int mode, int regulationMode, int turnRatio, int runState, int tachoLimit) throws IOException {
        return super.setOutputState(port, power, mode, regulationMode, turnRatio, runState, tachoLimit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized byte resetMotorPosition(int port, boolean relative) throws IOException {
        return super.resetMotorPosition(port, relative); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
