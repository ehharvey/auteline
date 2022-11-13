/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// BankDatabase.java
// Represents the bank account information database

package auteline;

public class BankDatabase {

  SQLAdapter sqlAdapter = new SQLAdapter();

  private Account accounts[]; // array of Accounts

  // no-argument BankDatabase constructor initializes accounts
  public BankDatabase() {
    accounts = new Account[2]; // just 2 accounts for testing
    accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
    accounts[1] = new Account(98765, 56789, 200.0, 200.0);

    if(ATMTest.runWithSQL){
      sqlAdapter.startConnection();
    }

  }

  // retrieve Account object containing specified account number
  private Account getAccount(int accountNumber) {
    // loop through accounts searching for matching account number
    for (Account currentAccount : accounts) {
      // return current account if match found
      if (currentAccount.getAccountNumber() == accountNumber) {
        return currentAccount;
      }
    }
    return null; // if no matching account was found
  }

  // determine whether user-specified account number and PIN match
  // those of an account in the database
  public boolean authenticateUser(int userAccountNumber, int userPIN) {
    if (ATMTest.runWithSQL){
      return sqlAdapter.validatePIN(userAccountNumber, userPIN);
    }
    else{
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);
      // if account exists, return result of Account method validatePIN
      return userAccount != null ? userAccount.validatePIN(userPIN) : false;
    }
  }

  // return available balance of Account with specified account number
  public double getAvailableBalance(int userAccountNumber) {
    if (ATMTest.runWithSQL){
      return sqlAdapter.getAvailableBalance(userAccountNumber);
    }
    else{
      return getAccount(userAccountNumber).getAvailableBalance();

    }
  }

  // return total balance of Account with specified account number
  public double getTotalBalance(int userAccountNumber) {
    if (ATMTest.runWithSQL) {
      return sqlAdapter.getTotalBalance(userAccountNumber);

    }
    else{
      return getAccount(userAccountNumber).getTotalBalance();
    }
  }

  // credit an amount to Account with specified account number
  public void credit(int userAccountNumber, double amount) {
    if (ATMTest.runWithSQL){
      sqlAdapter.credit(userAccountNumber, amount);
    }
    else {
      getAccount(userAccountNumber).credit(amount);
    }
  }

  // debit an amount from of Account with specified account number
  public void debit(int userAccountNumber, double amount) {
    if (ATMTest.runWithSQL){
      sqlAdapter.debit(userAccountNumber, amount);
    }
    else{
      getAccount(userAccountNumber).debit(amount);
    }
  }

}
