package chapter_4.section_2;

// Dual output stages
class Cloner extends DualOutputPushStage implements PushStage {

    public void putA(Box p) {
        final Box p2 = p.duplicate();
        next1().putA(p);
        new Thread(new Runnable() {

            public void run() {
                next2().putA(p2);
            }
        }).start();
    }

}
