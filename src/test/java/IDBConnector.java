package main.util;

import java.sql.Connection;

public interface IDBConnector {
    public IDBConnector  getInstance();
    public Connection getConnection();
    public void closeConnection(); 
}
