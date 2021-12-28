package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Caretaker {

    private ArrayList<Memento> log;
    private int current_state = -1; //-1 until there is a memento in the history

    public Caretaker(){
        this.log = new ArrayList<>();
    }

    public void addMemento(Memento m){
        this.log.add(m);
        this.current_state = this.log.size() -1; //-1 because we're it starts at index zero for size 1
        System.out.println(current_state);
    }
    public Memento getMemento(int index){
        return this.log.get(index);
    }

    public Memento undo(){
        System.out.println("Undo state");
        if(this.current_state > 0){
            current_state -= 1; ///traveling back in time
            Memento temp = getMemento(current_state);
            this.log.remove(log.size()-1);
            return temp;
        }
        else{
            return null;
        }
    }

    public Memento redo(){
        System.out.println("Redo state");
        if(this.current_state > this.log.size() -1){
            current_state = this.log.size() -1;
        }
        else{
            this.current_state += 1;
        }
        return getMemento(this.current_state);
    }
}
