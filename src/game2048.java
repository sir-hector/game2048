import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class game2048 extends JPanel {

    enum state {
        start, won, running, over
    }

    final static int target = 2048;
    static int highest;
    static int score;

    private Random rand = new Random();


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
    public void startGame(){

    }
}
