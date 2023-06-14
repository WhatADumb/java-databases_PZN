package study.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class PrepareStatementTest {
    @Test
    public void testPrepareSelect()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String username = "admin'; #";
        String password = "empty";

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            System.out.println("Login Successfuly: " + resultSet.getString("username"));
        }else{
            System.out.println("Access Denied");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testPrepareDDL()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        
        String username = "point";
        String password = "exact";

        var query = """
                INSERT INTO admin(username, password) VALUES(?, ?)
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        int result = preparedStatement.executeUpdate();
        System.out.println("QUERY OK, " + result + " rows affected");

        preparedStatement.close();
        connection.close();
    }
}
