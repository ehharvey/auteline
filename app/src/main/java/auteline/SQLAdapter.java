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
            System.out.println(result.getInt(1)+"  "+result.getString(2)+"  "+result.getString(3)); 

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
}

