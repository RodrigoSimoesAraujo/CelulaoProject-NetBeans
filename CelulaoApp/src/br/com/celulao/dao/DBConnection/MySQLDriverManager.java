package br.com.celulao.dao.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class MySQLDriverManager {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "rootmobile");
        connectionProps.put("password", "treko@121");
        conn = DriverManager.getConnection(
                "jdbc:mysql://db-celulao.mysql.uhserver.com:3306/db_celulao",connectionProps);
        System.out.println("Connected to database");
        return conn;
    }
}
