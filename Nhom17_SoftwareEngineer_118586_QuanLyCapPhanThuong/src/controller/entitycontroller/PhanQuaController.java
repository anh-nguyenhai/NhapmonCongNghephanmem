package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.PhanQua;
import service.DatabaseConnection;
import javafx.collections.transformation.FilteredList;

public class PhanQuaController extends EntityController<PhanQua> {

    FilteredList<PhanQua> filteredData;

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from phan_qua LIMIT " + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new PhanQua(
                    result.getInt("ID"), 
                    result.getString("loaiQua"),
                    result.getInt("giaTri"), 
                    result.getString("moTa")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getDataFromDatabase() {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from phan_qua");
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new PhanQua(
                    result.getInt("ID"), 
                    result.getString("loaiQua"),
                    result.getInt("giaTri"), 
                    result.getString("moTa")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void addRecord(PhanQua record) {
        DatabaseConnection.executeUpdate(
            "INSERT INTO phan_qua VALUES (null, '"
            + record.getLoaiQua() + "', "
            + record.getGiaTriInt() + ", '"
            + record.getMoTa() + "')"
            );

    }

    @Override
    public void updateRecord(PhanQua newRecord) {
        DatabaseConnection.executeUpdate("UPDATE phan_qua SET phan_qua.moTa = '"
        + newRecord.getMoTa()
        + "', phan_qua.giaTri = " + newRecord.getGiaTriInt() 
        +", phan_qua.loaiQua = '" + newRecord.getLoaiQua()
        + "' WHERE phan_qua.ID = "+ newRecord.getID());
    }

    @Override
    public void updateRecord(PhanQua oldRecord, PhanQua newRecord) {
        

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(PhanQua record) {
        DatabaseConnection.executeUpdate("Delete from phan_qua where phan_qua.ID = " + record.getID() + ";");
    }
   
}
