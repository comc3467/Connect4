package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameForm {
    private JButton button1;
    public JPanel gamePanel;
    private JPanel gameWindow;


    public gameForm(Connect4 instance) {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "We got em boys");
            }
        });
        gamePanel.add(instance);
    }
}
