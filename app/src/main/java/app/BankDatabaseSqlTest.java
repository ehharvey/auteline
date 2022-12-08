package app;

import java.sql.ResultSet;

import auteline.BankDatabaseSql;

public class BankDatabaseSqlTest extends BankDatabaseSql {
    void setUp() {
        String query = """
            DROP TABLE IF EXISTS bank_accounts;

            CREATE TABLE bank_accounts(
                account_number int signed NOT NULL UNIQUE,
                pin int NOT NULL,
                available_balance double NOT NULL,
                total_balance double NOT NULL,
                PRIMARY KEY (account_number)
            );

            INSERT INTO bank_accounts (
                account_number,
                pin,
                available_balance,
                total_balance
            )
            VALUES
            {
                12345,
                54321,
                500.00,
                1000.00
            };
                """;

        ResultSet rs = this.executeQuery(query);
    }
    
    public BankDatabaseSqlTest(String ip, String database, String user, String password) {
        super(ip, database, user, password);
        this.setUp();
    }
}
