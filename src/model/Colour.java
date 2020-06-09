package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Colour  {

    public static HashMap<String, Color> COLOURS = new HashMap<String, Color> ();

    public static void entryColor(String name, int color){
        COLOURS.put(name, new Color(color));
    }
    public static Color getColorScheme(String name){
        Color color;
        color = COLOURS.get(name);
        return color;
    }

}


