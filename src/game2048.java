import model.tile.Tile;
import model.Colour;


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
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    goUp();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    goDown();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    goLeft();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    goRight();
                }
                repaint();

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

        g.setColor(Colour.getBoardColor(2));
        g.fillRoundRect(200,100,499,499,15,15);

        if(gamestate == State.running){
            for(int r=0; r<side; r++){
                for(int c=0;c<side;c++){
                    if(tiles[r][c] == null){
                        g.setColor(Colour.getBoardColor(4));
                        g.fillRoundRect(215 + c * 121,115+r*121, 106,106,7,7);
                    }
                    else{
                        drawTile(g,r,c);
                    }
                }
            }
        }

        else {
            g.setColor(Colour.getBoardColor(3));
            g.fillRoundRect(215, 115, 469, 469, 7, 7);
            g.setColor(Colour.getBoardColor(16));
            g.setFont(new Font("Arial", Font.BOLD, 128));
            g.drawString("2048", 310, 270);
            g.setFont(new Font("Arial", Font.BOLD, 20));

            if(gamestate == State.won){
                g.setColor(Colour.getTileColor(16));
                g.drawString("YOU ARE A WINNER", 400,350);
            } else if(gamestate == State.over){
                g.setColor(Colour.getTileColor(16));
                g.drawString("Gameover", 360,350);
            }

            g.setColor(Colour.getBoardColor(16));
            g.drawString("CLICK TO START ", 370, 470);
            g.setColor(Colour.getBoardColor(16));
            g.drawString("game: 2048 author: s20687", 10, 30);
            g.setColor(Colour.getBoardColor(16));
            g.drawString("USE ARROW KEYS TO MOVE TILES", 280, 530);
        }
    }
    public void addRandomTile(){
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos =(pos +1) % (side * side);
            row = pos /side;
            col = pos % side;

        } while(tiles[row][col] != null);

        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = new Tile(val);
    };
    public void drawTile(Graphics2D g, int r, int c){
        int value = tiles[r][c].getValue();

        g.setColor(Colour.getTileColor(value));
        g.fillRoundRect(215 + c*121, 115+r*121, 106,106,7,7);
        String s = String.valueOf(value);
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();

        int x = 215 +c *121+(106-fm.stringWidth(s))/2;
        int y = 115 +r *121+(asc+(106-(asc+dec))/2);
        g.setColor(Colour.getStringColor(value));
        g.drawString(s, x,y);

    };

    boolean goUp(){
        return move(0,-1,0);

    }
    boolean goDown(){
        return move(0,1,side*side-1);

    }
    boolean goRight(){
        return move(1,0,side*side-1);
    }
    boolean goLeft(){
        return move(-1,0,0);
    }

    private boolean move (int moveX, int moveY, int a){
        boolean moved =false;
        for(int i = 0; i< side*side; i++){
            int j = Math.abs(a -i);

            int row = j/side;
            int col = j%side;

            if(tiles[row][col] ==null)
                continue;

            int nextTileRow = row + moveY;
            int nextTileCol = col + moveX;

            // dopoki nie wyhcodzimy poza obrys planszy
            while(nextTileRow >=0 && nextTileRow <side && nextTileCol >=0 && nextTileCol <side){
                // zapamietanie obecnego kafelka
                Tile currentTile = tiles[row][col];
                // nastepny kafelek - uwzglednienie przesuniecie
                Tile nextTile = tiles[nextTileRow][nextTileCol];

                // sprawdzanie czy nastepny kafelek jest pusty
                if(nextTile == null){

                    // przypisanie do nowego kafelka/ starego
                    tiles[nextTileRow][nextTileCol] = currentTile;
                    //wyzerowanie;
                    tiles[row][col]= null;

                    // kolejny do sprawdzenia w petli while
                    row = nextTileRow;
                    col = nextTileCol;
                    nextTileRow += moveY;
                    nextTileCol += moveX;

                    moved = true;

                }
                // sprawdzenie czy moze sie polaczyc z innym
                else if (nextTile.canMergeWith(currentTile)){
                    int value = nextTile.mergeWith(currentTile);
                    score += value;
                    // zerowanie poprzedniego
                    tiles[row][col] = null;

                    // ustawianie wyniku

                    if(score > highest){
                        highest = score;
                    }
                    moved = true;
                    break;
                } else{
                    break;
                }


            }


        }

        //dodanie warunków brzegowych dla wygranej i przegranej



        if (moved) {
            if (highest < target) {
                clearMerged();
                addRandomTile();

            } else if (highest == target)
                gamestate = State.won;
        }
        return moved;
    }

    // sprawdzanie mozliwosci ruchu
    boolean movesAvailable() {
        boolean hasMoves = goUp() || goDown() || goLeft() || goRight();
        return hasMoves;
    }

    public void clearMerged(){
        for(Tile[] row : tiles)
            for(Tile tile : row)
                if(tile != null){
                    tile.setMerged(false);
                }

    }


}
