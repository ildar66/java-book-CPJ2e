package chapter_3.section_5;

import chapter_0.InsufficientFunds;

/**
 * // Example section
 * An instance class @see {@link TSBoolean} is used to control entry into the synchronized part of
 * the main checking-side method tryTransfer, which is the potential deadlock point in this design. If
 * another transfer is attempted by a savings account while one is executing (always, in this case, one
 * that is initiated by the checking account), then it is just ignored without deadlocking. This is
 * acceptable here since the executing tryTransfer and transferOut operations are based on
 * the most recently updated savings balance anyway.
 */
public class ATCheckingAccount extends BankAccount {

    protected ATSavingsAccount savings;
    protected long threshold;
    protected TSBoolean transferInProgress = new TSBoolean();

    public ATCheckingAccount(long t) {
        threshold = t;
    }

    // called only upon initialization
    synchronized void initSavings(ATSavingsAccount s) {
        savings = s;
    }

    protected boolean shouldTry() {
        return balance < threshold;
    }

    void tryTransfer() { // called internally or from savings
        if (!transferInProgress.testAndSet()) { // if not busy ...
            try {
                synchronized (this) {
                    if (shouldTry())
                        balance += savings.transferOut();
                }
            } finally {
                transferInProgress.clear();
            }
        }
    }

    public synchronized void deposit(long amount) throws InsufficientFunds {
        if (balance + amount < 0)
            throw new InsufficientFunds();
        else {
            balance += amount;
            tryTransfer();
        }
    }

}
