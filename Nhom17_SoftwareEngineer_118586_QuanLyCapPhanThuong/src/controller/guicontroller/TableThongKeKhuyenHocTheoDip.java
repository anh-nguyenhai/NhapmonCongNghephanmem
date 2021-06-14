package controller.guicontroller;

import app.QuanLyNhanKhau;
import controller.entitycontroller.KhuyenHocTheoDipController;
import entity.KhuyenHocTheoDip;
import entity.PhatQuaCuoiNam;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Arrays;

public class TableThongKeKhuyenHocTheoDip extends TableController<KhuyenHocTheoDip> {

    public TableThongKeKhuyenHocTheoDip(InsideMainFrameTab tab, int index, TextField sTextField) {
        super(tab, index, sTextField, new KhuyenHocTheoDipController(), 
        new String[]{"date", "tongQua", "tongTien"}, 
        new String[]{"Ngày phát", "Tổng quà đã phát", "Tổng tiền"}, 
        50);
        disable(3, toolBar.getItems().size());
        setAddForm("/fxml/ThongKeTheoDip.fxml");

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.getFilterList().setPredicate(thongke -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(thongke.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thongke.getTongQua().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thongke.getTongTien().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });

    toolBar.getItems().get(6).setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);
                Parent root = createAForm("/fxml/ThongKeTheoDip.fxml");
                getDataForForm(root);
                Stage demo = new Stage();
                Scene scene = new Scene(root); 

                // demo.setResizable(false);
                demo.setScene(scene); 
                demo.setAlwaysOnTop(true);

                demo.setOnCloseRequest(s->{
                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                    demo.close();
                });
                
                demo.show();
            }
        });
    }

    @Override
    public void firstClickOnTable() {
        disable(3, toolBar.getItems().size());
        toolBar.getItems().get(6).setDisable(false);
    }

    @Override
    public void onShowing(int index) {
        System.out.println("why");
        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongCuoiNamLabel");
        Label label = (Label)node;

        label.setText(null);
        label.setStyle("-fx-background-color: transparent;");
    }

    @Override
    public boolean addRecord() {
        return false;
    }

    @Override
    public boolean updateRecord() {
        return false;
    }

    @Override
    public boolean deleteRecord() {
        return false;
    }

    private void getDataForForm(Parent root){

        KhuyenHocTheoDip khuyenHocTheoDip = getSelectedItem();
        KhuyenHocTheoDipController phatQuaController = new KhuyenHocTheoDipController();
        phatQuaController.getDataTheoDip(khuyenHocTheoDip.getDate());
        phatQuaController.creatFilterListPhatQua();
        for(Node node : root.lookupAll(".label")){
            Label label = (Label)node;
            if (label.getId() != null ) {
            if(label.getId().equals("ngayPhat")){
                label.setText("" + khuyenHocTheoDip.getDate());
            }
            if(label.getId().equals("tongQua")){
                label.setText(khuyenHocTheoDip.getTongQua());
            }
            if(label.getId().equals("tongTien")){
                label.setText(khuyenHocTheoDip.getTongTien());
            }
        };
    }

        String[] listToDisplay = {"idHocSinh", "hoTen", "hocLuc", "thanhTichDacBiet", "ngay", "namHoc", "giaTri", "moTa"};
        String[] listLabelColumn = {"ID Học Sinh", "Họ Tên", "Học Lực", "Thành Tích Đặc Biệt", "Ngày", "Năm học", "Giá trị", "Mô tả"};

 
        for(Node node2 : root.lookupAll(".table-view")){
        TableView<PhatQuaCuoiNam> tableView = (TableView<PhatQuaCuoiNam>) node2;
             service.TableLayout.layoutTable(tableView, Arrays.asList(listToDisplay), Arrays.asList(listLabelColumn));
             tableView.setItems(phatQuaController.getFilterListPhatQua());
        }

        TextField text = (TextField) root.lookup(".text-field");
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            phatQuaController.getFilterListPhatQua().setPredicate(phatQua -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (phatQua.getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(phatQua.getIdHocSinh()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getHocLuc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getThanhTichDacBiet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getNamHoc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(phatQua.getGiaTri()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(phatQua.getNgay()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        

    }
}
