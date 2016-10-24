package chapter_4.section_2;

// Linear stages
class Flipper extends SingleOutputPushStage implements PushStage {

    public void putA(Box p) {
        if (p instanceof JoinedPair)
            ((JoinedPair) p).flip();
        next1().putA(p);
    }
}
