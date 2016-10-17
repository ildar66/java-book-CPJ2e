package chapter_3.section_6;

import common.InsufficientFunds;

/**
 * // Transaction Participants. Implementations
 * Participants in transactions must support both a transaction participant interface and an interface
 * describing their basic actions.
 * However, it is not always necessary to provide transactional signatures for pure accessor methods such
 * as balance here. Instead (or in addition to transactional versions), such methods can return the
 * most recently committed value when called from clients that are not participating in transactions.
 * Alternatively, a special null-transaction type (or just passing null for the Transaction
 * argument) can be used to denote one-shot invocations of transactional methods.
 */
public interface TransBankAccount extends Transactor {

    public long balance(Transaction t) throws Failure;

    public void deposit(Transaction t, long amount) throws InsufficientFunds, Failure;

    public void withdraw(Transaction t, long amount) throws InsufficientFunds, Failure;

}
