package auteline;

import java.sql.*;

public class SQLAdapter {

    Connection connection = null;
    public void startConnection(){

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auteline_schema","root","pass");
        } catch (Exception e) {
            System.out.println("Could not create MySQL connection: " + e);
        }
    }

    public ResultSet executeQuery(String query){
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Could not execute MySQL query: " + e);
        }
        return result;
    }
    public void executeUpdate(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Could not execute MySQL update query: " + e);
        }
    }
    public void closeConnection(){
        try{
            connection.close();
        } catch (Exception e) {
            System.out.println("Could not close MySQL connection: " + e);
        }
    }


    // determines whether a user-specified PIN matches PIN in Account
    public boolean validatePIN(int accountNumber, int userPIN){
        boolean validPin;
        ResultSet resultSet = executeQuery("SELECT pin FROM bank_accounts WHERE account_number = " + accountNumber +";");
        try {
            if (!resultSet.next()){
                validPin = false;
            }
            else validPin = resultSet.getInt("pin") == userPIN;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return validPin;
    }

    // returns available balance
    public double getAvailableBalance(int accountNumber) {
        ResultSet resultSet = executeQuery("SELECT available_balance FROM bank_accounts WHERE account_number = " + accountNumber +";");
        try {
            resultSet.next();
            return resultSet.getDouble("available_balance");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // returns the total balance
    public double getTotalBalance(int accountNumber) {
        ResultSet resultSet = executeQuery("SELECT total_balance FROM bank_accounts WHERE account_number = " + accountNumber +";");
        try {
            resultSet.next();
            return resultSet.getDouble("total_balance");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // credits an amount to the account
    public void credit(int accountNumber, double amount) {
        //adds to totalBalance of account
        executeUpdate("UPDATE bank_accounts SET total_balance = total_balance + " + amount + " WHERE account_number = " + accountNumber +";");
    }

    // debits an amount from the account
    public void debit(int accountNumber, double amount) {
        //deducts from available balance and total balance
        executeUpdate("UPDATE bank_accounts SET total_balance = total_balance - " + amount + ", available_balance = available_balance - " + amount + " WHERE account_number = " + accountNumber +";");

    }
}

