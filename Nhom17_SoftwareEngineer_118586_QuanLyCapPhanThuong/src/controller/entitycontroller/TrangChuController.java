package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import service.DatabaseConnection;

public class TrangChuController {
    public static  User user = new User();
    public static String countNhanKhau() throws SQLException {
        ResultSet resultSet = DatabaseConnection.executeQuery("select count(ID) as so_nhan_khau from nhan_khau;");
        resultSet.next();
        try {
            String s = resultSet.getString("so_nhan_khau");
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    } 
    
    public static String countHoGiaDinh() throws SQLException {
        ResultSet resultSet = DatabaseConnection.executeQuery("select count(ID) as so_ho_gia_dinh from ho_gia_dinh;");
        resultSet.next();
        try {
            String s = resultSet.getString("so_ho_gia_dinh");
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } 
    }

    public static String countChauNho() throws SQLException {
        ResultSet resultSet = DatabaseConnection.executeQuery("Select count(ID) as so_chau_nho from nhan_khau where (year(curdate()) - year(ngaySinh)) between 0 and 18; ");
        resultSet.next();
        try {
            String s = resultSet.getString("so_chau_nho");
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String countHocSinh() throws SQLException {
        ResultSet resultSet = DatabaseConnection.executeQuery("Select count(ID) as so_hoc_sinh from hoc_sinh ; ");
        resultSet.next();
        try {
            String s = resultSet.getString("so_hoc_sinh");
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setDataUser(){
        ResultSet resultSet = DatabaseConnection.executeQuery("Select * from users where userName = '" + user.getUserName() + "'");
        try {
            resultSet.next();
            user.setChucVu(resultSet.getString("chucVu"));
            user.setHoTen(resultSet.getString("hoTen"));
            user.setDienthoai(resultSet.getString("dienThoai"));
            user.setDiaChi(resultSet.getString("diaChi"));
            user.setEmail(resultSet.getString("email"));
            user.setFacebook(resultSet.getString("facebook"));
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

}

    
