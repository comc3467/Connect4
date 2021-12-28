package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board_Big extends Board {
    Board_Big(Point p1_, Point p2_, JTextField statsField) {
        p1 = p1_;
        p2 = p2_;
        gameDone = false;

        rows = 7;
        columns = 8;

        board = new Color[columns][rows];
        for (Color[] colors : board) {
            Arrays.fill(colors, Color.WHITE);
        }
        players = new Color[]{Color.YELLOW, Color.RED};
        turn = 0;

        WIDTH = 900;
        widthUnit = WIDTH / (columns + 2); //how wide an individual oval/spot is

        HEIGHT = 495;
        heightUnit = HEIGHT / (rows + 2); //how tall an individual oval/spot is

        this.originator = new Originator(this);
        this.caretaker = new Caretaker();
//        this.originator_2 = new Originator(this);
//        this.caretaker_2 = new Caretaker();

        originator.setState(this.board);
        caretaker.addMemento(originator.save()); //just in case we want the first state

        //ANNOUNCER / OBSERVER PATTERN //

        this.addListener(statsField);

        ////
    }
}
