package chapter_4.section_2;

// Collectors
class Collector extends SingleOutputPushStage implements DualInputPushStage {

    public void putA(Box p) {
        next1().putA(p);
    }

    public void putB(Box p) {
        next1().putA(p);
    }
}
