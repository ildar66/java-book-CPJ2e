package chapter_2.section_4;

/**
 * Read-Only Adapters para
 * @see Account
 */
public class AccountRecorder { // A logging facility

    public void recordBalance(Account a) {
        System.out.println(a.balance()); // or record in file
    }
}
