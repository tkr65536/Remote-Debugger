package main;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * OldUI and Communication Handler with the Brick
 *
 * @author Teddy Reiss
 */
public class OldUI {

    //private Brick brick;

    public OldUI(String deviceName) {
        //brick = new Brick(deviceName);

        //UI Setup
        JFrame frame = new JFrame();

        //Vertical layout
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        JPanel middlePanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //Upper Panel (communication) setup
        JLabel upperHeader = new JLabel("NXT Info");
        //row, col, hgap, vgap
        JPanel discoveryPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel renamePanel = new JPanel(new GridLayout(3, 2, 10, 10));

        
        //Discovery setup
        JLabel discoveryHeader = new JLabel("Discovered NXT Bricks");
        JPanel placeHolder = new JPanel();
        JTable table = new JTable();
        JButton search = new JButton("search");
        JButton connect = new JButton("connect");
        
        discoveryPanel.add(discoveryHeader);
        discoveryPanel.add(placeHolder);
        discoveryPanel.add(table);
        discoveryPanel.add(search);
        placeHolder = new JPanel();
        discoveryPanel.add(placeHolder);
        discoveryPanel.add(connect);
        
        
        //Rename Panel Setup
        JLabel renameHeader = new JLabel("Rename NXT Brick");
        JLabel newName = new JLabel("New Name");
        JTextField nameInput = new JTextField("");
        JButton renameButton = new JButton("Rename");
        
        renamePanel.add(renameHeader);
        placeHolder = new JPanel();
        renamePanel.add(placeHolder);
        renamePanel.add(newName);
        renamePanel.add(nameInput);
        placeHolder = new JPanel();
        renamePanel.add(placeHolder);
        renamePanel.add(renameButton);
        

        upperPanel.add(upperHeader);
        upperPanel.add(discoveryPanel);
        upperPanel.add(renamePanel);
        frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
        frame.getContentPane().add(middlePanel, BorderLayout.CENTER);
        frame.getContentPane().add(lowerPanel, BorderLayout.SOUTH);

        frame.setSize(428, 820);
        frame.setVisible(true);
    }
}
