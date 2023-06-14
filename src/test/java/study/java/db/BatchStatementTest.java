package study.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class BatchStatementTest {
    @Test
    public void testBatchStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        var sql = "INSERT INTO comments(email, comment) VALUES('x@sample.com', 'hi')";

        for(int i = 1; i <= 1000; i++){
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    public void testBatchPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        
        var email = "sample@data.sample";
        var comment = "hello";
        var sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for(int i = 1; i <= 1000; i++){
            preparedStatement.clearParameters();
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, comment);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}
