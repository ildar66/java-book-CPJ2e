package chapter_3.section_6;

import common.InsufficientFunds;

/**
 * // Transaction Participants. Implementations
 * When applied to the BankAccount class, taking the simplest possible option at each step leads to a
 * mixed-strategy version that is probably not fit for serious use. Among other scale-downs, it maintains
 * only a single backup copy of state (as a single field), so can be used only for non-overlapping
 * transactions. But this version suffices to illustrate the general structure of transactional classes and
 * also, implicitly, how much more code would be required to build a more useful version:
 */
class SimpleTransBankAccount implements TransBankAccount {

    protected long balance = 0;
    protected long workingBalance = 0; // single shadow copy
    protected Transaction currentTx = null; // single transaction

    public synchronized long balance(Transaction t) throws Failure {
        if (t != currentTx)
            throw new Failure();
        return workingBalance;
    }

    public synchronized void deposit(Transaction t, long amount) throws InsufficientFunds, Failure {
        if (t != currentTx)
            throw new Failure();
        if (workingBalance < -amount)
            throw new InsufficientFunds();
        workingBalance += amount;
    }

    public synchronized void withdraw(Transaction t, long amount) throws InsufficientFunds, Failure {
        deposit(t, -amount);
    }

    public synchronized boolean join(Transaction t) {
        if (currentTx != null)
            return false;
        currentTx = t;
        workingBalance = balance;
        return true;
    }

    public synchronized boolean canCommit(Transaction t) {
        return (t == currentTx);
    }

    public synchronized void abort(Transaction t) {
        if (t == currentTx)
            currentTx = null;
    }

    public synchronized void commit(Transaction t) throws Failure {
        if (t != currentTx)
            throw new Failure();
        balance = workingBalance;
        currentTx = null;
    }

}
