package chapter_1.section_1;

import java.awt.*;
import java.util.Random;
/*
 * Particle class defines a completely unrealistic model of movable bodies
 */

public class Particle {

    protected int x;
    protected int y;
    protected final Random rng = new Random();

    public Particle(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }

    public synchronized void move() {
        x += rng.nextInt(10) - 5;
        y += rng.nextInt(20) - 5;
    }

    public void draw(Graphics g) {
        int lx, ly;
        synchronized (this) {
            lx = x;
            ly = y;
        }
        g.drawRect(lx, ly, 10, 10);
    }
}
