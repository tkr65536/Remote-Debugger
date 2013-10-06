/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 *
 * @author Teddy Reiss
 */
public class LegoTouchSensor extends TouchSensor implements Sensor {

    /**
     * String constant for type conversion
     *
     * @see lejos.nxt.TouchSensor
     */
    public final static String NAME = "LEGO Touch Sensor";
    private SensorPort port;

    public LegoTouchSensor(SensorPort port) {
        super(port);
        this.port = port;
    }

    @Override
    public String getCommonValueAsString() {
        if (isPressed()) {
            return "Pressed";
        } else {
            return "Not Pressed";
        }
    }

    @Override
    public int getCommonValue() {
        if (isPressed()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public ArrayList<String> getModes() {
        return new ArrayList<String>(0);
    }

    @Override
    public void setNewMode(int mode) {
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
