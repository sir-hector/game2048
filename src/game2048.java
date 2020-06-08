import model.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class game2048 extends JPanel {

    enum State {
        start, won, running, over
    }

    final static int target = 2048;
    static int highest;
    static int score;

    private Random rand = new Random();
    private Tile[][] tiles;
    private int side =4;
    private State gamestate = State.start;
    private boolean checkingAvaiableMoves;

    public game2048(){
        setPreferredSize(new Dimension(900, 700));
        setBackground(new Color(0XFAF8EF));
        setFont(new Font("SandSerif", Font.BOLD,48));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
                repaint();
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
    }
    public void paintComponent(Graphics g1){
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g);
    }
    public void startGame(){

    }
    void drawGrid(Graphics2D g){
        g.setColor(new Color(0xBBA));
        g.fillRoundRect(200,100,499,15,15,15);

        g.setColor(new Color(0xBBA));
        g.fillRoundRect(215,115,469,469,7,7);

        g.setColor(new Color(0xBBAa));
        g.setFont(new Font("Arial", Font.BOLD, 128));
        g.drawString("2048", 250,270);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("click to start ", 330,470);
        g.drawString("use arrow keys to move tiles ", 310,530);

    }
}
