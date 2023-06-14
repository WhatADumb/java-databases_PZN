package study.java.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class DDLQueryTest {
    @Test
    public void testQueryInsert()throws SQLException {
        Connection connect = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connect.createStatement();

        String query = """
                INSERT INTO customers(id, name, email) VALUES
                ('tefoo', 'elass foo', 'bar@sample.com')
                """;

        var result = statement.executeUpdate(query);
        System.out.println("QUERY OK, " + result + " rows affected");

        statement.close();
        connect.close();
    }

    @Test
    public void testQueryDelete()throws SQLException {
        Connection connect = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connect.createStatement();

        String query = """
                DELETE FROM customers
                """;

        var result = statement.executeUpdate(query);
        System.out.println("QUERY OK, " + result + " rows affected");

        statement.close();
        connect.close();
    }
}
