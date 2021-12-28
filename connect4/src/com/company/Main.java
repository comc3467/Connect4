package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;




class Connect4 extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private static final int WIDTH, HEIGHT, widthUnit, heightUnit, boardLength, boardHeight;
    private static JPanel panel;
    private static Point p1, p2;
    private static Board board;

    private settingsWindow settings_instance = settingsWindow.getInstance();



    public Connect4(String board_size, JPanel gamePanel, JTextField statsField, Color background_color) {
//        this.frame = frame;
//        frame.setContentPane(gamePanel);
//        frame.add(this)
////        JFrame frame = new JFrame("gameForm");
////        frame.setContentPane(new gamePanel().gamePanel);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//        javax.swing.Timer timer = new javax.swing.Timer(10, this);
//        timer.start();
//
//        frame.addMouseListener(this);
//        frame.addMouseMotionListener(this);

//        setBackground(Color.WHITE);
        this.panel = gamePanel;


        //panel.add(this);
//        JFrame frame = new JFrame("gameForm");
//        frame.setContentPane(new gamePanel().gamePanel);

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
        javax.swing.Timer timer = new javax.swing.Timer(10, this);
        timer.start();

        this.panel.addMouseListener(this);
        this.panel.addMouseMotionListener(this);
        this.panel.setLayout(new BorderLayout(0,0));

        // USE OF THE SINGLETON PATTERN //

        this.setBackground(settings_instance.get_color());
        this.panel.add(this, BorderLayout.CENTER);
        this.panel.setVisible(true);

        board = new boardFactory().getGame(board_size, p1, p2, statsField);

        gamePanel.setSize(new Dimension(board.WIDTH,board.HEIGHT));
//        repaint();




    }

    static {
        int initialWidth = 900;
        int initialHeight = 500;
        boardLength = 7;
        boardHeight = 6;
        widthUnit = initialWidth / (boardLength + 2);
        WIDTH = widthUnit * (boardLength + 2);
        heightUnit = initialHeight / (boardHeight + 2);
        HEIGHT = heightUnit * (boardHeight + 2);
    }


    public void actionPerformed(ActionEvent e) {
//        System.out.println(board.turn);
        repaint();
    }

    public void paintComponent(Graphics g) {
//        System.out.println("paint component!");
        super.paintComponent(g);
        board.draw(g);
    }

    public void mouseMoved(MouseEvent e) {
        board.hover(e.getX());
    }

    public void mousePressed(MouseEvent e) {
        board.drop();
        System.out.println("Hello");
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    static class PointPair {
        public Point p1, p2;

        PointPair(int x1, int y1, int x2, int y2) {
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
        }
    }

    public void undo(){
        board.restore();
        System.out.println("Restoring...");
        repaint();
    }
}







