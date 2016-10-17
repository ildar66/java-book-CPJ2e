package chapter_2.section_4;

import common.InsufficientFunds;

// Read-Only Adapters para
interface UpdatableAccount extends Account {

    void credit(long amount) throws InsufficientFunds;

    void debit(long amount) throws InsufficientFunds;
}
