package chapter_3.section_5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

// Decoupling Observers
public class Subject {

    protected double val = 0.0; // modeled state
    protected final CopyOnWriteArrayList observers = new CopyOnWriteArrayList();

    public synchronized double getValue() {
        return val;
    }

    protected synchronized void setValue(double d) {
        val = d;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void changeValue(double newstate) {
        setValue(newstate);
        for (Iterator<?> it = observers.iterator(); it.hasNext(); )
            ((Observer) (it.next())).changed(this);
    }

}
