package chapter_2.section_4;

/*
    //Read-Only Adapters para

    Sample implementation of updatable version
*/
class UpdatableAccountImpl implements UpdatableAccount {

    private long currentBalance;

    public UpdatableAccountImpl(long initialBalance) {
        currentBalance = initialBalance;
    }

    public synchronized long balance() {
        return currentBalance;
    }

    public synchronized void credit(long amount) throws InsufficientFunds {
        if (amount >= 0 || currentBalance >= -amount)
            currentBalance += amount;
        else
            throw new InsufficientFunds();
    }

    public synchronized void debit(long amount) throws InsufficientFunds {
        credit(-amount);
    }
}
