package controller.entitycontroller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChiTietQuaDacBietTheoDip;
import entity.QuaDacBietTheoDip;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import service.DatabaseConnection;

public class QuaDacBietTheoDipController extends EntityController<QuaDacBietTheoDip> {
    protected ArrayList<ChiTietQuaDacBietTheoDip> listPhatQua = null;
    protected FilteredList<ChiTietQuaDacBietTheoDip> filteredListPhatQua;

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection.executeQuery("Select * from phan_qua_dip_dac_biet order by date DESC LIMIT "
        + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
        while (result.next()) {
            list.add(new QuaDacBietTheoDip(result.getDate("date"), result.getInt("idQua"),
                result.getInt("tongQua"), result.getInt("tongTien")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection.executeQuery("Select * from phan_qua_dip_dac_biet order by date DESC ");
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
        while (result.next()) {
            list.add(new QuaDacBietTheoDip(result.getDate("date"), result.getInt("idQua"),
                result.getInt("tongQua"), result.getInt("tongTien")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDataTheoDip(Date ngay) {
        ResultSet resultSet = DatabaseConnection.executeQuery(
        " SELECT S1.ID, S3.hoTen, S3.ngaySinh, S3.diaChiHienNay, S1.ngay, S1.dip, S2.giaTri, S2.moTa FROM phat_qua_dip_dac_biet S1 inner join phan_qua S2 on S1.idPhanQua = S2.ID inner join nhan_khau S3 on S1.idNhanKhau = S3.ID where S1.ngay = '"  + ngay + "'");
            if (listPhatQua != null) {
                listPhatQua.clear();
            } else {
                listPhatQua = new ArrayList<>();
            }
            try {
                while (resultSet.next()) {
                    listPhatQua.add(new ChiTietQuaDacBietTheoDip(
                        resultSet.getInt("ID"),
                        resultSet.getString("hoTen"),
                        resultSet.getDate("ngaySinh"),
                        resultSet.getString("diaChiHienNay"),
                        resultSet.getDate("ngay"),
                        resultSet.getString("dip"),
                        resultSet.getInt("giaTri"),
                        resultSet.getString("moTa")
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

    public FilteredList<ChiTietQuaDacBietTheoDip> getFilterListPhatQua() {
        return filteredListPhatQua;
    }


    @Override
    public void addRecord(QuaDacBietTheoDip record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(QuaDacBietTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(QuaDacBietTheoDip oldRecord, QuaDacBietTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(QuaDacBietTheoDip record) {
        // TODO Auto-generated method stub

    }
   
}

