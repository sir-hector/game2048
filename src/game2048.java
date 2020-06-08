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
        if(gamestate != State.running){
            score =0;
            highest =0;
            gamestate = State.running;
            tiles = new Tile[side][side];
            addRandomTile();
            addRandomTile();
        }
    }
    void drawGrid(Graphics2D g){

        g.setColor(new Color(0xBBA));
        g.fillRoundRect(200,100,499,15,15,15);

        if(gamestate == State.running){
            for(int r=0; r<side; r++){
                for(int c=0;c<side;c++){
                    if(tiles[r][c] == null){
                        g.setColor(new Color(0xBBA));
                        g.fillRoundRect(215 + c * 121,115+r*121, 106,106,7,7);
                    }
                    else{
                        drawTile(g,r,c);
                    }
                }
            }


        }

        else {
            g.setColor(new Color(0xBBA));
            g.fillRoundRect(215, 115, 469, 469, 7, 7);

            g.setColor(new Color(0xBBAa));
            g.setFont(new Font("Arial", Font.BOLD, 128));
            g.drawString("2048", 250, 270);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            if(gamestate == State.won){
                g.drawString("YOU ARE A WINNER", 390,350);
            } else if(gamestate == State.over){
                g.drawString("Gameover", 400,350);
            }
            g.drawString("click to start ", 330, 470);
            g.drawString("use arrow keys to move tiles ", 310, 530);
        }
    }
    public void addRandomTile(){
        int pos = rand.nextInt(side * side);
        System.out.println(pos);
        int row, col;
        do {
            pos =(pos +1) % (side * side);
            row = pos /side;
            col = pos % side;

        } while(tiles[row][col] != null);

        int val = rand.nextInt(10) == 0 ? 4 :2;
        tiles[row][col] = new Tile(val);
    };
    public void drawTile(Graphics2D g, int r, int c){
        int value = tiles[r][c].getValue();

        g.setColor(new Color(0xBBAaa7));
        g.fillRoundRect(215 + c*121, 115+r*121, 106,106,7,7);
        String s = String.valueOf(value);
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();

        int x = 215 +c *121+(106-fm.stringWidth(s))/2;
        int y = 115 +r *121+(asc+(106-(asc+dec))/2);
        g.setColor(new Color(0xBBA237));
        g.drawString(s, x,y);

    };
}
