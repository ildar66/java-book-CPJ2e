package chapter_4.section_2;

import java.awt.*;

// Representations
class BasicBox extends Box {

    protected Dimension size;

    public BasicBox(int xdim, int ydim) {
        size = new Dimension(xdim, ydim);
    }

    public synchronized Dimension size() {
        return size;
    }

    public void show(Graphics g, Point origin) {
        g.setColor(getColor());
        g.fillRect(origin.x, origin.y, size.width, size.height);
    }

    public synchronized Box duplicate() {
        Box p = new BasicBox(size.width, size.height);
        p.setColor(getColor());
        return p;
    }

}
