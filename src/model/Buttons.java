package model;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Buttons extends JButton {

    public Buttons(String text){
        super(text);
        setPreferredSize(new Dimension(100,100));

        this.setFocusable(false);
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l);


    }
}
