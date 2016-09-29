package chapter_2.section_3;

/*
    Adapters para
    Adapters can be used to wrap bare unsynchronized ground objects within fully synchronized host objects

    The java.util.Collection framework uses an Adapter-based scheme to allow layered
    synchronization of collection classes. Except for Vector and Hashtable, the basic collection
    classes (such as java.util.ArrayList) are unsynchronized. However, anonymous
    synchronized Adapter classes can be constructed around the basic classes using for example:
    List l = Collections.synchronizedList(new ArrayList());
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