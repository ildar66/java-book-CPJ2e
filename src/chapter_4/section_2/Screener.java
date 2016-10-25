package chapter_4.section_2;

// Dual output stages
class Screener extends DualOutputPushStage implements PushStage {

    protected final BoxPredicate predicate;

    public Screener(BoxPredicate p) {
        predicate = p;
    }

    public void putA(final Box p) {
        if (predicate.test(p)) {
            new Thread(new Runnable() {

                public void run() {
                    next1().putA(p);
                }
            }).start();
        } else
            next2().putA(p);
    }
}
