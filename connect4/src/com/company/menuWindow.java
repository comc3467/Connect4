package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class menuWindow {



    public Boolean SmallGameBool = false;
    public Boolean MediumGameBool = true; //starts off as true, so there's a default
    public Boolean BigGameBool = false;


    public JPanel menuPanel;
    public JButton newGameButton;
    private JButton exitButton;
    private JRadioButton mediumBoardRadioButton;
    private JRadioButton largeBoardRadioButton;
    private JRadioButton smallBoardRadioButton;


    private JButton smallStart;
    private JButton bigStart;
    private JButton mediumStart;
    private JPanel gamePanel;
    private JButton settingsButton;

    private JPanel gamesPanel;
    private static Connect4 game_instance;
    private static Point p1, p2;

    private JFrame frame;
    private settingsWindow settings_instance = settingsWindow.getInstance();
    private gameWindow gamepage_instance = new gameWindow();
    private JPanel settingsPanel = settings_instance.getPanel();
    private JPanel gamepagePanel = gamepage_instance.getPanel();



    private static menuWindow menu_instance;


    public static void main(String[] args){

        menu_instance = new menuWindow();




    }

    public menuWindow() {

        this.frame = new JFrame("Window");
        frame.setContentPane(this.menuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 480));
        frame.pack();
        frame.setVisible(true);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.setContentPane(gamePanel);
                settings_instance.setMenuPanelAndFrame(menuPanel, frame);
                frame.setContentPane(settingsPanel);
                frame.revalidate();
                frame.repaint();

            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamepage_instance.setMenuPanelAndFrame(menuPanel, frame);
                int width, height;
                if(SmallGameBool){
                    gamepage_instance.setSize("small", settings_instance.get_color());
                    width = 800;
                    height = 580;
                }
                else if(MediumGameBool){
                    gamepage_instance.setSize("medium", settings_instance.get_color());
                    width = 900;
                    height = 635;
                }
                else if(BigGameBool){
                    gamepage_instance.setSize("big", settings_instance.get_color());
                    width = 900;
                    height = 635;
                }
                else{ //we still do a medium size regardless
                    gamepage_instance.setSize("medium", settings_instance.get_color());
                    width = 900;
                    height = 635;
                }
                frame.setContentPane(gamepagePanel);
                frame.setSize(width,height);
                frame.revalidate();
                frame.repaint();

//                frame.setContentPane(gamePanel);
            }
        });

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });


        smallBoardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SmallGameBool = true;
                MediumGameBool = false;
                BigGameBool = false;
            }
        });
        mediumBoardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SmallGameBool = false;
                MediumGameBool = true;
                BigGameBool = false;
            }
        });
        largeBoardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SmallGameBool = false;
                MediumGameBool = false;
                BigGameBool = true;
            }
        });
    }
}

