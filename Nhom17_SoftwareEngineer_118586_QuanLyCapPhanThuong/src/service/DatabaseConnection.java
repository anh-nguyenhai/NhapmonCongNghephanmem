package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static Statement statement = null;
    private static Connection connection = null;

    public static boolean initializeConnection(String hostName, String port){
        if(connection != null) return true;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port
                    + "/quan_ly_nhan_khau?useUnicode=true&characterEncoding=utf-8", "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to database !!!");
        }
        
        return true; 
    }

    public static void closeConnection(){
        if(connection == null) return;
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot close connection to database !!!");
        }
    }

    public static ResultSet executeQuery(String query){
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Cannot execute query to database !!!");
            
        }
        return null;
    }

    public static void executeUpdate(String query){
        try {
            statement.executeUpdate(query);
        } catch (SQLException e1) {
            System.out.println("Cannot execute query to database !!!");
        }
    }

}
