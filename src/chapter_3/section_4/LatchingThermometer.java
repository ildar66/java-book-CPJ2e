package chapter_3.section_4;

// Latching variables and predicates

/**
 * While utility classes are convenient for most one-shot triggering applications, latching fields (also
 * known as permanent variables) and predicates can improve reliability, simplify usage, and improve
 * efficiency in other contexts as well.
 * Among their other properties, latching predicates (including the common special case of threshold
 * indicators) are among the very few conditions for which unsynchronized busy-wait loops (see § 3.2.6)
 * may be a possible (although rarely taken) implementation option for guarded methods. If a predicate is
 * known to latch, then there is no risk that it will slip (see § 3.2.4.1). Its value cannot change between
 * the check to see if it is true and a subsequent action that requires it to remain true. For example:
 */
public class LatchingThermometer { // Seldom useful
    // Note that this kind of construction is confined to classes in which all relevant variables
    // are either declared as volatile or are read and written only under synchronization

    private volatile boolean ready; // latching
    private volatile float temperature;

    public double getReading() {
        while (!ready)
            Thread.yield();
        return temperature;
    }

    void sense(float t) { // called from sensor
        temperature = t;
        ready = true;
    }
}
