package chapter_2.section_4;

import java.applet.Applet;

// The double-check idiom.
class AnimationApplet extends Applet {

    // Fragments
    // ...
    int framesPerSecond; // default zero is illegal value

    void animate() {

        try {
            if (framesPerSecond == 0) { // the unsynchronized check
                synchronized (this) {
                    if (framesPerSecond == 0) { // the double-check
                        String param = getParameter("fps");
                        framesPerSecond = Integer.parseInt(param);
                    }
                }
            }
        } catch (Exception e) {
        }

        // ... actions using framesPerSecond ...
    }

}
