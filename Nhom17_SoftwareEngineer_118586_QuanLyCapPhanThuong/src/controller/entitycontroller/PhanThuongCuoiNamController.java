package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.HoGiaDinh;
import service.DatabaseConnection;

public class PhanThuongCuoiNamController extends EntityController<HoGiaDinh> {
    // private ArrayList<PhanThuongCuoiNamRecord> list = null;

    public PhanThuongCuoiNamController() {
        getDataFromDatabase(1, 0); // get first page
    }

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection.executeQuery("Select * from ho_gia_dinh LIMIT " + Integer.toString(limit)
                + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                /*
                 * list.add(new PhanThuongCuoiNamRecord( result.getInt("ID"),
                 * result.getInt("idHocSinh"), result.getDate("ngay"),
                 * result.getString("namHoc"), result.getInt("idPhanQua"),
                 * result.getInt("soPhanQua"), result.getInt("giaTri") ) );
                 */
                list.add(new HoGiaDinh(result.getInt("ID"), result.getInt("idChuHo"), result.getString("maKhuVuc"),
                        result.getString("diaChi"),
                        // Date.valueOf("2000-1-10")
                        result.getDate("ngayLap")));
            }
            // result.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection.executeQuery("Select * from ho_gia_dinh");
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new HoGiaDinh(result.getInt("ID"), result.getInt("idChuHo"), result.getString("maKhuVuc"),
                        result.getString("diaChi"),
                        // Date.valueOf("2000-1-10")
                        result.getDate("ngayLap")));
            }
            // result.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateToDatabase() {

    }

    @Override
    public void addRecord(HoGiaDinh record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(HoGiaDinh newRecord) {
        // TODO Auto-generated method stub

    }

    public void updateRecord(HoGiaDinh oldRecord, HoGiaDinh newRecord) {

    }

    @Override
    public void deleteRecord(int index) {

    }

    @Override
    public void deleteRecord(HoGiaDinh record) {
        // System.out.println("Delete from ho_gia_dinh where ho_gia_dinh.ID = " +
        // record.getID());
        DatabaseConnection.executeUpdate("Delete from ho_gia_dinh where ho_gia_dinh.ID = " + record.getID() + ";");
    }

}
