/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.db;

import java.sql.Connection;
import javax.sql.DataSource;

/**
 *
 * @author Private
 */
public class DBConnector implements IDBConnector {
    
    private Connection connection;
    private DataSource dataSource;

    public DBConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void open() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
