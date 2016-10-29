/*
 * Created by User on 28.10.2016.
 * Active Objects section
 *
 * In the task-based frameworks illustrated throughout most of this chapter, threads are used to propel
    conceptually active messages sent among conceptually passive objects. However, it can be productive
    to approach some design problems from the opposite perspective — active objects sending each other
    passive messages.
 *
 *  pseudoclass ActiveWaterTank extends Thread { // Pseudocode
        // ...
        public void run() {
            for (;;) {
                accept message;
                if (message is of form addWater(float amount)) {
                    if (currentVolume >= capacity) {
                        if (overflow != null) {
                            send overflow.addWater(amount);
                            accept response;
                            if (response is of form OverflowException)
                                reply response;
                            else ...
                        else ...
                     else ...
                }
                else if (message is of form removeWater(float amount)) {
                   ...
                }
            }
        }
    }
 *
 */
package chapter_4.section_5;