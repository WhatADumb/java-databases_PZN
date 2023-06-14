package study.java.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class StatementQueryTest {
    @Test
    public void testQuerySelect() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        
        String query = """
                SELECT * FROM customers
                """;

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            var dataId = resultSet.getString("id");
            var dataName = resultSet.getString("name");
            var dataEmail = resultSet.getString("email");

            System.out.println(String.join(", ", dataId, dataName, dataEmail));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
