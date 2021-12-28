package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public abstract class Board{



    public Color[][] board;
    public Color[] players;

    public Point p1, p2;

    public int rows;
    public int columns;

    public int HEIGHT;
    public int WIDTH;

    public int heightUnit;
    public int widthUnit;

    public int turn;

    public int hoverX, hoverY;
    public boolean gameDone;

    public Originator originator;
    public Caretaker caretaker;
//    public Originator originator_2;
//    public Caretaker caretaker_2;

    private Announcer listener;

    public int turns_until_revert = 0; //originally anyone can revert
    public int next_to_revert = 2; //originally anyone can revert, this will be in the check to allow anyone
    public int reverts_left_0 = 3;
    public int reverts_left_1 = 3;


    public void addListener(JTextField statsField){
        this.listener = new Announcer(statsField);
    }
    public void tellListener(String message){
        this.listener.announce(message);
    }

    public void draw(Graphics g) { //gets called at the beginning and anytime that the user moves the mouse , also walked through //https://www.youtube.com/watch?v=8hvvyJPNaBE&t=402s for this but not the drop function
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)(g)).setStroke(new BasicStroke(2.0f));

        for (int i = widthUnit; i <= WIDTH - widthUnit; i += widthUnit) {
            g.setColor(Color.BLACK);
            g.drawLine(i, heightUnit, i, HEIGHT - heightUnit);
            if (i == WIDTH - widthUnit) continue;
            for (int j = heightUnit; j < HEIGHT - heightUnit; j += heightUnit) {
                g.setColor(board[i/widthUnit - 1][j/heightUnit - 1]);
                g.fillOval(i + 5, j + 5, widthUnit - 10, heightUnit - 10);
                g.setColor(Color.BLACK);
                g.drawOval(i + 5, j + 5, widthUnit - 10, heightUnit - 10);
            }
        }
        g.drawLine(widthUnit, HEIGHT - heightUnit, WIDTH - widthUnit, HEIGHT - heightUnit);

        g.setColor(gameDone ? Color.GREEN : players[turn]);
        g.fillOval(hoverX + 5, hoverY + 5, widthUnit - 10, heightUnit - 10);
        g.setColor(Color.BLACK);
        g.drawOval(hoverX + 5, hoverY + 5, widthUnit - 10, heightUnit - 10);

        g.setColor(Color.BLACK);
        if (p1 != null && p2 != null) {
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

    }
    public void hover(int x) {
        x -= x%widthUnit;
        if (x < widthUnit) x = widthUnit;
        if (x >= WIDTH - widthUnit) x = WIDTH - 2*widthUnit;
        hoverX = x;
        hoverY = 0;
    }
    public void drop() {
        if (board[hoverX/widthUnit - 1][0] != Color.WHITE) return;
        Color color = players[turn];
        int x = hoverX;
        int i;
        for (i = this.rows-1; i >= 0; i--) {
            if(board[x/widthUnit - 1][i] == Color.WHITE){ //first empty one
                board[x/widthUnit - 1][i] = color;
                break;
            }
        }
        checkConnect(x/widthUnit - 1, i);
        if (gameDone) return;

        ////MEMENTO DESIGN PATTERN IMPLEMENTATION////
        originator.setState(this.board);
        caretaker.addMemento(originator.save());

        ////
        turn = (turn + 1) % players.length;

        this.tellListener("drop");
        if(turns_until_revert > 0){
            turns_until_revert--;
        }


    }
    public void checkConnect(int x, int y) { //https://www.youtube.com/watch?v=8hvvyJPNaBE&t=402s , big hint for the groundwork for the project
        if (gameDone) return;

        Connect4.PointPair pair = search(board, x, y);

        if (pair != null) {
            p1 = new Point((pair.p1.x + 1) * widthUnit + widthUnit / 2, (pair.p1.y + 1) * heightUnit + heightUnit / 2);
            p2 = new Point((pair.p2.x + 1) * widthUnit + widthUnit / 2, (pair.p2.y + 1) * heightUnit + heightUnit / 2);
            //frame.removeMouseListener(new Connect4("small"));
            gameDone = true;
        }
    }
    public Connect4.PointPair search(Color[][] arr, int i, int j) { //https://www.youtube.com/watch?v=8hvvyJPNaBE&t=402s , big hint for the groundwork for the project
        Color color = arr[i][j];
        int left, right, up, down;

        // check horizontally left to right
        left = right = i;
        while (left >= 0 && arr[left][j] == color) left--;
        left++;
        while (right < arr.length && arr[right][j] == color) right++;
        right--;
        if (right - left >= 3) {
            return new Connect4.PointPair(left, j, right, j);
        }

        // check vertically top to bottom
        down = j;
        while (down < arr[i].length && arr[i][down] == color) down++;
        down--;
        if (down - j >= 3) {
            return new Connect4.PointPair(i, j, i, down);
        }

        // check diagonal top left to bottom right
        left = right = i;
        up = down = j;
        while (left >= 0 && up >= 0 && arr[left][up] == color) { left--; up--; }
        left++; up++;
        while (right < arr.length && down < arr[right].length && arr[right][down] == color) { right++; down++; }
        right--; down--;
        if (right - left >= 3 && down - up >= 3) {
            return new Connect4.PointPair(left, up, right, down);
        }

        // check diagonal top right to bottom left
        left = right = i;
        up = down = j;
        while (left >= 0 && down < arr[left].length && arr[left][down] == color) {left--; down++;}
        left++; down--;
        while (right < arr.length && up >= 0 && arr[right][up] == color) {right++; up--;}
        right--; up++;
        if (right - left >= 3 && down - up >= 3) {
            return new Connect4.PointPair(left, down, right, up);
        }
        return null;
    }



    public void restore(){
        if(this.next_to_revert == 0 & turn == 1){
            return;
        }
        else if(this.next_to_revert == 1 & turn == 0){
            return;
        }
        else if(this.turn == 0 & reverts_left_0 == 0){
            return;
        }
        else if(this.turn == 1 & reverts_left_1 == 0){
            return;
        }
        else if(this.turns_until_revert > 0){
            return;
        }//otherwise, we keep going, so if next_to_revert is 2 then anyone can, so we're chilling
        Color[][] temp_state;
        Memento temp = caretaker.undo();
        if(temp != null){
            temp = caretaker.undo();
            if(temp != null){
                originator.restore(temp);
                temp_state = originator.getState();
                for(int i = 0; i < this.rows; i++){
                    for(int j = 0; j < this.columns; j++){
                        this.board[j][i] = temp_state[j][i];
                    }
                }
                this.tellListener("undo");
                this.turns_until_revert = 3;
                if(this.turn == 0){
                    this.reverts_left_0 -= 1;
                }
                else{
                    this.reverts_left_1 -= 1;
                }
                this.next_to_revert = 1-turn; //so this will be 0 if the turn was 1, and 1 if the turn was 0
            }
            else{
                caretaker.redo();
                System.out.println("Not able to redo 2nd layer");
            }
        }
        else{
            caretaker.redo();
            System.out.println("Not able to redo 1st layer");
        }
    }
}


