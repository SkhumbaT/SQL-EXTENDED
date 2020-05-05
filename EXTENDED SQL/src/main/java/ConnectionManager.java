import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private final String url;
    private Properties properties;

    public  Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(this.url,"user","pass");
    }

    public ConnectionManager(String host, String databaseName, String username, String password) {
        this.url = "jdbc:postgresql://"+host + "/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("pass",password);
    }
}
