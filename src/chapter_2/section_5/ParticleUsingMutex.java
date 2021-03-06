package chapter_2.section_5;

import java.awt.*;
import java.util.Random;

import EDU.oswego.cs.dl.util.concurrent.Mutex;

/*
    Unlike synchronized blocks, locking in standard Mutex classes is non-reentrant.
    If the lock is held, even by the thread performing the acquire, the thread will block.
*/
class ParticleUsingMutex {

    protected int x;
    protected int y;
    protected final Random rng = new Random();
    protected final Mutex mutex = new Mutex();

    public ParticleUsingMutex(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }

    public void move() {
        try {
            mutex.acquire();
            try {
                x += rng.nextInt(10) - 5;
                y += rng.nextInt(20) - 10;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void draw(Graphics g) {
        int lx, ly;

        try {
            mutex.acquire();
            try {
                lx = x;
                ly = y;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return;
        }

        g.drawRect(lx, ly, 10, 10);
    }

}
