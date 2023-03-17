package co.edu.ucatolica.jdbc2.config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1/test";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin123";
    private static final String MYSQL_DRIVE = "com.mysql.cj.jdbc.Driver";
    private static BasicDataSource dataSource;

    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setDriverClassName(MYSQL_DRIVE);
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    public static Connection getConnection(){
        try {
            Connection connection = getDataSource().getConnection();
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
