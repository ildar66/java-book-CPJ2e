package chapter_3.section_5;

// Decoupling Observers
public class Observer {

    protected double cachedState; // last known state
    protected final Subject subj; // only one allowed here

    Observer(Subject s) {
        subj = s;
        cachedState = s.getValue();
        display();
    }

    synchronized void changed(Subject s) {
        if (s != subj)
            return; // only one subject

        double oldState = cachedState;
        cachedState = subj.getValue(); // probe
        if (oldState != cachedState)
            display();
    }

    protected void display() {
        // somehow display subject state; for example just:
        System.out.println(cachedState);
    }

}
