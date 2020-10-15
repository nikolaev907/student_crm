package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    public static Connection connection;

    public static Connection getConnection() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = Thread
                    .currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("db.properties");
            prop.load(inputStream);
            String dbDriver = prop.getProperty("driver");
            String dbUrl = prop.getProperty("url");
            String dbUser = prop.getProperty("user");
            String dbPass = prop.getProperty("password");

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
