package study.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TransactionTest {
    @Test
    public void testCommit()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        for(int i = 1; i <= 50; i++){
            String sql = """
                    INSERT INTO comments(email, comment) VALUES(?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "data@sample.com");
            preparedStatement.setString(2, "hello there");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.commit();
        connection.close();
    }

    @Test
    public void testRollBack()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        for(int i = 1; i <= 50; i++){
            String sql = """
                    INSERT INTO comments(email, comment) VALUES(?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "data@sample.com");
            preparedStatement.setString(2, "hello there");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.rollback();
        connection.close();
    }
}
