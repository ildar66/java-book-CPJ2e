package chapter_3.section_2;

/*
// Guarded Suspension
The idea here is that implementations of BoundedCounter are obligated to maintain a
        count between MIN and MAX
*/
interface BoundedCounter {

    static final long MIN = 0; // minimum allowed value

    static final long MAX = 10; // maximum allowed value

    long count(); // INV: MIN <= count() <= MAX
    // INIT: count() == MIN

    void inc(); // only allowed when count() < MAX

    void dec(); // only allowed when count() > MIN

}
/*
    pseudoclass BoundedCounterWithWhen { // Pseudocode
        protected long count = MIN;

        public long count() { return count; }

        public void inc() {
            WHEN (count < MAX) {
                ++count;
            }
        }
        public void dec()
            WHEN (count > MIN) {
                --count;
            }
        }
   }
*/
