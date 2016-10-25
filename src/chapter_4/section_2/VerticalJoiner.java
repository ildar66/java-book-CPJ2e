package chapter_4.section_2;

class VerticalJoiner extends Joiner {

    protected Box join(Box p, Box q) {
        return new VerticallyJoinedPair(p, q);
    }
}
