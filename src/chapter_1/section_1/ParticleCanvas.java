package chapter_1.section_1;

import java.awt.*;

/*
 * Provides a drawing area for all of the Particles
 */
class ParticleCanvas extends Canvas {

    private Particle[] particles = new Particle[0];

    public ParticleCanvas(int size) {
        setSize(new Dimension(size, size));
    }

    protected synchronized void setParticles(Particle[] ps) {
        if (ps == null) {
            throw new IllegalArgumentException("Cannot set NULL");
        }
        particles = ps;
    }

    protected synchronized Particle[] getParticles() {
        return particles;
    }

    public void paint(Graphics g) {
        Particle[] ps = getParticles();

        for (int i = 0; i < ps.length; i++) {
            ps[i].draw(g);
        }
    }
}
