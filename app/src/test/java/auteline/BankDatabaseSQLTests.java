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
    BankDatabaseSql SqlDatabase = spy(new BankDatabaseSql(IP, DB, USER, PASS));

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
}
