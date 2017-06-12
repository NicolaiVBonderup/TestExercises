/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.runner;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.List;
import main.db.DBConnector;
import main.db.IDBConnector;
import main.util.Customer;
import main.util.CustomerMapper;

/**
 *
 * @author Private
 */
public class Main {
    
    private IDBConnector connector;
    private CustomerMapper customerMapper;
    
    public Main() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setURL("jdbc:mysql://localhost:3306/classicmodels");
        datasource.setUser("root");
        datasource.setPassword("pwd");

        connector = new DBConnector(datasource);
        customerMapper = new CustomerMapper(connector);
    }
    

    public static void main() {
        Main main = new Main();
    }

    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    public Customer createCustomer(Customer customer){return customerMapper.createCustomer(customer);}

    public Customer getCustomerById(int id) {
        return customerMapper.getCustomerById(id);
    }

    public boolean deleteCustomerById(int id){
        return customerMapper.deleteCustomerById(id);
    }
    
}
