package controller.entitycontroller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.KhuyenHocTheoDip;
import entity.PhatQuaCuoiNam;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import service.DatabaseConnection;

public class KhuyenHocTheoDipController extends EntityController<KhuyenHocTheoDip> {

    protected ArrayList<PhatQuaCuoiNam> listPhatQua = null;
    protected FilteredList<PhatQuaCuoiNam> filteredListPhatQua;

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection.executeQuery("Select * from phan_qua_khuyen_hoc order by date DESC LIMIT "
                + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new KhuyenHocTheoDip(result.getDate("date"), result.getInt("idQuaGioi"),
                        result.getInt("idQuaTienTien"), result.getInt("idQuaTrungBinh"), result.getInt("tongQua"),
                        result.getInt("tongTien")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection.executeQuery("Select * from phan_qua_khuyen_hoc order by date DESC ");
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new KhuyenHocTheoDip(result.getDate("date"), result.getInt("idQuaGioi"),
                        result.getInt("idQuaTienTien"), result.getInt("idQuaTrungBinh"), result.getInt("tongQua"),
                        result.getInt("tongTien")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDataTheoDip(Date ngay) {
        ResultSet resultSet = DatabaseConnection.executeQuery(
            "SELECT S1.ID, S1.idHocSinh, S3.hoTen, S3.hocLuc, S3.thanhTichDacBiet, S1.ngay, S1.namHoc, S2.giaTri, S2.moTa FROM phat_qua_cuoi_nam S1 inner join phan_qua S2 on S1.idPhanQua = S2.ID inner join hoc_sinh S3 on S1.idHocSinh = S3.ID where S1.ngay = '" + ngay + "'");
            if (listPhatQua != null) {
                list.clear();
            } else {
                listPhatQua = new ArrayList<>();
            }
            try {
                while (resultSet.next()) {
                    listPhatQua.add(new PhatQuaCuoiNam(
                        resultSet.getInt("ID"),
                        resultSet.getInt("idHocSinh"),
                        resultSet.getString("hoTen"),
                        resultSet.getString("hocLuc"),
                        resultSet.getString("thanhTichDacBiet"),
                        resultSet.getDate("ngay"),
                        resultSet.getString("namHoc"),
                        resultSet.getString("moTa"),
                        resultSet.getInt("giaTri")
                    ));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    public void creatFilterListPhatQua(){
        filteredListPhatQua = new FilteredList<>(FXCollections.observableArrayList(listPhatQua), p -> true);
    }

    public FilteredList<PhatQuaCuoiNam> getFilterListPhatQua() {
        return filteredListPhatQua;
    }


    @Override
    public void addRecord(KhuyenHocTheoDip record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoDip oldRecord, KhuyenHocTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(KhuyenHocTheoDip record) {
        // TODO Auto-generated method stub

    }
    
}
