package chapter_3.section_6;

// Transaction Participants. Interfaces
/*
Again, it is not even necessary to associate an object with a transaction. A simple unique long
        transactionKey argument may suffice in place of all uses of Transaction. At the other
        extreme, you may need a TransactionFactory for creating all Transactions. This
        allows different kinds of Transaction objects to be associated with different styles of
        transactions
*/
class Failure extends Exception {

}