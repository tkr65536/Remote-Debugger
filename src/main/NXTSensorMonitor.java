/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import sensor.Sensor;

/**
 *
 * @author Teddy Reiss
 */
public class NXTSensorMonitor implements Runnable {

    private Sensor S1;
    private Sensor S2;
    private Sensor S3;
    private Sensor S4;
    private ArrayList<NXTSensorListener> listenerList = new ArrayList<NXTSensorListener>();
    private boolean running = false;

    public NXTSensorMonitor(Sensor S1, Sensor S2, Sensor S3, Sensor S4) {
        this.S1 = S1;
        this.S2 = S2;
        this.S3 = S3;
        this.S4 = S4;
    }

    @Override
    public void run() {
        running = true;
        int S1_data = 0;
        int S2_data = 0;
        int S3_data = 0;
        int S4_data = 0;

        while (true) {
            //Should prevent the GUI thread from modifying S1 through S4 while we are reading from them.
            //synchronized (this) {
                if (S1 != null) {
                    int S1_temp = S1.getCommonValue();
                    if (S1_temp != S1_data) {
                        S1_data = S1_temp;
                        notifyListeners(S1);
                    }
                }
                if (S2 != null) {
                    int S2_temp = S2.getCommonValue();
                    if (S2_temp != S2_data) {
                        S2_data = S2_temp;
                        notifyListeners(S2);
                    }
                }
                if (S3 != null) {
                    int S3_temp = S3.getCommonValue();
                    if (S3_temp != S3_data) {
                        S3_data = S3_temp;
                        notifyListeners(S3);
                    }
                }
                if (S4 != null) {
                    int S4_temp = S4.getCommonValue();
                    if (S4_temp != S4_data) {
                        S4_data = S4_temp;
                        notifyListeners(S4);
                    }
                }
                Thread.yield();
                //try {
                //    Thread.sleep(200);
                //} catch (InterruptedException ex) {
                //    ex.printStackTrace();
                //}
            //}
        }
    }

    public synchronized void addNXTListener(NXTSensorListener toAdd) {
        listenerList.add(toAdd);
    }

    public synchronized void removeNXTListener(NXTSensorListener toRemove) {
        listenerList.remove(toRemove);
    }

    private void notifyListeners(Sensor updated) {
        for (NXTSensorListener current : listenerList) {
            current.NXTSensorUpdate(updated, updated.getPort().readRawValue(), updated.getCommonValue(), updated.getCommonValueAsString());
        }
        //System.out.println("Sensor Update");
    }

    //Trying a nonsynchronized version of this method...
    //public synchronized void updateSensorTypes(Sensor S1, Sensor S2, Sensor S3, Sensor S4) {
    public void updateSensorTypes(Sensor S1, Sensor S2, Sensor S3, Sensor S4) {
        this.S1 = S1;
        this.S2 = S2;
        this.S3 = S3;
        this.S4 = S4;
        //System.out.println(S1 + "  " + S2 + "  " + S3 + "  " + S4);
    }
    
    public boolean isRunning() {
        return running;
    }

    public interface NXTSensorListener {

        public void NXTSensorUpdate(Sensor updated, int newRawData, int newData, String newStringData);
    }
}
