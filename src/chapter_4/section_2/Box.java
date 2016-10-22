package chapter_4.section_2;

import java.awt.*;

// Representations
abstract class Box {

    protected Color color = Color.white;

    public synchronized Color getColor() {
        return color;
    }

    public synchronized void setColor(Color c) {
        color = c;
    }

    public abstract java.awt.Dimension size();

    public abstract Box duplicate(); // clone

    public abstract void show(Graphics g, Point origin);// display

}
