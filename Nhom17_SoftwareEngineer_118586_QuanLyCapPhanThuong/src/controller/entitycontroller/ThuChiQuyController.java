package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ThuChiQuy;
import service.DatabaseConnection;

public class ThuChiQuyController extends EntityController<ThuChiQuy> {

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection.executeQuery("select * from lich_su_thu_chi order by ngay DESC limit "
                + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new ThuChiQuy(result.getInt("ID"), result.getString("ngay"), result.getInt("soTien"),
                        result.getString("loaiThuChi"), result.getString("userNguoiThucHien"),
                        result.getString("moTa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection.executeQuery("select * from lich_su_thu_chi order by ngay DESC");
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new ThuChiQuy(result.getInt("ID"), result.getString("ngay"), result.getInt("soTien"),
                        result.getString("loaiThuChi"), result.getString("userNguoiThucHien"),
                        result.getString("moTa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addRecord(ThuChiQuy record) {
        DatabaseConnection.executeUpdate("INSERT INTO lich_su_thu_chi VALUES (null, '" + record.getNgay() + "', "
                + record.getSoTienInt() + ", '" + record.getLoaiThuChi() + "', '" + record.getUserNguoiThucHien() + "', '"
                + record.getMoTa() + "');");
    }

    public static void themThuChi(ThuChiQuy record) {
        DatabaseConnection.executeUpdate("INSERT INTO lich_su_thu_chi VALUES (null, '" + record.getNgay() + "', "
                + record.getSoTienInt() + ", '" + record.getLoaiThuChi() + "', '" + record.getUserNguoiThucHien() + "', '"
                + record.getMoTa() + "');");
    }

    @Override
    public void updateRecord(ThuChiQuy newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(ThuChiQuy oldRecord, ThuChiQuy newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(ThuChiQuy record) {
        // TODO Auto-generated method stub

    }

    public static void main(String args[]) {
        DatabaseConnection.initializeConnection("localhost", "3306");
        DatabaseConnection.executeQuery("insert into lich_su_thu_chi VALUES (null,'2020-11-05',10000, 'Thu', 'admin', 'Đóng tiền')");
        ThuChiQuyController controller = new ThuChiQuyController();
        controller.addRecord(new ThuChiQuy(3, "2020-11-02", 1000, "Thu", "admin", "nạp tiền vào quỹ"));
    }


}