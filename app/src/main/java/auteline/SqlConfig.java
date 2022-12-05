package auteline;

public class SqlConfig {
    public String ip;
    public String user;
    public String password;
    public String database;

    public SqlConfig(String ip, String user, String password, String database) {
        this.ip = ip;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public SqlConfig() {}

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

        SqlConfig other_sql = (SqlConfig) o;

        // field comparison
        return (
            this.ip.equals(other_sql.ip)
            && this.user.equals(other_sql.user)
            && this.password.equals(other_sql.password)
            && this.database.equals(other_sql.database)
        );
    }
}
