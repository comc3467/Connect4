package com.company;

import javax.swing.*;

// The announcer pattern implemented to provide information about player information.
public class Announcer extends JTextField {
    private static JTextField statsField_;

    private int turn;
    private int turns_until_revert;
    private String revert_player;
    private int reverts_left_red;
    private int reverts_left_yellow;

    Announcer(JTextField statsField){
        turn = 1;
        turns_until_revert = 0;
        revert_player = "Either"; //anyone can revert to begin with
        reverts_left_red = 3;
        reverts_left_yellow = 3;
        statsField_ = statsField;
        statsField.setEditable(false);
        statsField_.setText("Turn: " + turn
            + "    Turns until revert: " + turns_until_revert
            + "    Player who can revert next: " + revert_player
            + "    Reverts left for Yellow: " + reverts_left_yellow
            + "    Reverts left for Red: " + reverts_left_red);
    }
    public void announce(String message){
        if(message == "drop"){
            turn += 1;
            if(turns_until_revert > 0){
                turns_until_revert --;
            }
            statsField_.setText("Turn: " + turn
                    + "    Turns until revert: " + turns_until_revert
                    + "    Player who can revert next: " + revert_player
                    + "    Reverts left for Yellow: " + reverts_left_yellow
                    + "    Reverts left for Red: " + reverts_left_red);
        }
        else if(message == "undo"){
            turn -= 2; //since it only goes down by 2, we know yellow will be on the odds, red on the evens for turn numbers
            turns_until_revert = 3;
            if(turn % 2 == 1){ //meaning it was yellow's revert
                reverts_left_yellow -= 1;
                revert_player = "Red"; //red is next
            }
            else{ //meaning it was red's turn
                reverts_left_red -= 1;
                revert_player = "Yellow"; //yellow is next
            }
            statsField_.setText("Turn: " + turn
                    + "    Turns until revert: " + turns_until_revert
                    + "    Player who can revert next: " + revert_player
                    + "    Reverts left for Yellow: " + reverts_left_yellow
                    + "    Reverts left for Red: " + reverts_left_red);
        }
    }

}
