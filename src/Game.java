import model.Buttons;

import javax.swing.*;
import java.awt.*;

/**
 * @author Karol Kraus s20687
 * @version 1.0
 */

public class Game {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setResizable(true);
        game.add(new game2048(), BorderLayout.CENTER);
        game.pack();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }


}
