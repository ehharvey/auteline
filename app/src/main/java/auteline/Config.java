package auteline;

public class Config {
    public String AUTELINE_SQL_IP;
    public String AUTELINE_SQL_USER;
    public String AUTELINE_SQL_PASSWORD;
    public String AUTELINE_SQL_DATABASE;
    public boolean AUTELINE_GUI;

    public Config() {
        this.AUTELINE_SQL_IP = System.getenv("AUTELINE_SQL_IP");
        this.AUTELINE_SQL_USER = System.getenv("AUTELINE_SQL_USER");
        this.AUTELINE_SQL_PASSWORD = System.getenv("AUTELINE_SQL_PASSWORD");
        this.AUTELINE_SQL_DATABASE = System.getenv("AUTELINE_SQL_DATABASE");

        String auteline_gui = System.getenv("AUTELINE_GUI");

        if (auteline_gui == null) {
            this.AUTELINE_GUI = false;
        }
        else if (auteline_gui.toUpperCase() == "TRUE") {
            this.AUTELINE_GUI = true;
        }
        else {
            this.AUTELINE_GUI = false;
        }
    }
}
