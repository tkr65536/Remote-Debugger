package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.remote.RemoteMotor;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommandConnector;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;
import sensor.Sensor;
//

/**
 * 
 * @author Teddy Reiss
 */
public class UI {

    public final static String DEFAULT_MODE_BOX = "Modes";
    public final static String DEFAULT_SENSOR_BOX = "Sensor Port";
    private JTable motorTable;
    //private JTable setMotorTable;
    private JTable sensorTable;
    private JComboBox sensorBox;//JComboBox not generic in 1.6
    private JComboBox typeBox;//JComboBox not generic in 1.6
    private JComboBox modeBox;//JComboBox not generic in 1.6
    private JButton updateSensors;
    private RobotTableModel sensorModel;
    private RobotTableModel motorModel;
    private Sensor S1;
    private Sensor S2;
    private Sensor S3;
    private Sensor S4;
    private NXTSensorMonitor monitor;

    public UI() {
        NXTConnector connector = new NXTConnector();
        NXTInfo[] info = connector.search(null, null, NXTCommFactory.ALL_PROTOCOLS);
        connector.connectTo(info[0], NXTComm.LCP);
        NXTCommandConnector.setNXTCommand(new SynchronizedNXTCommand(connector.getNXTComm()));
        //Main Window Setup
        JFrame frame = new JFrame("Remote Debugger");

        frame.getContentPane().add(setUpWindow(), BorderLayout.CENTER);

        //Sensor Stuff
        addInitialSensorDataToWindow();
        monitor = new NXTSensorMonitor(S1, S2, S3, S4);
        SensorUpdateListener sensorListener = new SensorUpdateListener();
        monitor.addNXTListener(sensorListener);
        //End sensor stuff

        //Motor Stuff
        addMotorDataToWindow();
        NXTMotorMonitor motorMonitor = new NXTMotorMonitor();
        MotorListener motorListener = new MotorListener();
        motorMonitor.addListener(motorListener, NXTMotorMonitor.ALL_EVENT);
        Thread motorThread = new Thread(motorMonitor);
        motorThread.setName("Motor Monitor Thread");
        motorThread.start();
        System.out.println("Motor Monitor running");

        //Frame stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 820);//Aproximately the right size.
        frame.setVisible(true);
    }

