package main.db;

import java.sql.Connection;

public interface IDBConnector {
    
    Connection getConnection();
    void open();
    void close();
    
}
