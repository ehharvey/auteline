/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// ATMTest.java
// Driver program to test ATM program

package auteline;

public class ATMTest {


  // main method creates and runs the ATM
  public static void main(String[] args) {
    boolean runWithSQL = false;
    boolean runWithGUI = false;
    
    //check for command line arguments
    for (String arg: args){
      if (arg.equals("--SQL") || arg.equals("-SQL")) {
        //Confirm desired flags with group later
        runWithSQL = true;
      }
      else if (arg.equals("--GUI") || arg.equals("-GUI")){
        runWithGUI = true;
      }
    }
    Screen screen = new Screen();
    Keypad keypad = new Keypad();
    CashDispenser cashDispenser = new CashDispenser();
    DepositSlot depositSlot = new DepositSlot();
    BankDatabaseInterface bankDatabase;

    if (runWithSQL){
      bankDatabase = new BankDatabaseSql();
    }
    else{
      bankDatabase = new BankDatabase();
    }

    ATM mobileATM = new ATM(screen, keypad, cashDispenser,
            depositSlot, bankDatabase);
    mobileATM.run();
  }

}
