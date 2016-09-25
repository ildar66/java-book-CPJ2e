package chapter_1.section_1;

import java.applet.Applet;

/*
 *  The start and stop methods are synchronized to preclude concurrent starts or stops.
 *  Nullness of variable threads is used as a convenient state indicator.
 */
class ParticleApplet extends Applet {

    protected Thread[] threads = null;
    protected final ParticleCanvas canvas = new ParticleCanvas(100);

    public void init() {
        add(canvas);
    }

    protected Thread makeThread(final Particle p) {
        Runnable runloop = new Runnable() {

            @Override
            public void run() {
                try {
                    for (; ; ) {
                        p.move();
                        canvas.repaint();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        return new Thread(runloop);
    }

    @Override
    public synchronized void start() {
        int n = 10;

        if (isStopped()) {
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                particles[i] = new Particle(50, 50);
            }
            canvas.setParticles(particles);

            threads = new Thread[n];
            for (int i = 0; i < n; i++) {
                threads[i] = makeThread(particles[i]);
                threads[i].start();
            }
        }
    }

    private boolean isStopped() {
        return (threads == null);
    }

    @Override
    public synchronized void stop() {
        if (!isStopped()) {
            for (int i = 0; i < threads.length; i++) {
                threads[i].interrupt();
            }
            threads = null;
        }
    }

}
