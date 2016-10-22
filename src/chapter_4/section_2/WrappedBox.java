package chapter_4.section_2;

import java.awt.*;

// Representations
class WrappedBox extends Box {

    protected Dimension wrapperSize;
    protected Box inner;

    public WrappedBox(Box innerBox, Dimension size) {
        inner = innerBox;
        wrapperSize = size;
    }

    public void show(Graphics g, Point p) {
    }

    public Dimension size() {
        return new Dimension(0, 0);
    }

    public Box duplicate() {
        return null;
    }

    // ... other implementations of abstract Box methods

}
