/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PTOLEDO
 */
public class Conexao {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        //Class.forName("org.postgresql.Driver");
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "umc@2018");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aula_ioo","root", "");
        return con;
    }

}
