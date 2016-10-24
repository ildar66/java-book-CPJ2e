package chapter_4.section_2;

// Connections
class SingleOutputPushStage {

    private PushStage next1 = null;

    protected synchronized PushStage next1() {
        return next1;
    }

    public synchronized void attach1(PushStage s) {
        next1 = s;
    }

}
