package chapter_4.section_2;

import java.awt.*;

// Linear stages
class Wrapper extends SingleOutputPushStage implements PushStage {

    protected final int thickness;

    public Wrapper(int t) {
        thickness = t;
    }

    public void putA(Box p) {
        Dimension d = new Dimension(thickness, thickness);
        next1().putA(new WrappedBox(p, d));
    }
}
