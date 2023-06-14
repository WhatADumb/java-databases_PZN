package study.java.db;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DriverTest {
    @Test
    public void testSetDriver() {
        try{
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }
}
