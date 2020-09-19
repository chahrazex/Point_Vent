package Connectivity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CennectionClass
{
    Connection connection ;
    public Connection getConnection() throws SQLException {
        String  dbName ="Restaurant" ;
        String  username="root" ;
        String password="" ;

        try {
            Class.forName("com.mysql.jdbc.Driver") ;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password) ;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No connection");
        }

        return connection ;
    }
}
