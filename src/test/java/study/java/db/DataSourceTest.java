package study.java.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceTest {
    @Test
    public void testConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_db_sample");
        config.setUsername("root");
        config.setPassword("");

        config.setMaximumPoolSize(7);
        config.setMinimumIdle(3);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try{
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection connectDB = dataSource.getConnection();
            System.out.println("Success fetch Connection DB");

            connectDB.close();
            System.out.println("Success returning Connection DB");

            dataSource.close();
            System.out.println("Success closing Connection DB");
        }catch(SQLException e){
            Assertions.fail(e);
        }
    }

    @Test
    public void testConnectionUtil() throws SQLException {
        ConnectionUtil.getDataSource().getConnection();
    }
}
