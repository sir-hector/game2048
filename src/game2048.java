import model.Buttons;
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
    private final int side =4;
    private State gamestate = State.start;
    private boolean checkingAvaiableMoves;

    long tStart;
    long tEnd;
    long tDelta;
    double elapsedSeconds;
    double timeBonus;

    /**
     *  Constructor of main class
     *
     */

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
    /**
     *  Override method paintComponet from JComponent
     */
    @Override
    public void paintComponent(Graphics g1){
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g);
    }
    /**
     *  This method start the game - setting all variables to basic value and create a new Tile
     *  Staring counting the time and create two first random tiles
     */
    public void startGame(){
        if(gamestate != State.running){
            score =0;
            highest =0;
            gamestate = State.running;
            tiles = new Tile[side][side];
            tStart = System.currentTimeMillis();
            addRandomTile();
            addRandomTile();
        }
    }
    /**
     *  Method for drawing a grid.
     *  Drawing informations depending on state of game
     *  If the game is running drawing also a tille for first two created
     */
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
                g.setColor(Colour.getTileColor(64));
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("YOU WIN", 365,350);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                drawResult(g);

            } else if(gamestate == State.over){
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Colour.getTileColor(16));
                g.drawString("Gameover", 360,350);
            }
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Colour.getBoardColor(16));
            g.drawString("CLICK TO START ", 370, 470);
            g.setColor(Colour.getBoardColor(16));

            g.drawString("USE ARROW KEYS TO MOVE TILES", 280, 530);
        }
    }
    /**
     *  Method for drawing a  result
     */
    void drawResult(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Colour.getBoardColor(16));
        g.drawString(Integer.toString(score) ,480, 30);
        g.drawString("result:", 400, 30);
        g.drawString("your time:", 250, 60);
        g.drawString(Double.toString(elapsedSeconds) ,350, 60);
        g.drawString("Time bonus:", 440, 60);
        g.drawString(Double.toString(timeBonus) ,570, 60);

    }

    /**
     *  Method for generating a new random Tile
     *  Generating a random int from 0 to 16, divid and modulo provide as full matrix of our tiles
     *  new value is always 2 or 4
     */

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
    }

    /**
     *  Method for drawing a Tile
     * @param r - is the row
     * @param c - is the column
     *
     * Draw also a value in the center of tile
     */
    public void drawTile(Graphics2D g, int r, int c){
        int value = tiles[r][c].getValue();

        g.setColor(Colour.getTileColor(value));
        g.fillRoundRect(215 + c*121, 115+r*121, 107,107,7,7);
        String s = String.valueOf(value);
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();
        int x = 215 +c *121+(106-fm.stringWidth(s))/2;
        int y = 115 +r *121+(asc+(106-(asc+dec))/2);
        g.setColor(Colour.getStringColor(value));
        g.drawString(s, x,y);

    }

    /**
     *  Method for moving tiles up
     * @return the callback of function move
     */

    boolean goUp(){
        return move(0,-1,0);
    }
    /**
     *  Method for moving tile down
     * @return the callback of function move
     */
    boolean goDown(){
        return move(0,1,side*side-1);
    }
    /**
     *  Method for moving tile right
     * @return the callback of function move
     */
    boolean goRight(){
        return move(1,0,side*side-1);
    }
    /**
     *  Method for moving tile left
     * @return the callback of function move
     */
    boolean goLeft(){
        return move(-1,0,0);
    }

    /**
     *  Method for moving tiles
     * @param moveX - move right or left(1,-1) - 0 for moving up and down
     * @param moveY - move up or down(1,-1) - 0 for moving right and left
     * @param a
     * @return true if the moved is done or false if cant
     *
     * checking if next tile can be marged - double the value
     * checking possible moves, with no other opction setting game state to over
     * if we match the final target setting game state to win
     */

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
                    if(checkingAvaiableMoves){
                        return  true;
                    }

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

                    if(checkingAvaiableMoves){
                        return  true;
                    }
                    int value = nextTile.mergeWith(currentTile);
                    score += value;
                    // zerowanie poprzedniego
                    tiles[row][col] = null;

                    // ustawianie wyniku

                    if(value > highest){
                        highest = value;
                    }
                    moved = true;
                    break;
                } else{
                    break;
                }


            }


        }

        //dodanie warunków brzegowych dla wygranej i przegrane


        if (moved) {
            if (highest < target) {
                clearMerged();
                addRandomTile();
                    if(!hasMoves()){
                    gamestate =State.over;
               }

            } else if (highest == target) {
                gamestate = State.won;
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                timeBonus = 360 - elapsedSeconds;
            }
        }
        return moved;
    }

    /**
     *  Method for checking avaiable moves
     * @return true if moves are posible
     */
    boolean hasMoves() {
        checkingAvaiableMoves = true;
        boolean hasMoves = goUp() || goDown() || goLeft() || goRight();
        checkingAvaiableMoves = false;
        return hasMoves;
    }
    /**
     *  Method setting marged to tile after each of our moves
     */
    public void clearMerged(){
        for(Tile[] row : tiles)
            for(Tile tile : row)
                if(tile != null){
                    tile.setMerged(false);
                }

    }


}
