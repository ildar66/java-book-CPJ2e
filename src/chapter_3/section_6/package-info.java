/*
 * Created by User on 17.10.2016.
 * // Transactions section
 *
 * From a transactional point of view, a stand-alone transfer operation (without any provisions
    for automatic transfers) looks like:

    pseudoclass AccountUser { // Pseudocode
        TransactionLogger log; // any kind of logging facility
        // ...
        // Attempt transfer; return true if successful
        public boolean transfer(long amount, BankAccount source, BankAccount destination) {
            TRANSACTIONALLY {
                if (source.balance() >= amount) {
                    log.logTransfer(amount, source, destination);
                    source.withdraw(amount);
                    destination.deposit(amount);
                    return true;
                }
                else
                    return false;
            }
        }
    }

    The TRANSACTIONALLY pseudo-qualifier indicates that we'd like this code to be executed in an
    all-or-none fashion without any possibility of interference from other activities. Once implemented,
    this operation could be used in an automated transfer scheme of the sort described in § 3.5.1.
    Additionally, the transactional approach permits greater flexibility than seen in our specific solution,
    although with significantly greater overhead. Once classes are fitted with transactional apparatus, it
    becomes possible to associate transactionality with any sequence of operations involving bank
    accounts.
 */
package chapter_3.section_6;