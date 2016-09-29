package chapter_2.section_3;

import java.awt.*;

/*
  Confinement Across Methods:

  This Point class is defined as a simple record-style class with public x and y fields,
  so it would be unwise to share instances across threads.
*/
public class Plotter { // fragments

    // ...

    public void showNextPoint() {
        Point p = new Point();
        p.x = computeX();
        p.y = computeY();
        display(p);
    }

    int computeX() {
        return 1;
    }

    int computeY() {
        return 1;
    }

    protected void display(Point p) {
        // somehow arrange to show p.
    }

}
