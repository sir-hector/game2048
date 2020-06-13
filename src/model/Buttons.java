package model;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Buttons extends JButton {

    public Buttons(String text, int x, int y, int width, int height){
        super(text);
        setBounds(x,y,width,height);
        setBackground(Colour.getTileColor(4));
        setFont(new Font("Arial", Font.BOLD, 20));


    }


}
