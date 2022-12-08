package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import auteline.ATM;
import auteline.BankDatabase;
import auteline.BankDatabaseInterface;
import auteline.BankDatabaseSql;
import auteline.CashDispenser;
import auteline.Config;
import auteline.DepositSlot;
import auteline.Keypad;
import auteline.Screen;

public class App {    
      // main method creates and runs the ATM
      public static void main(String[] args) {    
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        CashDispenser cashDispenser = new CashDispenser();
        DepositSlot depositSlot = new DepositSlot();
        BankDatabaseInterface bankDatabase;
        Config config = new Config();

        ATM atm;
  

        // Config Database
        if (config.AUTELINE_SQL_IP != null) {
          bankDatabase = new BankDatabaseSql(
            config.AUTELINE_SQL_IP, config.AUTELINE_SQL_DATABASE, 
            config.AUTELINE_SQL_USER, config.AUTELINE_SQL_PASSWORD
          );
        }
        else {
          bankDatabase = new BankDatabase();
        }

        // Config GUI
        if (config.AUTELINE_GUI) {
          throw new UnsupportedOperationException("GUI not implemented... yet");
        }
        else {
          atm = new ATM(screen, keypad, cashDispenser, depositSlot, bankDatabase);
        }

        atm.run();
      }
}
