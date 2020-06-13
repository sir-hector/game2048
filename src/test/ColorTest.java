package test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static model.Colour.*;
import static org.junit.Assert.*;


public class ColorTest {

    @Test
    public void testgetBoardColor() {
        Color temp = getBoardColor(2);
        assertEquals(temp, new Color(0xBBADA0));
        Color temp2 = getBoardColor(80);
        assertEquals(temp2, new Color(0xcdc1b4));
    }

    @Test
    public void testTileColor() {
        Color temp = getTileColor(2);
        assertEquals(temp, new Color(0xeee4da));
        Color temp2 = getTileColor(80);
        assertEquals(temp2, new Color(0xcdc1b4));
    }
    @Test
    public void testgetStringColor() {
        Color temp = getStringColor(8);
        assertEquals(temp, new Color(0x776e65));
        Color temp2 = getStringColor(80);
        assertEquals(temp2, new Color(0xf9f6f2));
    }

}