    private JPanel setUpWindow() {
        int gridRow = 0;
        JPanel upperPanel = new JPanel(new GridBagLayout());
        //Upper Panel (communication) setup
        JLabel upperHeader = new JLabel("NXT Info");

        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(upperHeader, constr);

        //Discovery setup
        JLabel discoveryHeader = new JLabel("Discovered NXT Bricks - Not yet implemented");
        JTable table = new JTable();
        JButton search = new JButton("Search");
        JButton connect = new JButton("Connect");

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(discoveryHeader, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        constr.gridheight = 3;
        upperPanel.add(table, constr);

        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(search, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(connect, constr);

        //Rename Setup
        JLabel renameHeader = new JLabel("Rename NXT Brick - Not yet implemented");
        JLabel newName = new JLabel("New Name");
        JTextField nameInput = new JTextField("");
        JButton renameButton = new JButton("Rename");

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(renameHeader, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(newName, constr);

        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(nameInput, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(renameButton, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        constr.gridwidth = 2;
        constr.insets = new Insets(5, 5, 5, 5);
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constr);

        //Motor Panel Setup
        JLabel motorHeader = new JLabel("Motors (push enter to apply edits)");

        motorTable = new JTable();
        motorTable.setFillsViewportHeight(true);
        motorTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane motorScroller = new JScrollPane(motorTable);


        /*JLabel setValues = new JLabel("Set Motor Values - Not yet implemented");
         setMotorTable = new JTable();
         setMotorTable.setFillsViewportHeight(true);
         JScrollPane setScroller = new JScrollPane(setMotorTable);
         */

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(motorHeader, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.BOTH;
        constr.weighty = 0.25;
        upperPanel.add(motorScroller, constr);

        /*gridRow++;
         constr = new GridBagConstraints();
         constr.gridx = 0;
         constr.gridy = gridRow;
         upperPanel.add(setValues, constr);

         gridRow++;
         constr = new GridBagConstraints();
         constr.gridx = 0;
         constr.gridy = gridRow;
         constr.fill = GridBagConstraints.BOTH;
         constr.weighty = 0.25;
         constr.weightx = 0.25;
         constr.insets = new Insets(0, 5, 0, 0);
         upperPanel.add(setScroller, constr);*/

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        constr.gridwidth = 2;
        constr.insets = new Insets(5, 5, 5, 5);
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constr);

        //Sensor Panel setup
        JLabel sensorHeader = new JLabel("Sensors");
        sensorTable = new JTable();
        JScrollPane sensorScroller = new JScrollPane(sensorTable);
        JLabel changeLabel = new JLabel("Set and Change Sensor Type or the Mode");
        sensorBox = new JComboBox<String>();
        typeBox = new JComboBox<String>();
        modeBox = new JComboBox<String>();
        updateSensors = new JButton("Update");

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        upperPanel.add(sensorHeader, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = gridRow;
        constr.gridheight = 5;
        constr.fill = GridBagConstraints.BOTH;
        constr.weighty = 0.25;
        constr.weightx = 0.75;
        upperPanel.add(sensorScroller, constr);

        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        upperPanel.add(changeLabel, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(sensorBox, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(typeBox, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(modeBox, constr);

        gridRow++;
        constr = new GridBagConstraints();
        constr.gridx = 1;
        constr.gridy = gridRow;
        constr.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(updateSensors, constr);

        return upperPanel;
    }

    //Sensor helper methods and classes
    /*private Object[] getSensorInfo(SensorPort sensorPort, String sensorPortName, Sensor sensor) {
        //System.out.println(sensor.getType());
        //System.out.println(sensor.getId());//Prints out sensorPort
        //System.out.println(sensor.getMode());
        return getSensorInfo(sensor, sensorPortName, sensorPort.readRawValue(), sensor.getCommonValueAsString());
    }*/

    //Sensor Stuff
    private Object[] getSensorInfo(Sensor sensor, String portName, int rawValue, String commonValue) {
        return new Object[]{portName, sensor.getClass().getSimpleName(), rawValue, commonValue};
    }

    private void addInitialSensorDataToWindow() {
        boolean[] sensorNonEdits = new boolean[]{false, false, false, false, false};
        String[] sensorColumnNames = new String[]{"Port", "Type", "Raw Value", "\"Proper\" Value"};
        sensorModel = new RobotTableModel(sensorNonEdits, sensorColumnNames, 0);
        sensorTable.setModel(sensorModel);
        sensorModel.addRow(new Object[]{"", "", "", ""});
        sensorModel.addRow(new Object[]{"", "", "", ""});
        sensorModel.addRow(new Object[]{"", "", "", ""});
        sensorModel.addRow(new Object[]{"", "", "", ""});
        
        sensorTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sensorTable.invalidate();
        
        setUpSensorComboBoxes();
    }

    private void setUpSensorComboBoxes() {
        sensorBox.addItem(DEFAULT_SENSOR_BOX);
        sensorBox.addItem(Constants.S1);
        sensorBox.addItem(Constants.S2);
        sensorBox.addItem(Constants.S3);
        sensorBox.addItem(Constants.S4);

        typeBox.addItem("Sensor Type");
        for (String current : Constants.getSensorTypes()) {
            typeBox.addItem(current);
        }

        modeBox.addItem(DEFAULT_MODE_BOX);
        typeBox.addActionListener(new ModeBoxUpdater());
        updateSensors.addActionListener(new SensorComboBoxListener());
    }
    private synchronized void updateSensor(Sensor toUpdate) {
        updateSensor(toUpdate, toUpdate.getPort().readRawValue(), toUpdate.getCommonValueAsString());
    }
    private synchronized void updateSensor(Sensor toUpdate, int rawValue, String commonValue) {
        SensorPort port = toUpdate.getPort();
        int currentRow;
        if (port.equals(SensorPort.S1)) {
            currentRow = 0;
            if (sensorModel.getRowCount() >= currentRow) {
                sensorModel.removeRow(currentRow);
            }
            sensorModel.insertRow(currentRow, getSensorInfo(toUpdate,TypeConverter.identifySensorPortName(port), rawValue, commonValue));
        } else if (port.equals(SensorPort.S2)) {
            currentRow = 1;
            if (sensorModel.getRowCount() >= currentRow) {
                sensorModel.removeRow(currentRow);
            }
            sensorModel.insertRow(currentRow, getSensorInfo(toUpdate,TypeConverter.identifySensorPortName(port), rawValue, commonValue));
        } else if (port.equals(SensorPort.S3)) {
            currentRow = 2;
            if (sensorModel.getRowCount() >= currentRow) {
                sensorModel.removeRow(currentRow);
            }
            sensorModel.insertRow(currentRow, getSensorInfo(toUpdate,TypeConverter.identifySensorPortName(port), rawValue, commonValue));
        } else if (port.equals(SensorPort.S4)) {
            currentRow = 3;
            if (sensorModel.getRowCount() >= currentRow) {
                sensorModel.removeRow(currentRow);
            }
            sensorModel.insertRow(currentRow, getSensorInfo(toUpdate,TypeConverter.identifySensorPortName(port), rawValue, commonValue));
        }
    }

    private class SensorComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedSensorPort = (String) sensorBox.getSelectedItem();
            SensorPort selectedPort = TypeConverter.identifySensorPortFromName(selectedSensorPort);
            String selectedType = (String) typeBox.getSelectedItem();
            Sensor selectedSensor = TypeConverter.identifySensorTypeFromName(selectedType, selectedPort);
            //int currentRow = 0;

            String selectedMode = (String) modeBox.getSelectedItem();
            System.out.println(selectedMode);
            int mode = TypeConverter.findModeFromString(selectedMode);
            if (mode != TypeConverter.NO_MODE_PASSED) {
                selectedSensor.setNewMode(mode);
            }
            //moved from after the big comment in this method
            updateSensor(selectedSensor);
            if (selectedSensor.getPort().equals(SensorPort.S1)) {
                S1 = selectedSensor;
            } else if (selectedSensor.getPort().equals(SensorPort.S2)) {
                S2 = selectedSensor;
            } else if (selectedSensor.getPort().equals(SensorPort.S3)) {
                S3 = selectedSensor;
            } else if (selectedSensor.getPort().equals(SensorPort.S4)) {
                S4 = selectedSensor;
            }
            // }

            System.out.println(S1);
            System.out.println(S2);
            System.out.println(S3);
            System.out.println(S4);
            monitor.updateSensorTypes(S1, S2, S3, S4);

            if (!monitor.isRunning()) {
                Thread sensorMonitorThread = new Thread(monitor);
                sensorMonitorThread.setName("Sensor Monitor");
                sensorMonitorThread.start();
                System.out.println("Sensor Monitor Running");
            }

            //if (selectedSensor != null) {
                /*if (selectedPort.equals(SensorPort.S1)) {
             currentRow = 0;
             S1 = selectedSensor;
             if (sensorModel.getRowCount() >= currentRow) {
             sensorModel.removeRow(currentRow);
             }
             sensorModel.insertRow(currentRow, getSensorInfo(selectedPort, selectedSensorPort, S1));
             } else if (selectedPort.equals(SensorPort.S2)) {
             currentRow = 1;
             S2 = selectedSensor;
             if (sensorModel.getRowCount() >= currentRow) {
             sensorModel.removeRow(currentRow);
             }
             sensorModel.insertRow(currentRow, getSensorInfo(selectedPort, selectedSensorPort, S2));
             } else if (selectedPort.equals(SensorPort.S3)) {
             currentRow = 2;
             S3 = selectedSensor;
             if (sensorModel.getRowCount() >= currentRow) {
             sensorModel.removeRow(currentRow);
             }
             sensorModel.insertRow(currentRow, getSensorInfo(selectedPort, selectedSensorPort, S3));
             } else if (selectedPort.equals(SensorPort.S4)) {
             currentRow = 3;
             S4 = selectedSensor;
             if (sensorModel.getRowCount() >= currentRow) {
             sensorModel.removeRow(currentRow);
             }
             sensorModel.insertRow(currentRow, getSensorInfo(selectedPort, selectedSensorPort, S4));
             }*/
        }
    }

    private class ModeBoxUpdater implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modeBox.removeAllItems();
            modeBox.addItem(DEFAULT_MODE_BOX);
            String selectedSensor = (String) typeBox.getSelectedItem();
            String selectedPort = (String) sensorBox.getSelectedItem();
            SensorPort port = TypeConverter.identifySensorPortFromName(selectedPort);
            Sensor selected = TypeConverter.identifySensorTypeFromName(selectedSensor, port);
            List<String> modeList = selected.getModes();
            for (String mode : modeList) {
                modeBox.addItem(mode);
            }
        }
    }

    private class SensorUpdateListener implements NXTSensorMonitor.NXTSensorListener {

        UIUpdater[] updaters = new UIUpdater[4];

        @Override
        public void NXTSensorUpdate(Sensor updated, int rawData, int newData, String newStringData) {
            UIUpdater updater;
            if (updaters[updated.getPort().getId()] != null) {
                updater = updaters[updated.getPort().getId()];
            } else {
                updater = new UIUpdater(updated);
            }
            updater.setData(rawData, newData, newStringData);

            try {
                    SwingUtilities.invokeAndWait(updater);
                    //SwingUtilities.invokeLater(updater);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class UIUpdater implements Runnable {

        private Sensor updated;
        private int rawData = 0;
        private int newData = 0;
        private String stringData = "";

        private UIUpdater(Sensor updated) {

            this.updated = updated;
        }
        
        public void setData(int raw, int newData, String newStringData) {
        rawData = raw;
        this.newData = newData;
        stringData = newStringData;
    }

        @Override
        public void run() {
            System.out.println(SwingUtilities.isEventDispatchThread());
            updateSensor(updated, rawData, stringData);
            System.out.println("Sensor value updated");
            /*if (updated.equals(S1)) {
             sensorModel.removeRow(0);
             sensorModel.insertRow(0, getSensorInfo(port, portName, updated));
             } else if (updated.equals(S2)) {
             sensorModel.removeRow(1);
             sensorModel.insertRow(1, getSensorInfo(port, portName, updated));
             } else if (updated.equals(S3)) {
             sensorModel.removeRow(2);
             sensorModel.insertRow(2, getSensorInfo(port, portName, updated));
             } else if (updated.equals(S4)) {
             sensorModel.removeRow(3);
             sensorModel.insertRow(3, getSensorInfo(port, portName, updated));
             }*/
        }
    }
    //End Sensor Helper Methods and classes

    //Motor Stuff
    private void addMotorDataToWindow() {
        boolean[] motorNonEdits = new boolean[]{false, false, true, true, false};
        String[] motorColumnNames = new String[]{"Port", "Type", Constants.MOTOR_SPEED, Constants.MOTOR_POWER, Constants.MOTOR_ENCODER};
        motorModel = new RobotTableModel(motorNonEdits, motorColumnNames, 0);
        motorTable.setModel(motorModel);
        motorModel.addRow(getMotorInfo(Motor.A, "A", "NXT Motor"));
        motorModel.addRow(getMotorInfo(Motor.B, "B", "NXT Motor"));
        motorModel.addRow(getMotorInfo(Motor.C, "C", "NXT Motor"));
        motorModel.addTableModelListener(new MotorEditTableListener());
    }

    private Object[] getMotorInfo(RemoteMotor motor, String motorPort, String type) {
        //return new Object[]{motorPort, type, motor.getSpeed(), motor.getPower(), motor.getTachoCount()};
        return getMotorInfo(motorPort, type, motor.getSpeed(), motor.getPower(), motor.getTachoCount());
    }
    
    private Object[] getMotorInfo(String motorPort, String type, int speed, int power, int tachoCount) {
        return new Object[] {motorPort, type, speed, power, tachoCount};
    }

    private class MotorEditTableListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            int motor = e.getFirstRow();//in theory, listener only runs when one row is changed
            int edited = e.getColumn();//which item was edited (speed? power?)
            RobotTableModel source = motorModel;
            RemoteMotor selectedMotor = TypeConverter.getMotorFromPortName((String) (source.getValueAt(motor, 0)));
            if (source.getColumnName(edited).equalsIgnoreCase(Constants.MOTOR_POWER)) {
                int power = (Integer) (source.getValueAt(motor, edited));
                selectedMotor.setPower(Math.abs(power));
                if (power > 0) {
                    selectedMotor.forward();
                } else if (power < 0) {
                    selectedMotor.backward();
                } else {
                    selectedMotor.flt(true);
                }
            } else if (source.getColumnName(edited).equalsIgnoreCase(Constants.MOTOR_SPEED)) {
                int speed = ((Number)(source.getValueAt(motor, edited))).intValue();
                selectedMotor.setSpeed(Math.abs(speed));
                if (speed > 0) {
                    selectedMotor.forward();
                } else if (speed < 0) {
                    selectedMotor.backward();
                } else {
                    selectedMotor.flt(true);
                }
            }
        }
    }

    private class MotorListener implements NXTMotorMonitor.NXTMotorListener {

        @Override
        public void NXTMotorUpdate(String eventType, int eventData, RemoteMotor motor) {
            if (eventType.equalsIgnoreCase(NXTMotorMonitor.ENCODER_EVENT)) {
                int col = motorModel.findColumn(Constants.MOTOR_ENCODER);
                motorModel.setValueAt(eventData, TypeConverter.getRowFromMotorPort(motor.getId()), col);
                //System.out.println("Event Data" + eventData);
            }
        }
    }
}