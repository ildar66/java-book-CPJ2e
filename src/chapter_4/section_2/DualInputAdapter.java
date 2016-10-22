package chapter_4.section_2;

/**
 * // Adapters
 * We can make the"B"channels of DualInputPushStages completely transparent to other
 * stages by defining a simple Adapter
 * class that accepts a putA but relays it to the intended recipient's
 * putB.In this way,most stages can be built to invoke putA without knowing or caring that the box
 * is being fed into some successor's B channel:
 */

class DualInputAdapter implements PushStage {

    protected final DualInputPushStage stage;

    public DualInputAdapter(DualInputPushStage s) {
        stage = s;
    }

    public void putA(Box p) {
        stage.putB(p);
    }

}
