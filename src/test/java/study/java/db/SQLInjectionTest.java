package study.java.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class SQLInjectionTest {
    @Test
    public void testInjection()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "";

        String sql = "SELECT * FROM admin WHERE username = '"+ username +
        "' AND password = '" + password +"'";

        System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            System.out.println("Login Successfuly: " + resultSet.getString("username"));
        }else{
            System.out.println("Access Denied");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
