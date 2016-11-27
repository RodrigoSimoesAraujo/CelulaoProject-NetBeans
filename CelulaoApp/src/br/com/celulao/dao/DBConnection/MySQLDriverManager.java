package br.com.celulao.dao.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class MySQLDriverManager {
    private static Connection mainConn;
    private static Properties connectionProps = new Properties();

    private MySQLDriverManager(){

    }

    public static synchronized Connection getConnection() throws SQLException {
        if(mainConn == null || mainConn.isClosed() || !mainConn.isValid(2000)) {
            connectionProps.put("user", "rootmobile");
            connectionProps.put("password", "treko@121");
            mainConn = DriverManager.getConnection(
                    "jdbc:mysql://db-celulao.mysql.uhserver.com:3306/db_celulao", connectionProps);
            System.out.println("Connected to database. T:" + (new Date()).getTime());
            return mainConn;
        }else{
            System.out.println("Reusing DB connection");
            return mainConn;
        }
    }
    
    public static synchronized void closeConn() throws SQLException{
        if(mainConn != null && !mainConn.isClosed()) {
            System.out.println("Closing DB connection.");
            mainConn.close();
        }else{
            System.out.println("Connection already cloed.");
        }
    }
}
