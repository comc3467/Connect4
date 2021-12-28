package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class settingsWindow {

    private JPanel menuPanel;
    private JFrame frame;


    private JPanel settingsPanel;

    // Singleton implementation of settings menu to prevent disruption of game after it has started.
    private static settingsWindow instance = new settingsWindow();


    private JRadioButton connect3RadioButton;
    private JRadioButton connect4RadioButton;
    private JRadioButton connect5RadioButton;

    public boolean connect3RadioButtonBool= false;
    public boolean connect4RadioButtonBool= true;
    public boolean connect5RadioButtonBool= false;

    private JRadioButton greenRadioButton;
    private JRadioButton blueRadioButton;
    private JRadioButton orangeRadioButton;
    private JRadioButton magentaRadioButton;
    private JRadioButton whiteRadioButton;

    private JButton returnToMenuButton;

    public boolean greenRadioButtonBool = false;
    public boolean blueRadioButtonBool = false;
    public boolean orangeRadioButtonBool= false;
    public boolean magentaRadioButtonBool = false;
    public boolean whiteRadioButtonBool= true;

    public static settingsWindow getInstance(){
        return instance;
    }

    public JPanel getPanel(){
        return settingsPanel;
    }

    public void setMenuPanelAndFrame(JPanel panel, JFrame frame){
        this.menuPanel = panel;
        this.frame = frame;
    }

    public Color get_color() {
        if(greenRadioButtonBool){ return Color.GREEN; }
        else if(blueRadioButtonBool){ return Color.BLUE; }
        else if(orangeRadioButtonBool){ return Color.ORANGE; }
        else if(magentaRadioButtonBool){ return Color.MAGENTA; }
        else if(whiteRadioButtonBool){ return Color.WHITE; }
        else{ return Color.CYAN; }
    }


    private settingsWindow(){




        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setContentPane(menuPanel);
                frame.revalidate();
                frame.repaint();

            }


        });

        greenRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenRadioButtonBool = true;
                blueRadioButtonBool = false;
                orangeRadioButtonBool= false;
                magentaRadioButtonBool= false;
                whiteRadioButtonBool= false;
            }
        });

        blueRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenRadioButtonBool = false;
                blueRadioButtonBool = true;
                orangeRadioButtonBool= false;
                magentaRadioButtonBool= false;
                whiteRadioButtonBool= false;
            }
        });

        orangeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenRadioButtonBool = false;
                blueRadioButtonBool = false;
                orangeRadioButtonBool= true;
                magentaRadioButtonBool= false;
                whiteRadioButtonBool= false;
            }
        });

        magentaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenRadioButtonBool = false;
                blueRadioButtonBool = false;
                orangeRadioButtonBool= false;
                magentaRadioButtonBool= true;
                whiteRadioButtonBool= false;
            }
        });

        whiteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenRadioButtonBool = false;
                blueRadioButtonBool = false;
                orangeRadioButtonBool= false;
                magentaRadioButtonBool= false;
                whiteRadioButtonBool= true;
            }
        });
    }
}


