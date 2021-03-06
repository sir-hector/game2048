package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Colour  {

    public static Color getBoardColor(int value){
        switch(value){
            case 2: return new Color(0xBBADA0);
            case 4: return new Color(0xCDC1B4);
            case 8: return new Color(0xFFEBCD);
            case 16: return new Color(0x765A4A);
        }

        return new Color(0xcdc1b4);
    }


    public static Color getTileColor(int value){
        switch(value){
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);

        }

        return new Color(0xcdc1b4);
    }

    public static Color getStringColor(int value){
        return value < 16 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }


}


