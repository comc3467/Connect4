package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow {

    private JPanel menuPanel;
    private JFrame frame;
    private JPanel gamePanel;
    private String gamesize;






    private JPanel gamepagePanel;

    private JButton undoMoveButton;
    private JButton returnToMenuButton;
    private JTextField statsField;

    private static Connect4 game_;
    private static Announcer announcer_;

    public JPanel getPanel(){
        return gamepagePanel;
    }



    public void setMenuPanelAndFrame(JPanel panel, JFrame frame){
        this.menuPanel = panel;
        this.frame = frame;
    }

    public void setSize(String size, Color background_color){
        gamesize = size;
        game_ = new Connect4(size, gamePanel, statsField, background_color);
        gamePanel = game_;

    }

    public gameWindow(){
        gamePanel = new Connect4("medium", gamePanel, statsField, Color.WHITE);

        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setContentPane(menuPanel);
                frame.revalidate();
                frame.pack();
                frame.repaint();

            }


        });


        undoMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game_.undo();
            }
        });
    }

}
