/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.utils;

import br.com.celulao.dao.DBConnection.MySQLDriverManager;
import java.sql.SQLException;

/**
 *
 * @author SYSTEM
 */
public class General {
    public static void exitSystem(){
        try {
            MySQLDriverManager.closeConn();
        } catch (SQLException ex) {
            System.out.println("Err on closing DB connection.");
        }
        System.exit(0);
    }
}
