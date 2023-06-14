package study.java.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConnectionDatabase {
    @BeforeAll
    public static void beforeAll() {
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3306/java_db_sample";
        String user = "root";
        String password = "";

        try{
            Connection connectDb = DriverManager.getConnection(url, user, password);
            System.out.println("Success Connected Databases");
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }

    @Test
    public void testConnectionClosed() {
        String url = "jdbc:mysql://localhost:3306/java_db_sample";
        String user = "root";
        String password = "";

        try{
            Connection connectDb = DriverManager.getConnection(url, user, password);
            System.out.println("Success Connected Databases");
            connectDb.close();
            System.out.println("Success Closed Databases");
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }
    
    @Test
    public void testConnectionClosedV2() {
        String url = "jdbc:mysql://localhost:3306/java_db_sample";
        String user = "root";
        String password = "";

        try(Connection connectDb = DriverManager.getConnection(url, user, password)){
            System.out.println("Success Connected Databases");
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }
}