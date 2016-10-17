package chapter_2.section_4;

import common.InsufficientFunds;

/**
 * Read-Only Adapters para
 * @see Account
 * Use of a read-only wrapper at line (*) @see {@link AccountHolder} might seem an unnecessary precaution.
 * But it guards against what might happen if someone were to write the EvilAccountRecorder subclass
 * and use it in conjunction with AccountHolder
 */
class EvilAccountRecorder extends AccountRecorder {

    private long embezzlement;

    // ...
    public void recordBalance(Account a) {
        super.recordBalance(a);

        if (a instanceof UpdatableAccount) {
            UpdatableAccount u = (UpdatableAccount) a;
            try {
                u.debit(10);
                embezzlement += 10;
            } catch (InsufficientFunds quietlyignore) {
            }
        }
    }
}
