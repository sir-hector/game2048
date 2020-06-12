package model;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton {

    public Buttons(String text){
        super(text);
        setPreferredSize(new Dimension(100,30));
        this.setFocusable(false);
    }

}
