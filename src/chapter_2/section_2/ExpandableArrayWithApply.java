package chapter_2.section_2;

/*
* Synchronized aggregate operations
    While this is often acceptable, it may lead to the kinds of liveness and performance problems
    that motivated the default rule, saying to release locks when making method calls (here, to apply).
*/
class ExpandableArrayWithApply extends ExpandableArray {

    public ExpandableArrayWithApply(int cap) {
        super(cap);
    }

    synchronized void applyToAll(Procedure p) {
        for (int i = 0; i < size; ++i)
            p.apply(data[i]);
    }
    /**    USAGE:
     v.applyToAll(new Procedure() {
     public void apply(Object obj) {
     System.out.println(obj);
     }
     } );
     */
}
