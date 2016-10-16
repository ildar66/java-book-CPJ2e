package chapter_2.section_4;

import chapter_0.InsufficientFunds;

/**
 * Read-Only Adapters para
 * @see Account
 * Use of a read-only wrapper at line (*) might seem an unnecessary precaution. But it guards against
 * what might happen if someone were to write the following subclass and use it in conjunction with
 * AccountHolder:
 * @see EvilAccountRecorder
 */
class AccountHolder {

    private UpdatableAccount acct = new UpdatableAccountImpl(0);
    private AccountRecorder recorder;

    public AccountHolder(AccountRecorder r) {
        recorder = r;
    }

    public synchronized void acceptMoney(long amount) {
        try {
            acct.credit(amount);
            recorder.recordBalance(new ImmutableAccount(acct));// (*)
        } catch (InsufficientFunds ex) {
            System.out.println("Cannot accept negative amount.");
        }
    }

}
