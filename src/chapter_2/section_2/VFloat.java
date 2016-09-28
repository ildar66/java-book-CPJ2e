package chapter_2.section_2;

/*
   Volatile para:
   In terms of atomicity, visibility, and ordering, declaring a field as volatile is nearly identical in
   effect to using a little fully synchronized class protecting only that field via get/set methods, as in:
   Declaring a field as volatile differs only in that no locking is involved.
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

}
