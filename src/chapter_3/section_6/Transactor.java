package chapter_3.section_6;

// Transaction Participants. Interfaces
interface Transactor {

    // Enter a new transaction and return true, if can do so
    public boolean join(Transaction t);

    // Return true if this transaction can be committed
    public boolean canCommit(Transaction t);

    // Update state to reflect current transaction
    public void commit(Transaction t) throws Failure;

    // Roll back state (No exception; ignore if inapplicable)
    public void abort(Transaction t);

}
