/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import main.Constants;
import main.TypeConverter;

/**
 *
 * @author Teddy Reiss
 */
public class LegoUltrasonicSensor extends UltrasonicSensor implements Sensor {
    /**
     * String constant for type conversion
     * @see lejos.nxt.SoundSensor
     */
    public final static String NAME = "LEGO Ultrasonic Sensor";
    private SensorPort port;

    public LegoUltrasonicSensor(SensorPort port) {
        super(port);
        this.port = port;
    }

    @Override
    public String getCommonValueAsString() {
        return getCommonValue() + "";
    }

    @Override
    public int getCommonValue() {
        return getDistance();
    }

    @Override
    public ArrayList<String> getModes() {
        ArrayList<String> modeList = new ArrayList<String>();
        modeList.add(TypeConverter.findNameFromMode((int) UltrasonicSensor.MODE_CAPTURE));
        modeList.add(TypeConverter.findNameFromMode((int) UltrasonicSensor.MODE_CONTINUOUS));
        modeList.add(TypeConverter.findNameFromMode((int) UltrasonicSensor.MODE_OFF));
        modeList.add(TypeConverter.findNameFromMode((int) UltrasonicSensor.MODE_PING));
        modeList.add(TypeConverter.findNameFromMode((int) UltrasonicSensor.MODE_RESET));
        return modeList;
    }

    @Override
    public void setNewMode(int mode) {
        setMode(mode);
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