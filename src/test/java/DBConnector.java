package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector implements IDBConnector {

    private String driver;
    private String URL;
    private String id;
    private String pw;

    private Connection con;

    private DBConnector instance;

    public DBConnector(String driver, String URL, String id, String pw) {
        this.driver = driver;
        this.URL = URL;
        this.id = id;
        this.pw = pw;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector(driver, URL, id, pw);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return con;
    }

    @Override
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
