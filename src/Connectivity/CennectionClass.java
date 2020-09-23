package Connectivity;

import Model.Client;
import Model.Prduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class CennectionClass
{

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        String  dbName ="Point_Vent" ;
        String  username="root" ;
        String password="" ;

        try {
            Class.forName("com.mysql.jdbc.Driver") ;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password) ;
            JOptionPane.showMessageDialog(null,"Done");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No connection");
        }

        return connection ;
    }

    public static void main(String[] args) throws SQLException {
        getConnection();
    }
    public static ObservableList<Prduit> getProduit()
    {
        CennectionClass cennectionClass =new CennectionClass() ;
        ObservableList<Prduit> list = FXCollections.observableArrayList() ;
        try {
            Connection connection =cennectionClass.getConnection() ;
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM Produit") ;
            while (set.next())
            {
                list.add(new Prduit(set.getInt("idProd"),set.getString("nameProd"),set.getInt("prixProd"),
                        set.getString("qteProd")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list ;
    }
    public static ObservableList<Client> getClient()
    {
        CennectionClass cennectionClass =new CennectionClass() ;
        ObservableList<Client> list = FXCollections.observableArrayList() ;
        try {
            Connection connection =cennectionClass.getConnection() ;
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM Client") ;
            while (set.next())
            {
                list.add(new Client(set.getInt("idClient"),set.getString("nameClient"),set.getInt("telClient"),
                        set.getString("adressClient")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list ;
    }
    public  boolean Add(String table ,int num ,String name , int prix, String qte)
    {
        CennectionClass cennectionClass =new CennectionClass() ;
        try {
            Connection connection=cennectionClass.getConnection() ;
            Statement statement =connection.createStatement();
            return statement.execute("INSERT INTO "+table+" VALUES ('"+num+"','"+name+"','"+prix+"','"+qte+"')");
        }
        catch (SQLException e) {
            if (table.equals("Client"))
            {
                JOptionPane.showMessageDialog(null,"Number or Name of Client exist");
            }
            if (table.equals("Produit"))
            {
                JOptionPane.showMessageDialog(null,"Number or Name of produit exist");

            }
            return  true ;
        }
    }
    public boolean Delete(String table ,String cond)
    {
        CennectionClass cennectionClass=new CennectionClass() ;
        String sql = null;
        if (table.equals("Client"))
        {
            sql="DELETE FROM Client "+cond ;
        }
        if (table.equals("Produit"))
        {
            sql="DELETE FROM Produit "+cond ;
        }

        try {
            Connection connection=cennectionClass.getConnection();
            Statement statement=connection.createStatement() ;
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean Edit(String table ,String cond ,String name , int prix, String description)
    {
        CennectionClass cennectionClass=new CennectionClass() ;
        String sql = null;
        if (table.equals("Produit"))
        {
            sql="UPDATE Produit SET nameProd ='"+name+"', prixProd='"+prix+"', descripProd='"+description+ "'"+cond;
        }
        else
        {
            sql="UPDATE Client SET  nameClient ='"+name+"', telClient='"+prix+"', adressClient='"+description+"' "+cond;
        }

        try {
            Connection connection=cennectionClass.getConnection() ;
            Statement statement =connection.createStatement() ;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false ;
    }

}
