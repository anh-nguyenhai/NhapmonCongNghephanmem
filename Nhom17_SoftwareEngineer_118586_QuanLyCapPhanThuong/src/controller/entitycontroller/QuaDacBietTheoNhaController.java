package controller.entitycontroller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChiTietQuaDacBietTheoHo;
import entity.QuaDacBietTheoNha;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import service.DatabaseConnection;

public class QuaDacBietTheoNhaController extends EntityController<QuaDacBietTheoNha> {
    Date ngay = null;
    protected ArrayList<ChiTietQuaDacBietTheoHo> listPhatQua = null;
    protected FilteredList<ChiTietQuaDacBietTheoHo> filteredListPhatQua;


    @Override
    public void getDataFromDatabase(int limit, int page) {
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }
        if (ngay != null) {
            ResultSet result = DatabaseConnection.executeQuery("SELECT S4.ID, S4.diaChi, S5.hoTen, S1.ngay, S1.dip, count(S1.ID) as soQua, sum(S2.giaTri) as soTien FROM phat_qua_dip_dac_biet S1 inner join phan_qua S2 on S1.idPhanQua = S2.ID inner join nhan_khau S3 on S1.idNhanKhau = S3.ID inner join ho_gia_dinh S4 on S4.diaChi = S3.diaChiHienNay inner join nhan_khau S5 on S5.ID = S4.idChuHo where S1.ngay = '" 
            + ngay + "' group by S4.ID, S4.diaChi, S5.hoTen, S4.diaChi, S1.ngay, S1.dip LIMIT "
                + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
            
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add( new QuaDacBietTheoNha(
                    result.getInt("ID"), 
                    result.getString("diaChi"),
                    result.getString("hoTen"),
                    result.getDate("ngay"), 
                    result.getString("dip"),
                    result.getInt("soQua"),
                    result.getInt("soTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    @Override
    public void getDataFromDatabase() {
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }
        if (ngay != null) {
            ResultSet result = DatabaseConnection.executeQuery("SELECT S4.ID, S4.diaChi, S5.hoTen, S1.ngay, S1.dip, count(S1.ID) as soQua, sum(S2.giaTri) as soTien FROM phat_qua_dip_dac_biet S1 inner join phan_qua S2 on S1.idPhanQua = S2.ID inner join nhan_khau S3 on S1.idNhanKhau = S3.ID inner join ho_gia_dinh S4 on S4.diaChi = S3.diaChiHienNay inner join nhan_khau S5 on S5.ID = S4.idChuHo where S1.ngay = '" 
            + ngay + "' group by S4.ID, S4.diaChi, S5.hoTen, S4.diaChi, S1.ngay, S1.dip");
            
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add( new QuaDacBietTheoNha(
                    result.getInt("ID"), 
                    result.getString("diaChi"),
                    result.getString("hoTen"),
                    result.getDate("ngay"), 
                    result.getString("dip"),
                    result.getInt("soQua"),
                    result.getInt("soTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    public void getDataTheoHo(Date ngayNhan, String dip, String diaChi) {
        ResultSet resultSet = DatabaseConnection.executeQuery(
            "select S2.hoTen, S2.ngaySinh, S1.ngay as ngayNhan, S1.dip, S3.giaTri, S3.moTa "
            + "from phat_qua_dip_dac_biet S1 inner join nhan_khau S2 on S1.idNhanKhau = S2.ID "
            + "inner join phan_qua S3 on S3.ID = S1.idPhanQua "
            + "where S1.ngay = '" + ngayNhan + "' and S1.dip = '" + dip + "' and S2.diaChiHienNay like '%" + diaChi +"%'");
        if (listPhatQua != null) {
                listPhatQua.clear();
            } else {
                listPhatQua = new ArrayList<>();
            }
            try {
                while (resultSet.next()) {
                    listPhatQua.add(new ChiTietQuaDacBietTheoHo(
                    resultSet.getString("hoTen"),
                    resultSet.getDate("ngaySinh"), 
                    resultSet.getDate("ngayNhan"), 
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

    public ArrayList<String> getNgayPhatQua(){
        ArrayList<String> array = new ArrayList<>();
        ResultSet resultSet = DatabaseConnection.executeQuery("Select distinct date from phan_qua_dip_dac_biet");
        try {
            while (resultSet.next()) {
                array.add(resultSet.getDate("date").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void creatFilterListPhatQua(){
        filteredListPhatQua = new FilteredList<>(FXCollections.observableArrayList(listPhatQua), p -> true);
    }

    public FilteredList<ChiTietQuaDacBietTheoHo> getFilterListPhatQua() {
        return filteredListPhatQua;
    }

    @Override
    public void addRecord(QuaDacBietTheoNha record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(QuaDacBietTheoNha newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(QuaDacBietTheoNha oldRecord, QuaDacBietTheoNha newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(QuaDacBietTheoNha record) {
        // TODO Auto-generated method stub

    }
    public static void main(String args[]) {
        DatabaseConnection.initializeConnection("localhost", "3306");
        QuaDacBietTheoNhaController control = new QuaDacBietTheoNhaController();
        control.ngay = Date.valueOf("2020-12-09");
        control.getDataFromDatabase();
    }

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
    
}
