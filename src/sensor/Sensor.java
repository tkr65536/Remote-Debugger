/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.List;
import lejos.nxt.SensorPort;

/**
 *
 * @author Teddy Reiss
 */
public interface Sensor {
    /**
     * Returns the most commonly used value of the sensor as a string
     * Recommended Implementation: <code>return getCommonValue() + "";</code>
     * @return most commonly used value
     */
    public String getCommonValueAsString();
    /**
     * Returns the most commonly used value of the sensor as an int (most sensors can return data as ints, if they can't, return -1, 255 or throw an UnsupportedOperationExceptiopn)
     * @return most commonly used value
     */
    public int getCommonValue();
    
    /**
     * 
     * @return an ArrayList of all the possible modes for this sensor or an empty ArrayList
     */
    public List<String> getModes();
    
    /**
     * Sets the mode of the sensor
     * can be specific depending on the type of sensor (ie. doesn't have to call setMode())
     * @param mode 
     */
    public void setNewMode(int mode);
    
    /**
     * @return returns the port assosciated with this sensor.
     */
    public SensorPort getPort();
    
    /**
     * @return the name to be displayed
     */
    public String getName();
    
    @Override
    public String toString();
}
