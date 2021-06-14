package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;

import service.DatabaseConnection;

public class QuyController {
    public static String xemQuy() {
        ResultSet resultSet = DatabaseConnection.executeQuery("select * from quy");
        try {
            resultSet.next();
            String s = resultSet.getString("soDuQuy");
            return s;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void thuQuy(int soTienThu) {
        DatabaseConnection.executeUpdate("Update quy SET soDuQuy = soDuQuy + "
         + Integer.toString(soTienThu)) ;
    }

    public static void chiQuy(int soTienChi) {
        DatabaseConnection.executeUpdate("Update quy SET soDuQuy = soDuQuy - "
         + Integer.toString(soTienChi)) ;
    }


    public static void main(String args[]) {
        DatabaseConnection.initializeConnection("localhost", "3306");
        String s = QuyController.xemQuy();
        QuyController.thuQuy(10000);
        System.out.println(s);
    }
}
