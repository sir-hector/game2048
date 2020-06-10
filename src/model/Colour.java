package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Colour  {


    public static Color getTileColor(int value){
        switch(value){
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
        }

        return new Color(0xcdc1b4);
    }


}


