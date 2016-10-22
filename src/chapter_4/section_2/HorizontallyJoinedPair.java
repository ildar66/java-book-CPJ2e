package chapter_4.section_2;

// Representations
class HorizontallyJoinedPair extends JoinedPair {

    public HorizontallyJoinedPair(Box l, Box r) {
        super(l, r);
    }

    public synchronized Box duplicate() {
        HorizontallyJoinedPair p = new HorizontallyJoinedPair(fst.duplicate(), snd.duplicate());
        p.setColor(getColor());
        return p;
    }

    // ... other implementations of abstract Box methods

}
