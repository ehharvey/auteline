/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// ATMTest.java
// Driver program to test ATM program

package auteline;

public class ATMTest {

  public static boolean runWithSQL = false;
  public static boolean runWithGUI = false;
  // main method creates and runs the ATM
  public static void main(String[] args) {
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
    BankDatabase bankDatabase = new BankDatabase();

    ATM mobileATM = new ATM(screen, keypad, cashDispenser, 
                            depositSlot, bankDatabase);
    mobileATM.run();
  }

}
