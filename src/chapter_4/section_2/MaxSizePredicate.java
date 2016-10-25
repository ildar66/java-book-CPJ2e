package chapter_4.section_2;

// Dual output stages
class MaxSizePredicate implements BoxPredicate {

    protected final int max; // max size to let through

    public MaxSizePredicate(int maximum) {
        max = maximum;
    }

    public boolean test(Box p) {
        return p.size().height <= max && p.size().width <= max;
    }
}
