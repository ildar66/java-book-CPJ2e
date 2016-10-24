package chapter_4.section_2;

import java.awt.*;

// Linear stages
class Painter extends SingleOutputPushStage implements PushStage {

    protected final Color color; // the color to paint things

    public Painter(Color c) {
        color = c;
    }

    public void putA(Box p) {
        p.setColor(color);
        next1().putA(p);
    }
}
