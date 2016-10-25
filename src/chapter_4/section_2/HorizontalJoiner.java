package chapter_4.section_2;

class HorizontalJoiner extends Joiner {

    protected Box join(Box p, Box q) {
        return new HorizontallyJoinedPair(p, q);
    }
}