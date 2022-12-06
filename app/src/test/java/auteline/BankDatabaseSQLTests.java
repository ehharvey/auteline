package auteline;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BankDatabaseSQLTests {
    final int ACCOUNT_NUMBER = 12345; 
    final int PIN = 54321;

    final String IP = "123.123.123.123";
    final String DB = "auteline_schema";
    final String USER = "root";
    final String PASS = "pass";
    BankDatabaseSql SqlDatabase = spy(new BankDatabaseSql());

    @Test
    public void T001_validatePIN_12345PIN54321_true() throws SQLException{
        final String STATEMENT = "SELECT pin FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER +";";
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("pin")).thenReturn(PIN);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);

        boolean expected = true;
        boolean actual = SqlDatabase.validatePIN(ACCOUNT_NUMBER, PIN);

        verify(SqlDatabase, times(1)).executeQuery(STATEMENT);
        verify(resultSetMock, times(1)).next();
        verify(resultSetMock, times(1)).getInt("pin");
        assertEquals(expected, actual);
    }

    @Test
    public void T002_getAvailableBalance_1200_1200() throws SQLException{
        final double BALANCE = 1200.00;
        final String COLUMN = "available_balance";
        final String STATEMENT = "SELECT available_balance FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER + ";";
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getDouble(COLUMN)).thenReturn(BALANCE);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);
        
        double expected = BALANCE;
        double actual = SqlDatabase.getAvailableBalance(ACCOUNT_NUMBER);

        verify(SqlDatabase, times(1)).executeQuery(STATEMENT);
        verify(resultSetMock, times(1)).getDouble(COLUMN);
        verify(resultSetMock, times(1)).next();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void T003_getTotalBalance_800_800() throws SQLException{
        final double BALANCE = 800.00;
        final String COLUMN = "total_balance";
        final String STATEMENT = "SELECT " + COLUMN + " FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER + ";";
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getDouble(COLUMN)).thenReturn(BALANCE);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);
        
        double expected = BALANCE;
        double actual = SqlDatabase.getTotalBalance(ACCOUNT_NUMBER);

        verify(SqlDatabase, times(1)).executeQuery(STATEMENT);
        verify(resultSetMock, times(1)).getDouble(COLUMN);
        verify(resultSetMock, times(1)).next();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void T004_credit_400_UpdateStatement(){
        final double AMOUNT = 400.00;
        final String STATEMENT = "UPDATE bank_accounts SET total_balance = total_balance + " + AMOUNT + " WHERE account_number = " + ACCOUNT_NUMBER +";";
        
        SqlDatabase.credit(ACCOUNT_NUMBER, AMOUNT);

        verify(SqlDatabase, times(1)).executeUpdate(STATEMENT);
    }

    @Test
    public void T005_debit_300_UpdateStatement(){
        final double AMOUNT = 300.00;
        final String STATEMENT = "UPDATE bank_accounts SET total_balance = total_balance - " + AMOUNT + ", available_balance = available_balance - " + AMOUNT + " WHERE account_number = " + ACCOUNT_NUMBER +";";
        
        SqlDatabase.debit(ACCOUNT_NUMBER, AMOUNT);

        verify(SqlDatabase, times(1)).executeUpdate(STATEMENT);
    }

    @Test
    public void T006_autenticateUser_12345PIN54321_true() throws SQLException{
        ResultSet resultSetMock = mock(ResultSet.class);
        final String STATEMENT = "SELECT pin FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER +";";
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("pin")).thenReturn(PIN);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);
        
        boolean expected = true;
        boolean actual = SqlDatabase.authenticateUser(ACCOUNT_NUMBER, PIN);

        verify(SqlDatabase, times(1)).validatePIN(ACCOUNT_NUMBER, PIN);
        verify(resultSetMock, times(1)).next();
        verify(resultSetMock, times(1)).getInt("pin");
        assertEquals(expected, actual);
    }

    @Test
    public void T007_autenticateUser_12345PIN33333_false() throws SQLException{
        ResultSet resultSetMock = mock(ResultSet.class);
        final String STATEMENT = "SELECT pin FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER +";";
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("pin")).thenReturn(PIN);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);
        
        boolean expected = false;
        boolean actual = SqlDatabase.authenticateUser(ACCOUNT_NUMBER, 33333);

        verify(SqlDatabase, times(1)).validatePIN(ACCOUNT_NUMBER, 33333);
        verify(resultSetMock, times(1)).next();
        verify(resultSetMock, times(1)).getInt("pin");
        assertEquals(expected, actual);
    }

    @Test
    public void T008_validatePIN_12345PIN22222_false() throws SQLException{
        final String STATEMENT = "SELECT pin FROM bank_accounts WHERE account_number = " + ACCOUNT_NUMBER +";";
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("pin")).thenReturn(PIN);
        when(SqlDatabase.executeQuery(STATEMENT)).thenReturn(resultSetMock);

        boolean expected = false;
        boolean actual = SqlDatabase.validatePIN(ACCOUNT_NUMBER, 22222);

        verify(SqlDatabase, times(1)).executeQuery(STATEMENT);
        verify(resultSetMock, times(1)).next();
        verify(resultSetMock, times(1)).getInt("pin");
        assertEquals(expected, actual);
    }
}
