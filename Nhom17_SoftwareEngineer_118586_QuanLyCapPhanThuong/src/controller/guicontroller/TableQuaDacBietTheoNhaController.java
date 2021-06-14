package controller.guicontroller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import app.QuanLyNhanKhau;
import controller.entitycontroller.QuaDacBietTheoNhaController;
import entity.ChiTietQuaDacBietTheoHo;
import entity.QuaDacBietTheoNha;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TableQuaDacBietTheoNhaController extends TableController<QuaDacBietTheoNha> {

    public TableQuaDacBietTheoNhaController (InsideMainFrameTab tab, int index, TextField sTextField) {
        super(tab, index, sTextField, new QuaDacBietTheoNhaController(), 
        new String[]{"ID", "diaChi", "hoTen", "ngay", "dip", "soQua", "soTien"},
        new String[]{"Số hộ gia đình", "Địa chỉ", "Họ tên chủ hộ", "Ngày phát quà", "Dịp", "Tổng quà", "Tổng tiền"},
        50);
        setAddForm("/fxml/ThongKeTheoNhaDialog.fxml");
        disable(4, toolBar.getItems().size());

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.getFilterList().setPredicate(thongke -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(thongke.getID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thongke.getDiaChi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thongke.getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(thongke.getNgay()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(thongke.getDip()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(thongke.getSoQua()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(thongke.getSoTien()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }

                return false;
            });
        });

        toolBar.getItems().get(6).setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);
                Parent root = createAForm("/fxml/ThongKeTheoNha.fxml");
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
        if(toolBar.getItems().size() > 4) toolBar.getItems().get(4).setDisable(true);
        if(toolBar.getItems().size() > 5) toolBar.getItems().get(5).setDisable(true);
        toolBar.getItems().get(6).setDisable(false);
    }

    @Override
    public void prepareAddRecord() {
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        ArrayList<String> ngayPhatQua = ((QuaDacBietTheoNhaController)controller).getNgayPhatQua();

        comboBox.getItems().addAll(ngayPhatQua);
        comboBox.setStyle("-fx-font: 18px \"System\";");
    }

    @Override
    public boolean addRecord() {
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        String date = comboBox.getSelectionModel().getSelectedItem();
        if(date == null || date.isBlank()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn ngày !!!");
            alert.showAndWait();
            return false;
        }
        ((QuaDacBietTheoNhaController)controller).setNgay(Date.valueOf(date));
        reloadPage();

        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongDacBietLabel");
        Label label = (Label)node;

        label.setText(" Ngày phát: " + date);
        label.setStyle("-fx-background-color: #ccebfa;");
        
        return true;
        
    }
    

    private void getDataForForm(Parent root){
        QuaDacBietTheoNha quaDacBietTheoNha = getSelectedItem();
        QuaDacBietTheoNhaController quaTheoNhaController = new QuaDacBietTheoNhaController();
        quaTheoNhaController.getDataTheoHo(quaDacBietTheoNha.getNgay(),
         quaDacBietTheoNha.getDip(), quaDacBietTheoNha.getDiaChi());
        quaTheoNhaController.creatFilterListPhatQua();

        for(Node node : root.lookupAll(".label")){
            Label label = (Label)node;
            if (label.getId() != null ) {
            if(label.getId().equals("soHo")){
                label.setText(String.valueOf(quaDacBietTheoNha.getID()));
            }
            if(label.getId().equals("diaChi")){
                label.setText(quaDacBietTheoNha.getDiaChi());
            }
            if(label.getId().equals("tongQua")){
                label.setText(String.valueOf(quaDacBietTheoNha.getSoQua()));
            }
            if(label.getId().equals("chuHo")){
                label.setText(quaDacBietTheoNha.getHoTen());
            } 
            if(label.getId().equals("tongTien")){
                label.setText(String.valueOf(quaDacBietTheoNha.getSoTien()));
            } 
        };
    }
    String[] listToDisplay = {"hoTen", "ngaySinh", "ngayNhan", "dip", "giaTri","moTa"};
    String[] listLabelColumn = {"Họ tên cháu nhỏ", "Ngày sinh", "Ngày nhận", "Dịp", "Giá trị", "Mô tả"};

    for(Node node2 : root.lookupAll(".table-view")){
        TableView<ChiTietQuaDacBietTheoHo> tableView = (TableView<ChiTietQuaDacBietTheoHo>) node2;
        service.TableLayout.layoutTable(tableView, Arrays.asList(listToDisplay), Arrays.asList(listLabelColumn));
        tableView.setItems(quaTheoNhaController.getFilterListPhatQua());
    }
}

    @Override
    public void onShowing(int index) {
        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongDacBietLabel");
        Label label = (Label)node;
        if(((QuaDacBietTheoNhaController)controller).getNgay() != null){
            label.setText(" Ngày phát: " + ((QuaDacBietTheoNhaController)controller).getNgay());
            label.setStyle("-fx-background-color: #ccebfa;");
        }
    }

    @Override
    public boolean updateRecord() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteRecord() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
