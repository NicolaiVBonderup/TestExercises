/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import connector.DBConnector;
import connector.EnvironmentVar;
import entity.Bottom;
import entity.Cake;
import entity.Top;
import mapper.CakeMapper;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Private
 */
public class CakeMapperTest {
    
    CakeMapper cm;
    
    public CakeMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cm = new CakeMapper(new DBConnector(EnvironmentVar.mysqlDriver,EnvironmentVar.dbURI,EnvironmentVar.username,EnvironmentVar.password));
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testCreateCake() {
        Cake cake = cm.createCake(new Bottom(1,"BottomTest",20.00), new Top(2,"TopTest",420.69));
        assertThat(cake.getTop(),is("TopTest"));
        assertThat(cake.getBottom(),is("BottomTest"));
        assertThat(cake.getName(),is("BottomTest with TopTest"));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
