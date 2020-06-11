import model.Buttons;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        Buttons saveButton = new Buttons("Save");
        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.add(saveButton);
        game.setResizable(true);
        game.add(new game2048(), BorderLayout.CENTER);
        game.pack();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }
}
