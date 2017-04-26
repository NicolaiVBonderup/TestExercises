package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector
{
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/cupcakeshop";
    private String user = "root";
    private String password = "root";
    private Connection conn = null;

    public DBConnector(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public DBConnector() {
    }
    
    
    public Connection getConnection()
    {
        if (conn == null)
        {
            try
            {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            }
            catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return conn;
    }
}