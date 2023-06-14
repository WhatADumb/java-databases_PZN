package study.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class AutoIncrementTest {
    @Test
    public void testGetLastResultId() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        
        String email = "fadhil@sample.com";
        String comment = "hiyalo";
        String sql = """
                INSERT INTO comments(email, comment) VALUES(?, ?)
                """;
        
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, comment);
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        
        if(resultSet.next()){
            var dataId = resultSet.getInt(1);
            System.out.println("Last Id: " + dataId);
        }

        preparedStatement.close();
        connection.close();
    }
}
