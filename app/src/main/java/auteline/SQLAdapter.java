package auteline;

import java.sql.*;

public class SQLAdapter {

    public Connection startConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auteline_schema","root","pass");
            
            
        } catch (Exception e) {
            System.out.println("Could not create MySQL connection: " + e);
            
        }
        return con;
    }

    public ResultSet executeQuery(Connection con, String query){
        ResultSet result = null;
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            while (result.next()){
                System.out.println("Account number: " +result.getInt(1));
            }

        } catch (Exception e) {
            System.out.println("Could not execute MySQL query: " + e);
        }
        
        return result;
    }
   
    public void closeConnection(Connection con){
        try{
            con.close(); 
        } catch (Exception e) {
            System.out.println("Could not close MySQL connection: " + e);
        } 
    }


    // determines whether a user-specified PIN matches PIN in Account
    public boolean validatePIN(Connection con, int accountNumber, int userPIN) {

        return false;
    }

    // returns available balance
    public double getAvailableBalance(Connection con, int accountNumber) {

        return 0;
    }

    // returns the total balance
    public double getTotalBalance(Connection con, int accountNumber) {

        return 0;
    }

    // credits an amount to the account
    public void credit(Connection con, int accountNumber, double amount) {


    }

    // debits an amount from the account
    public void debit(Connection con, int accountNumber, double amount) {

    }
}

