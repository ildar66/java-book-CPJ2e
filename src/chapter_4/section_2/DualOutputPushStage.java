package chapter_4.section_2;

// Connections
class DualOutputPushStage extends SingleOutputPushStage {

    private PushStage next2 = null;

    protected synchronized PushStage next2() {
        return next2;
    }

    public synchronized void attach2(PushStage s) {
        next2 = s;
    }

}
