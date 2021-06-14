package controller.guicontroller;

import app.QuanLyNhanKhau;
import controller.entitycontroller.QuaDacBietTheoDipController;
import entity.ChiTietQuaDacBietTheoDip;
import entity.QuaDacBietTheoDip;
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
public class TableQuaDacBietTheoDipController  extends TableController<QuaDacBietTheoDip>{

    public TableQuaDacBietTheoDipController(InsideMainFrameTab tab, int index, TextField sTextField) {
        super(tab, index, sTextField, new QuaDacBietTheoDipController(), 
        new String[]{"date","idQua", "tongQua", "tongTien"}, 
        new String[]{"Ngày phát", "ID phần quà", "Tổng quà đã phát", "Tổng tiền"}, 
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
                if (String.valueOf(thongke.getIdQua()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongDacBietLabel");
        Label label = (Label)node;

        label.setText(null);
        label.setStyle("-fx-background-color: transparent;");
    }

    private void getDataForForm(Parent root){

        QuaDacBietTheoDip quaDacBietTheoDip = getSelectedItem();
        QuaDacBietTheoDipController quaDacBietTheoDipController = new QuaDacBietTheoDipController();
        quaDacBietTheoDipController.getDataTheoDip(quaDacBietTheoDip.getDate());
        quaDacBietTheoDipController.creatFilterListPhatQua();
        for(Node node : root.lookupAll(".label")){
            Label label = (Label)node;
            if (label.getId() != null ) {
            if(label.getId().equals("ngayPhat")){
                label.setText("" + quaDacBietTheoDip.getDate());
            }
            if(label.getId().equals("tongQua")){
                label.setText(quaDacBietTheoDip.getTongQua());
            }
            if(label.getId().equals("tongTien")){
                label.setText(quaDacBietTheoDip.getTongTien());
            }
        };

        String[] listToDisplay = {"hoTen", "ngaySinh", "diaChiHienNay", "ngay", "dip","giaTri", "moTa"};
        String[] listLabelColumn = {"Họ tên", "Ngày sinh", "Địa chỉ", "Ngày nhận quà", "Dịp", "Giá trị", "Mô tả"};

 
        for(Node node2 : root.lookupAll(".table-view")){
        TableView<ChiTietQuaDacBietTheoDip> tableView = (TableView<ChiTietQuaDacBietTheoDip>) node2;
             service.TableLayout.layoutTable(tableView, Arrays.asList(listToDisplay), Arrays.asList(listLabelColumn));
             tableView.setItems(quaDacBietTheoDipController.getFilterListPhatQua());
        }

        TextField text = (TextField) root.lookup(".text-field");
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            quaDacBietTheoDipController.getFilterListPhatQua().setPredicate(phatQua -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (phatQua.getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(phatQua.getNgaySinh()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getDiaChiHienNay().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(phatQua.getNgay()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getDip().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getGiaTri().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (phatQua.getMoTa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        

        }
    }

	@Override
	public boolean addRecord() {
		// TODO Auto-generated method stub
		return false;
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
