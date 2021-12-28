package com.company;

import com.company.Board;
import com.company.Connect4;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
// Factory Pattern Implemented
public class boardFactory {

    //use getShape method to get object of type shape
    public Board getGame(String boardType, Point p1, Point p2, JTextField statsField){
        if(boardType == "big"){
            return new Board_Big(p1,p2, statsField);
        }
        else if(boardType == "medium"){
            return new Board_Medium(p1,p2, statsField);
        }
        else if(boardType == "small"){
            return new Board_Small(p1,p2, statsField);
        }

        return null;
    }
}


