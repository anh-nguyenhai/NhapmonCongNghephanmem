package controller.guicontroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.entitycontroller.QuyController;
import controller.entitycontroller.ThuChiQuyController;
import controller.entitycontroller.TrangChuController;
import entity.ThuChiQuy;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ThuChiQuyGUIController extends TableController<ThuChiQuy> {
    private static Label soDuQuy;

    public ThuChiQuyGUIController(TableView<ThuChiQuy> table, ToolBar toolBar, TextField sTextField, Label label){
        super(table, toolBar, sTextField, new ThuChiQuyController(),
        new String[]{"ngay","soTien","loaiThuChi","userNguoiThucHien","moTa"},
        new String[]{"Ngày", "Số tiền", "Loại thu chi", "Người thực hiện", "Mô tả"},
        40);
        soDuQuy = label;
        updateQuy();
        setAddForm("/fxml/Form Thu chi quỹ.fxml");


        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.getFilterList().setPredicate(thuChi -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (thuChi.getMoTa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thuChi.getUserNguoiThucHien().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thuChi.getLoaiThuChi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thuChi.getNgay().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (thuChi.getSoTien().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });

        toolBar.getItems().get(6).setDisable(true);
        toolBar.getItems().get(6).setOnMouseClicked(new EventHandler<MouseEvent>(){
           
            @Override
            public void handle(MouseEvent arg0) {
                Parent root = createAForm("/fxml/Form Thu chi quỹ.fxml");

                getDataForForm(root);
                
                for(Node node : root.lookupAll(".label")){
                    Label label = (Label)node;
                   
                        label.setText("Thông tin chi tiết");
                        label.setPrefWidth(250);
                        label.setLayoutX(340);
                }

                for (Node node : root.getChildrenUnmodifiable()) {
                    if(node instanceof TextField){
                        ((TextField)node).setEditable(false);
                    }
                    if(node instanceof ComboBox){
                        ((ComboBox<String>)node).setStyle("-fx-font: 18px \"System\";-fx-opacity: 1; -fx-background-color: white; -fx-text-fill: black;");
                        ((ComboBox<String>)node).setDisable(true);
                    }
                    if(node instanceof DatePicker){
                        ((DatePicker)node).setStyle("-fx-font: 20px \"System\";-fx-opacity: 1; -fx-background-color: white; -fx-text-fill: black;");
                        ((DatePicker)node).setDisable(true);
                    }
                }

                showAForm(root);
    }
});
    }

  @Override
public void firstClickOnTable() {
    toolBar.getItems().get(6).setDisable(false);
    toolBar.getItems().get(4).setDisable(true);
    toolBar.getItems().get(5).setDisable(true);
} 

@Override
    public void prepareAddRecord() {
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        comboBox.getItems().addAll("Thu", "Chi");
        comboBox.setStyle("-fx-font: 20px \"System\";"); 
        DatePicker datePicker = (DatePicker)addFormRoot.lookup(".date-picker");
        datePicker.setStyle("-fx-font: 20px \"System\";-fx-background-color: white; -fx-text-fill: black;");
        
        for(Node node : addFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;
            if(textField.getPromptText().equals("Người thực hiện")){
                textField.setDisable(true);
                textField.setText(TrangChuController.user.getUserName());
            }
        }
    }
    @Override
    public boolean addRecord() {
        ThuChiQuy thuChiQuy = new ThuChiQuy();

        String loaiThuChi = null;
        for(Node node : addFormRoot.lookupAll(".combo-box")){
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>)node;
            loaiThuChi = comboBox.getSelectionModel().getSelectedItem();
        }
        if(loaiThuChi == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn loại hành động !!!");
            alert.showAndWait();
            return false;
        }
        thuChiQuy.setLoaiThuChi(loaiThuChi);


        for(Node node : addFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;

            if(textField.getPromptText().equals("Số tiền")){
                try {
                    thuChiQuy.setSoTien(Integer.parseInt(textField.getText()));
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Giá trị must be an integer");
                    alert.showAndWait();
                    return false;
                }
            }

            if(textField.getPromptText().equals("Mô tả")){
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Mô tả không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                thuChiQuy.setMoTa(textField.getText());
            }

        }

        DatePicker datePicker = (DatePicker) addFormRoot.lookup(".date-picker");
        if(datePicker.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn ngày !!!");
            alert.showAndWait();
            return false;
        }
        String ngay = datePicker.getValue().toString();
        thuChiQuy.setNgay(ngay);
        thuChiQuy.setUserNguoiThucHien(TrangChuController.user.getUserName());
        controller.addRecord(thuChiQuy);
        if (thuChiQuy.getLoaiThuChi() == "Thu") QuyController.thuQuy(thuChiQuy.getSoTienInt());
        else QuyController.chiQuy(thuChiQuy.getSoTienInt());
        updateQuy();
        reloadPage();
        return true;
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
    
    private void getDataForForm(Parent root){
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>)root.lookup(".combo-box");
        comboBox.getItems().addAll("Thu quỹ", "Chi quỹ");
        comboBox.setStyle("-fx-font: 20px \"System\";");

        ThuChiQuy thuChiQuy = getSelectedItem();
        if(thuChiQuy.getLoaiThuChi().equals("Thu quỹ") || thuChiQuy.getLoaiThuChi().equals("thu quỹ")){
            comboBox.getSelectionModel().select("Thu quỹ");
        }else{
            comboBox.getSelectionModel().select("Chi quỹ");
        }

        for(Node node : root.lookupAll(".text-field")){
            TextField textField = (TextField)node;

            if(textField.getPromptText().equals("Người thực hiện")){
                textField.setText(thuChiQuy.getUserNguoiThucHien());
            }

            if(textField.getPromptText().equals("Số tiền")){
                textField.setText("" + thuChiQuy.getSoTien());
            }

            if(textField.getPromptText().equals("Mô tả")){
                textField.setText(thuChiQuy.getMoTa());
            }
                   
        DatePicker datePicker = (DatePicker) root.lookup(".date-picker");
        datePicker.setValue(LOCAL_DATE(thuChiQuy.getNgay()));
        }
    }

    // public static void updateQuy(){
    //     String s = format(QuyController.xemQuy());
    //     soDuQuy.setText(s + " VNĐ");
    // }

    public static void updateQuy(){
        soDuQuy.setText(format(QuyController.xemQuy())+ " VND");
    }

    public static void chiQuy(ThuChiQuy thuChiQuy, int soTienChiQuy){
        ThuChiQuyController.themThuChi(thuChiQuy);
        QuyController.chiQuy(soTienChiQuy);
        updateQuy();
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public static String format(String s){
        int count = 1;
        while(count != s.length()+1){
            if ((count % 4) == 0) {
                s = s.substring(0,s.length() - (count-1)) + "," + s.substring(s.length() - (count-1)); 
            } 
            count ++;
        }
        return s;
    }
    
}
