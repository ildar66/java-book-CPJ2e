package chapter_2.section_4;

// Read-Only Adapters para
final class ImmutableAccount implements Account {

    private Account delegate;

    public ImmutableAccount(long initialBalance) {
        delegate = new UpdatableAccountImpl(initialBalance);
    }

    ImmutableAccount(Account acct) {
        delegate = acct;
    }

    public long balance() { // forward the immutable method
        return delegate.balance();
    }
}
