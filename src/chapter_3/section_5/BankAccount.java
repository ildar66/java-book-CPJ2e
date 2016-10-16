package chapter_3.section_5;

import chapter_0.InsufficientFunds;

// Example section
/*
To illustrate some common techniques, consider a service that automatically transfers money from a
savings account to a checking account whenever the checking balance falls below some threshold, but
only if the savings account is not overdrawn. This operation can be expressed as a pseudocode joint
action: // Pseudocode
     void autoTransfer(BankAccount checking, BankAccount savings, long threshold, long maxTransfer) {
        WHEN (checking.balance() < threshold && savings.balance() >= 0) {
            long amount = savings.balance();
            if (amount > maxTransfer) amount = maxTransfer;
                savings.withdraw(amount);
                checking.deposit(amount);
        }
     }
*/
/*
Here are some observations that lead to a solution:
    • There is no compelling reason to add an explicit coordinator class. The required interactions
        can be defined in special subclasses of BankAccount.
    • The action can be performed if the checking balance decreases or the savings balance
        increases. The only operation that causes either one to change is deposit (since
        withdraw is here defined to call deposit), so versions of this method in each class
            initiate all transfers.
    • Only a checking account needs to know about the threshold, and only a savings account
        needs to know about the maxTransfer amount. (Other reasonable factorings would lead
        to slightly different implementations.)
    • On the savings side, the condition check and action code can be rolled together by defining
        the single method transferOut to return zero if there is nothing to transfer, and
        otherwise to deduct and return the amount.
    • On the checking side, a single method tryTransfer can be used to handle both
        checking-initiated and savings-initiated changes.
*/
public class BankAccount {

    protected long balance = 0;

    public synchronized long balance() {
        return balance;
    }

    public synchronized void deposit(long amount) throws InsufficientFunds {
        if (balance + amount < 0)
            throw new InsufficientFunds();
        else
            balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFunds {
        deposit(-amount);
    }

}
