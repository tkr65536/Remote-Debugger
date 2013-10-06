/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import main.Constants;

/**
 *
 * @author Teddy Reiss
 */
public class LegoSoundSensor extends SoundSensor implements Sensor {
    /**
     * String constant for type conversion
     * @see lejos.nxt.UltrasonicSensor
     */
    public final static String NAME = "LEGO Sound Sensor";
    public final static int DB = 257;
    public final static int DBA = 258;
    private SensorPort port;

    public LegoSoundSensor(SensorPort port, boolean dba) {
        super(port, dba);
        this.port = port;
    }

    public LegoSoundSensor(SensorPort port) {
        super(port);
        this.port = port;
    }

    @Override
    public String getCommonValueAsString() {
        return getCommonValue() + "%";
    }

    @Override
    public int getCommonValue() {
        return readValue();
    }

    @Override
    public ArrayList<String> getModes() {
        ArrayList<String> modeList = new ArrayList<String>();
        modeList.add(Constants.DB);
        modeList.add(Constants.DBA);
        return modeList;
    }

    @Override
    public void setNewMode(int mode) {
        if (mode == DB) {
            setDBA(false);
        } else {
            setDBA(true);
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
