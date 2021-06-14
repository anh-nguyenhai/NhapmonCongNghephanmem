package controller.entitycontroller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChauNhoNhanQua;
import entity.PhanQua;
import service.DatabaseConnection;

public class ChauNhoNhanQuaController extends EntityController<ChauNhoNhanQua> {

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection.executeQuery(
        "SELECT S1.ID, S1.hoTen, S1.gioiTinh, S1.ngaySinh, (year(CURRENT_DATE) - year(S1.ngaySinh)) as tuoi, S2.diaChi, S3.hoTen as phuHuynh FROM nhan_khau S1 inner join ho_gia_dinh S2 on S1.diaChiHienNay = S2.diaChi inner join nhan_khau S3 on S3.ID = S2.idChuHo where year(CURRENT_DATE) - year(S1.ngaySinh) <19 LIMIT " 
            + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));

        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new ChauNhoNhanQua(
                    result.getInt("ID"),
                    result.getString("hoTen"),
                    result.getString("gioiTinh"),
                    result.getDate("ngaySinh"), 
                    result.getInt("tuoi"), 
                    result.getString("diaChi"), 
                    result.getString("phuHuynh")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection.executeQuery("SELECT S1.ID, S1.hoTen, S1.gioiTinh, S1.ngaySinh, (year(CURRENT_DATE) - year(S1.ngaySinh)) as tuoi, S2.diaChi, S3.hoTen as phuHuynh FROM nhan_khau S1 inner join ho_gia_dinh S2 on S1.diaChiHienNay = S2.diaChi inner join nhan_khau S3 on S3.ID = S2.idChuHo where year(CURRENT_DATE) - year(S1.ngaySinh) <19 ");

        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new ChauNhoNhanQua(
                    result.getInt("ID"),
                    result.getString("hoTen"),
                    result.getString("gioiTinh"),
                    result.getDate("ngaySinh"), 
                    result.getInt("tuoi"), 
                    result.getString("diaChi"), 
                    result.getString("phuHuynh")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<PhanQua> getAllGift() {
        ArrayList<PhanQua> returnResult = new ArrayList<>();

        ResultSet result = DatabaseConnection.executeQuery("Select * from phan_qua where loaiQua like '%Dịp đặc biệt%'");

        try {
            while (result.next()) {
                returnResult.add(new PhanQua(result.getInt("ID"), result.getString("loaiQua"), result.getInt("giaTri"),
                        result.getString("moTa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnResult;
    }

    public int countChauNho(){
        int count;
        ResultSet result = DatabaseConnection.executeQuery("Select count(ID) from nhan_khau where (year(CURRENT_DATE) - year(ngaySinh)) < 19");
        try {
            while (result.next()) {
                count = result.getInt("count(ID)");
                return count;

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public void giveGift(Date theGivingDay, String dip, int iDQua, int sumGift, int sumValue) throws SQLException {
        ResultSet result1 = DatabaseConnection.executeQuery(
                "SELECT * FROM phan_qua_dip_dac_biet WHERE date = '" + theGivingDay + "'");

        if (result1.next()) {
            throw new SQLException();
        }
        DatabaseConnection.executeUpdate("INSERT INTO `phan_qua_dip_dac_biet` VALUES ('" + theGivingDay + "', " + iDQua + ", " + sumGift + ", " + sumValue + ")");
        ResultSet result = DatabaseConnection.executeQuery("SELECT ID from nhan_khau where year(CURRENT_DATE) - year(ngaySinh) < 19");
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
        while (result.next())
            {
                arrayList.add(result.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i<arrayList.size(); i++) {
            DatabaseConnection.executeUpdate("INSERT INTO `phat_qua_dip_dac_biet` VALUES (null," 
            + arrayList.get(i) + ", '" + theGivingDay + "', '" + dip + "', " + iDQua + ")"); 
        }
        
    }

    public static boolean searchDiaChi(String s){
        ResultSet resultSet = DatabaseConnection.executeQuery("Select * from ho_gia_dinh where diaChi = '"+
        s + "'" );
        try {
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public void addRecord(ChauNhoNhanQua record) {
        DatabaseConnection.executeUpdate(
            "insert into nhan_khau VALUES (null, '" + record.getHoTen() + "', null, '"
            + record.getNgaySinh() + "', '" + record.getGioiTinh() + "', null, null, null, null, null, null, '"
            + record.getDiaChi() + "', '" + record.getDiaChi() + "',  null, null, null, null, null, null)");
    }

    @Override
    public void updateRecord(ChauNhoNhanQua newRecord) {
        DatabaseConnection.executeUpdate(
            "UPDATE nhan_khau SET hoTen = '" + newRecord.getHoTen() + "', ngaySinh = '"
            + newRecord.getNgaySinh() + "', gioiTinh = '" + newRecord.getGioiTinh() + "', diaChiHienNay = '"
            + newRecord.getDiaChi() + "', noiThuongTru = '" + newRecord.getDiaChi() + "' where ID = "
            + newRecord.getID());
    }

    @Override
    public void updateRecord(ChauNhoNhanQua oldRecord, ChauNhoNhanQua newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {

    }

    @Override
    public void deleteRecord(ChauNhoNhanQua record) {
        DatabaseConnection.executeUpdate("Delete from nhan_khau where ID = " + record.getID() + ";");
    }

}
