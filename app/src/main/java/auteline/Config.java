package auteline;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

public class Config {
    private static final Yaml yaml = new Yaml(new Constructor(Config.class));

    public SqlConfig sql;
    public boolean gui;

    public Config() {
        sql = null;
        gui = false;
    }

    public Config(boolean gui, SqlConfig sqlConfig) {
        this.gui = gui;
        this.sql = sqlConfig;
    }

    @Override
    public String toString() {
        String result = yaml.dump(this);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;

        Config other = (Config) o;
        return (
            this.gui == other.gui
            && this.sql.equals(other.sql)
        );
    }

    public static Config fromYaml(String s) throws YAMLException {        
        return yaml.load(s);
    }

    public static Config fromYaml(File f) throws FileNotFoundException, YAMLException {
        InputStream inputStream = new FileInputStream(f);
        Config result = yaml.load(inputStream);
    
        return result;
    }

    public static Config fromYaml(Path p) throws FileNotFoundException, YAMLException {
        return fromYaml(p.toFile());
    }
}
