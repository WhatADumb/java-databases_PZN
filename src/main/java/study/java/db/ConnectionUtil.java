package study.java.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
    
    private static HikariDataSource dataSource;

    static{
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_db_sample");
        config.setUsername("root");
        config.setPassword("");

        config.setMaximumPoolSize(7);
        config.setMinimumIdle(3);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);
        
        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
