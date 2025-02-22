/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Transaction.java
// Abstract superclass Transaction represents an ATM transaction

package auteline;

public abstract class Transaction {

  private int accountNumber; // indicates account involved
  private Screen screen; // ATM's screen
  private BankDatabaseInterface bankDatabase; // account info database

  // Transaction constructor invoked by subclasses using super()
  public Transaction(int userAccountNumber, Screen atmScreen,
                     BankDatabaseInterface atmBankDatabase) {
    accountNumber = userAccountNumber;
    screen = atmScreen;
    bankDatabase = atmBankDatabase;
  }

  // return account number
  public int getAccountNumber() {
    return accountNumber;
  }

  // return reference to screen
  public Screen getScreen() {
    return screen;
  }

  // return reference to bank database
  public BankDatabaseInterface getBankDatabase() {
    return bankDatabase;
  }

  // perform the transaction (overridden by each subclass)
  abstract public void execute();

}
