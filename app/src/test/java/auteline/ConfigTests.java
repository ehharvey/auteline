package auteline;
import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class ConfigTests {
    
    @Test
    public void T001_defaultConfig() {
        // Arrange
        boolean EXPECTED_GUI_SETTING = false;
        SqlConfig EXPECTED_SQL = null;

        // Act
        Config actual = new Config();

        // Assert
        assertEquals(EXPECTED_GUI_SETTING, actual.gui);
        assertEquals(EXPECTED_SQL, actual.sql);
    }

    @Test
    public void T002_simpleConfig_fromYaml() {
        // Arrange
        String testDataString = """
                ---
                gui: true
                """;
        
        boolean EXPECTED_GUI_SETTING = true;
        SqlConfig EXPECTED_SQL_CONFIG = null;

        // Act
        Config actual = Config.fromYaml(testDataString);

        // Assert
        assertEquals(EXPECTED_SQL_CONFIG, actual.sql);
        assertEquals(EXPECTED_GUI_SETTING, actual.gui);
    }

    @Test
    public void T003_complexConfig_fromYaml() {
        // Arrange
        String testDataString = """
            ---
            sql:
                user: sql_user
                ip: sql_ip
                database: sql_database
                password: sql_password
            gui: true
            """;
        
        boolean EXPECTED_GUI_SETTING = true;
        SqlConfig EXPECTED_SQL_CONFIG = new SqlConfig(
            "sql_ip",
            "sql_user",
            "sql_password",
            "sql_database"
        );

        // Act
        Config actual = Config.fromYaml(testDataString);

        // Assert
        assertEquals(EXPECTED_SQL_CONFIG, actual.sql);
        assertEquals(EXPECTED_GUI_SETTING, actual.gui);
    }

    @Test
    public void T004_complexFile_fromYaml() throws IOException {
        // Arrange
        String testDataString = """
            ---
            sql:
                user: sql_user
                ip: sql_ip
                database: sql_database
                password: sql_password
            gui: true
            """;
        Path tempfile = Files.createTempFile(null, null);
        FileWriter writer = new FileWriter(tempfile.toFile());
        writer.write(testDataString);
        writer.close();

        SqlConfig EXPECTED_SQL_CONFIG = new SqlConfig(
            "sql_ip",
            "sql_user",
            "sql_password",
            "sql_database"
        );
        boolean EXPECTED_GUI_SETTING = true;

        Config EXPECTED = new Config(EXPECTED_GUI_SETTING, EXPECTED_SQL_CONFIG);
        
        // Act
        Config actual = Config.fromYaml(tempfile.toFile());

        // Assert
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void T005_complexPath_fromYaml() throws IOException {
        // Arrange
        String testDataString = """
            ---
            sql:
                user: sql_user
                ip: sql_ip
                database: sql_database
                password: sql_password
            gui: true
            """;
        Path tempfile = Files.createTempFile(null, null);
        FileWriter writer = new FileWriter(tempfile.toFile());
        writer.write(testDataString);
        writer.close();

        SqlConfig EXPECTED_SQL_CONFIG = new SqlConfig(
            "sql_ip",
            "sql_user",
            "sql_password",
            "sql_database"
        );
        boolean EXPECTED_GUI_SETTING = true;

        Config EXPECTED = new Config(EXPECTED_GUI_SETTING, EXPECTED_SQL_CONFIG);
        
        // Act
        Config actual = Config.fromYaml(tempfile);

        // Assert
        assertEquals(EXPECTED, actual);
    }
}
