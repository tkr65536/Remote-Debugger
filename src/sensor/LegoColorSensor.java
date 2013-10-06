/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import main.Constants;
import main.TypeConverter;

/**
 *
 * @author Teddy Reiss
 */
public class LegoColorSensor extends ColorSensor implements Sensor {

    /**
     * String constant for type conversion
     *
     * @see lejos.nxt.ColorSensor
     */
    public final static String NAME = "LEGO Color Sensor";
    public final static int COLOR_RED = 259;
    public final static int COLOR_BLUE = 260;
    public final static int COLOR_GREEN = 261;
    public final static int COLOR_NONE = 262;

    public LegoColorSensor(SensorPort port, int color) {
        super(port, color);
        this.port = port;
    }

    public LegoColorSensor(SensorPort port) {
        super(port);
        this.port = port;
    }

    @Override
    //@TODO Replace with a color to string converter!!!!
    public String getCommonValueAsString() {
        return TypeConverter.convertColorToString(getCommonValue());
    }

    @Override
    public int getCommonValue() {
        return getColorID();
    }

    @Override
    public ArrayList<String> getModes() {
        ArrayList<String> modeList = new ArrayList<String>();
        modeList.add(Constants.COLOR_RED);
        modeList.add(Constants.COLOR_BLUE);
        modeList.add(Constants.COLOR_GREEN);
        modeList.add(Constants.COLOR_NONE);
        return modeList;
    }

    @Override
    public void setNewMode(int mode) {
        switch (mode) {
            case COLOR_RED:
                setFloodlight(Color.RED);
                break;
            case COLOR_BLUE:
                setFloodlight(Color.BLUE);
                break;
            case COLOR_GREEN:
                setFloodlight(Color.GREEN);
                break;
            case COLOR_NONE:
                setFloodlight(false);
                setFloodlight(Color.NONE);
                break;
            default:
                break;
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
