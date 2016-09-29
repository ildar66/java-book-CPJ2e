package chapter_2.section_3;

/*
    Adapters para
    Adapters can be used to wrap bare unsynchronized ground objects within fully synchronized host objects
*/
class SynchedPoint {

    protected final BarePoint delegate = new BarePoint();

    public synchronized double getX() {
        return delegate.x;
    }

    public synchronized double getY() {
        return delegate.y;
    }

    public synchronized void setX(double v) {
        delegate.x = v;
    }

    public synchronized void setY(double v) {
        delegate.y = v;
    }
}