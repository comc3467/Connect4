package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
// Memento pattern reverts to a former board state to undo a move

public class Memento {
    private Board board_;
    private Color[][] previous_state_;

    public Memento(Color[][] state, Board board){
        this.board_ = board;
        this.previous_state_ = new Color[board_.columns][board_.rows];
        for(int i = 0; i < this.board_.rows; i++){
            for(int j = 0; j < this.board_.columns; j++){
                this.previous_state_[j][i] = state[j][i];
            }
        }
    }

    public Color[][] get_state(){
        return this.previous_state_;
    }
    public void set_state(Color[][] state){
        for(int i = 0; i < this.board_.rows; i++){
            for(int j = 0; j < this.board_.columns; j++){
                this.previous_state_[j][i] = state[j][i];
            }
        }
    }
}
