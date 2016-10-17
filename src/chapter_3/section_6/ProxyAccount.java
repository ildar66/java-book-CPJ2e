package chapter_3.section_6;

/*
 * // Transaction Participants. Implementations
 *
 * Classes obeying the Transactor interface can also employ arbitrary sharing of references among
 * participants. For example, you can construct a Proxy account that forwards messages to another
 * unrelated and otherwise uncontrolled account.
 */
class ProxyAccount /* implements TransBankAccount */ {

    private TransBankAccount delegate;

    public boolean join(Transaction t) {
        return delegate.join(t);
    }

    public long balance(Transaction t) throws Failure {
        return delegate.balance(t);
    }

    // and so on...

}
