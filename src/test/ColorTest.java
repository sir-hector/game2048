package test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static model.Colour.getBoardColor;
import static org.junit.Assert.*;


public class ColorTest {

    @Test
    public void testgetBoardColor() {
        Color temp = getBoardColor(2);
        assertEquals(temp, new Color(0xBBADA0));
        Color temp2 = getBoardColor(80);
        assertEquals(temp2, new Color(0xcdc1b4));
    }

}
