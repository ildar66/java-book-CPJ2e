package chapter_2.section_4;

// Read-Only Adapters para
/**
    The most secure version of this scheme takes a bit of work to set up:
        • Define a base interface describing some non-mutative functionality.
        • Optionally, define a subinterface that supports additional update methods used in the normal
        mutable implementation class.
        • Define a read-only adapter that forwards only the exported operations. For added security,
        declare that the immutable class is final. The use of final means that when you think
        you have an immutable object, you really do — it's not of some subclass that supports
        mutable operations as well.

    These classes could be used, for example, in: @see {@link AccountHolder}

    The java.util.Collection framework uses a variant of this scheme. Rather than declaring a
    separate immutable interface, the main Collection interface permits mutative methods to throw
    UnsupportedOperationExceptions. Anonymous read-only adapter classes throw these
    exceptions on all attempted update operations. They can be constructed via, for example:
    List l = new ArrayList();
    // ...
    untrustedObject.use(Collections.unmodifiableList(l));
*/
interface Account {

    long balance();
}
