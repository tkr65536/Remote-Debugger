/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import java.util.ArrayList;
import java.util.List;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.IRSeekerV2;

/**
 *
 * @author Teddy Reiss
 */
public class HTIRSeekerV2 extends IRSeekerV2 implements Sensor {
    public static final String NAME = "HiTechnic IR Seeker V2";
    private static final String AC = "AC";
    private static final String DC = "DC";
    private SensorPort irPort;
    
    public HTIRSeekerV2 (SensorPort port, IRSeekerV2.Mode mode) {
        super(port, mode);
        irPort = port;
    }
    
    public HTIRSeekerV2(SensorPort port) {
        this(port, IRSeekerV2.Mode.AC);
    }

    @Override
    public String getCommonValueAsString() {
        return "" + getCommonValue();
    }

    @Override
    public int getCommonValue() {
        return getDirection();
    }

    @Override
    public List<String> getModes() {
        ArrayList<String> returnable = new ArrayList<String>();
        returnable.add(AC);
        returnable.add(DC);
        return returnable;
    }

    @Override
    public void setNewMode(int mode) {
        System.err.println("You cannot change the mode of an IRSeeker while using it currently. Sorry.");
    }

    @Override
    public SensorPort getPort() {
        return irPort;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
