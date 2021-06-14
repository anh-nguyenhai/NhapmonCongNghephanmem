package controller.guicontroller;

import controller.entitycontroller.PhanThuongCuoiNamController;
import entity.HoGiaDinh;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;

public class PhanThuongCuoiNamGuiController extends TableController<HoGiaDinh>{

    public PhanThuongCuoiNamGuiController(TableView<HoGiaDinh> table, ToolBar toolBar){
        super(table, toolBar, new PhanThuongCuoiNamController(), new String[]{"ID", "ngayLap", "diaChi", "idChuHo"}, 40);
        setAddForm("/fxml/FormDemo.fxml");
        setUpdateForm("/fxml/FormDemo.fxml");
    }

    @Override
    public boolean addRecord() {
        System.out.println("Add record");
        return true;
    }

    @Override
    public boolean updateRecord() {
        System.out.println("Update record");
        return true;
    }

    @Override
    public boolean deleteRecord() {
        System.out.println("Delete record");
        return true;
    }

}
