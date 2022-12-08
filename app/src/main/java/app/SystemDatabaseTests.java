package app;

import auteline.Config;

public class SystemDatabaseTests {
    private static void test_001_authenticate(Config config) {
        BankDatabaseSqlTest db = new BankDatabaseSqlTest(config.AUTELINE_SQL_IP, 
            config.AUTELINE_SQL_DATABASE, 
            config.AUTELINE_SQL_USER, 
            config.AUTELINE_SQL_PASSWORD);
        
        assert db.validatePIN(12345, 54321);
    }

    public static void test_002_getbalance(Config config) {
        BankDatabaseSqlTest db = new BankDatabaseSqlTest(config.AUTELINE_SQL_IP, 
            config.AUTELINE_SQL_DATABASE, 
            config.AUTELINE_SQL_USER, 
            config.AUTELINE_SQL_PASSWORD);
        
        assert db.getAvailableBalance(12345) == 1000.00;
    }

    public static void main(String[] args) {
        Config config = new Config();

        test_001_authenticate(config);
        test_002_getbalance(config);
    }
}
