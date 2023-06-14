package study.java.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class TimeSQTest {
    @Test
    public void testDDLTime()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_times(sample_time, sample_date, sample_timestamp)
                VALUES(?, ?, ?)
                """;
       
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setTime(1, new Time(System.currentTimeMillis()));
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
    
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testQueryTime()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                SELECT * FROM sample_times
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            var time = resultSet.getTime("sample_time");
            var date = resultSet.getDate("sample_date");
            var timestamp = resultSet.getTimestamp("sample_timestamp");

            System.out.println("Time: " + time);
            System.out.println("Date: " + date);
            System.out.println("TimeStamp: " + timestamp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
