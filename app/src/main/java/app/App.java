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
    private static Config getConfig(Path path) throws FileNotFoundException, YAMLException {
        Yaml yaml = new Yaml(new Constructor(Config.class));
        
        try {
          InputStream inputStream = new FileInputStream(path.toFile());
          Config result = yaml.load(inputStream);
    
          return result;
    
        } catch (FileNotFoundException e) {
          throw e;
        } catch (YAMLException e) {
          throw e;
        }
      }
    
      private static Config parseArgs(String[] args) throws FileNotFoundException, YAMLException, IllegalArgumentException {
        if (args.length != 1) {
          throw new IllegalArgumentException();
        }
    
        try {
          Config result = getConfig(Path.of(args[0]));
          return result;
        } catch (FileNotFoundException e) {
          throw e;
        } catch (YAMLException e) {
          throw e;
        }
      }
    
      // main method creates and runs the ATM
      public static void main(String[] args) {    
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        CashDispenser cashDispenser = new CashDispenser();
        DepositSlot depositSlot = new DepositSlot();
        BankDatabaseInterface bankDatabase;
        Config config = new Config();

        ATM atm;
    
        try {
          config = parseArgs(args);
        } catch (IllegalArgumentException e) {
          // Retain default config
        } catch (FileNotFoundException e) {
          System.err.println("Supplied filename does not exist!");
          System.exit(1);
        } catch (YAMLException e) {
          System.err.println("Supplied file is not valid YAML!");
          System.exit(2);
        }

        // Config Database
        if (config.sql != null) {
          bankDatabase = new BankDatabaseSql(
            config.sql.ip, config.sql.database, 
            config.sql.user, config.sql.password
          );
        }
        else {
          bankDatabase = new BankDatabase();
        }

        // Config GUI
        if (config.gui) {
          throw new UnsupportedOperationException("GUI not implemented... yet");
        }
        else {
          atm = new ATM(screen, keypad, cashDispenser, depositSlot, bankDatabase);
        }

        atm.run();
      }
}
