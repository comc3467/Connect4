package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Originator {
    private Color[][] previous_state_;
    private Board board_;

    public Originator(Board board){
        this.board_ = board;
        previous_state_ = new Color[board_.columns][board_.rows];
    }

    public void setState(Color[][] state){
        for(int i = 0; i < this.board_.rows; i++){
            for(int j = 0; j < this.board_.columns; j++){
                this.previous_state_[j][i] = state[j][i];
            }
        }
    }

    public Color[][] getState(){
        return this.previous_state_;
    }

    public Memento save(){
        return new Memento(this.previous_state_, this.board_);
    }

    public void restore(Memento m){
        this.previous_state_ = m.get_state();
    }
}
