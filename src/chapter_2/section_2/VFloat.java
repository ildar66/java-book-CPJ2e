package chapter_2.section_2;

/*
   Volatile para:
   In terms of atomicity, visibility, and ordering, declaring a field as volatile is nearly identical in
   effect to using a little fully synchronized class protecting only that field via get/set methods, as in:
*/
final class VFloat {

    // Declaring a field as volatile differs only in that no locking is involved.
    private float value;

    final synchronized float get() {
        return value;
    }

    final synchronized void set(float f) {
        this.value = f;
    }

    /*Using volatile fields can make sense when it is somehow known that only one thread can change
    a field, but many other threads are allowed to read it at any time.*/

}
