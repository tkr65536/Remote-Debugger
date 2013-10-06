/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import main.Constants;

/**
 *
 * @author Teddy Reiss
 */
public class LegoLightSensor extends LightSensor implements Sensor {
    private static LegoLightSensor[] instances;

    /**
     * String constant for type conversion
     * @see lejos.nxt.LightSensor
     */
    public final static String NAME = "LEGO Light Sensor";
    
    public static final int FLOODLIGHT_ON = 255;
    public static final int FLOODLIGHT_OFF = 256;
    private SensorPort port;

    private LegoLightSensor(SensorPort port, boolean floodlight) {
            super(port, floodlight);
            this.port = port;
    }
    private LegoLightSensor(SensorPort port) {
        super(port);
        this.port = port;
    }
    public static LegoLightSensor getInstance(SensorPort port) {
        if (instances == null) {
            instances = new LegoLightSensor[4];//4 ports max
        }
        if (instances[port.getId()] == null) {
            instances[port.getId()] = new LegoLightSensor(port);
        }
        return instances[port.getId()];
    }
    @Override
    public String getCommonValueAsString() {
        return getCommonValue() + "";
    }

    @Override
    public int getCommonValue() {
        return getLightValue();
    }

    @Override
    public ArrayList<String> getModes() {
        ArrayList<String> modeList = new ArrayList<String>();
        modeList.add(Constants.FLOODLIGHT_ON);
        modeList.add(Constants.FLOODLIGHT_OFF);
        return modeList;
    }

    @Override
    public void setNewMode(int mode) {
        if (mode == FLOODLIGHT_ON) {
            setFloodlight(true);
        } else {
            setFloodlight(false);
        }
    }

    @Override
    public SensorPort getPort() {
        return port;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
