package controller.guicontroller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import app.QuanLyNhanKhau;
import controller.entitycontroller.ChauNhoNhanQuaController;
import controller.entitycontroller.QuyController;
import controller.entitycontroller.TrangChuController;
import entity.ChauNhoNhanQua;
import entity.PhanQua;
import entity.ThuChiQuy;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class ChauNhoNhanQuaTableController extends TableController<ChauNhoNhanQua> {
    private EventHandler<Event> event = null;
    private int sumGift;
    private int sumValue;
    int iDPhanQua;

    public ChauNhoNhanQuaTableController(InsideMainFrameTab tab, int index, TextField textField) {
        super(tab, index, textField, new ChauNhoNhanQuaController(),
                new String[] { "ID", "hoTen", "gioiTinh", "ngaySinh", "tuoi", "diaChi", "phuHuynh" },
                new String[] { "ID nhân khẩu", "Họ tên", "Giới tính", "Ngày sinh", "Tuổi", "Địa chỉ", "Phụ huynh" },
                40);
        setAddForm("/fxml/ThemNhanKhau.fxml");
        setUpdateForm("/fxml/ThemNhanKhau.fxml");
        
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.getFilterList().setPredicate(chauNho -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(chauNho.getID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(chauNho.getTuoi()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (chauNho.getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (chauNho.getDiaChi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (chauNho.getGioiTinh().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (chauNho.getPhuHuynh().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(chauNho.getNgaySinh()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });

        toolBar.getItems().get(7).setOnMouseClicked(s -> {
            Parent giveGiftForm = createAForm("/fxml/FormPhatQuaDipDacBiet.fxml");
            giveGiftShowing(giveGiftForm);
            showAForm(giveGiftForm);
        });

        toolBar.getItems().get(6).setDisable(true);
    }


    @Override
    public void prepareAddRecord() {
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        comboBox.getItems().addAll("Nam", "Nữ");
        comboBox.setStyle("-fx-font: 20px \"System\";"); 

        DatePicker datePicker = (DatePicker)addFormRoot.lookup(".date-picker");
        datePicker.setStyle("-fx-font: 20px \"System\";-fx-background-color: white; -fx-text-fill: black;");
    }

    @Override
    public boolean addRecord() {
        ChauNhoNhanQua chauNhoNhanQua = new ChauNhoNhanQua();

        String gioiTinh = null;
        for(Node node : addFormRoot.lookupAll(".combo-box")){
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>)node;
            gioiTinh = comboBox.getSelectionModel().getSelectedItem();
        }
        if(gioiTinh == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn giới tính nhân khẩu !!!");
            alert.showAndWait();
            return false;
        }     
        chauNhoNhanQua.setGioiTinh(gioiTinh);

        for(Node node : addFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;
            
            if (textField.getPromptText() == null)
              continue;

            if(textField.getPromptText().equals("Họ tên")){
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Họ tên không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                chauNhoNhanQua.setHoTen(textField.getText());
            }

            if(textField.getPromptText().equals("Địa chỉ")){
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Địa chỉ không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                if (!ChauNhoNhanQuaController.searchDiaChi(textField.getText())) {
                    Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText("Địa chỉ không hợp lệ");
                        alert.setContentText("Không có địa chỉ gia đình này");
                        alert.showAndWait();
                        return false;
                }
                chauNhoNhanQua.setDiaChi(textField.getText());
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
        chauNhoNhanQua.setNgaySinh(Date.valueOf(datePicker.getValue()));

        controller.addRecord(chauNhoNhanQua);
        reloadPage();
        return true;
    }

    @Override
    public void prepareUpdateRecord() {
        getDataForForm(updateFormRoot);
        for (Node node : updateFormRoot.lookupAll(".label")) {
            Label label = (Label) node;

            if (label.getText().equals("THÊM NHÂN KHẨU")) {
                label.setText("SỬA ĐỔI THÔNG TIN NHÂN KHẨU");
                label.setTranslateX(-80);
                // label.setAlignment(Pos.CENTER);
            }
        }

        for(Node node : updateFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;
            if(textField.getPromptText().equals("Địa chỉ")){
                textField.setEditable(false);
            }
        }
    }
    


    private void getDataForForm(Parent root){
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>)root.lookup(".combo-box");
        comboBox.getItems().addAll("Nam", "Nữ");
        comboBox.setStyle("-fx-font: 20px \"System\";");

        DatePicker datePicker = (DatePicker)updateFormRoot.lookup(".date-picker");
        datePicker.setStyle("-fx-font: 20px \"System\";-fx-background-color: white; -fx-text-fill: black;");

        ChauNhoNhanQua chauNhoNhanQua = getSelectedItem();
        if(chauNhoNhanQua.getGioiTinh().equals("Nam") || chauNhoNhanQua.getGioiTinh().equals("nam")){
            comboBox.getSelectionModel().select("Nam");
        }else{
            comboBox.getSelectionModel().select("Nữ");
        };
        
        for(Node node : root.lookupAll(".text-field")){
            TextField textField = (TextField)node;

            if(textField.getPromptText().equals("Họ tên")){
                textField.setText(chauNhoNhanQua.getHoTen());
            }

            if(textField.getPromptText().equals("Địa chỉ")){
                textField.setText(chauNhoNhanQua.getDiaChi());
            }
        };

        datePicker.setValue(LOCAL_DATE(String.valueOf(chauNhoNhanQua.getNgaySinh())));

    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }




    @Override
    public boolean updateRecord() {
        ChauNhoNhanQua chauNhoNhanQua = getSelectedItem();
        String gioiTinh = null;
        for(Node node : updateFormRoot.lookupAll(".combo-box")){
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>)node;
            gioiTinh = comboBox.getSelectionModel().getSelectedItem();
        }
        if(gioiTinh == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn giới tính !!!");
            alert.showAndWait();
            return false;
        }
        chauNhoNhanQua.setGioiTinh(gioiTinh);

        for(Node node : updateFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;

            if(textField.getPromptText() == null) continue;

            if(textField.getPromptText().equals("Họ tên")){
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Họ tên không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                chauNhoNhanQua.setHoTen(textField.getText());
            }

            if(textField.getPromptText().equals("Địa chỉ")){
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Mô tả không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                chauNhoNhanQua.setDiaChi(textField.getText());
            }
        }

        DatePicker datePicker = (DatePicker) updateFormRoot.lookup(".date-picker");
        if(datePicker.getValue() == null){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!!!");
        alert.setContentText("Chưa chọn ngày !!!");
        alert.showAndWait();
        return false;
        }
        chauNhoNhanQua.setNgaySinh(Date.valueOf(datePicker.getValue()));

        controller.updateRecord(chauNhoNhanQua);
        reloadPage();
        return true;
    }

    @Override
    public boolean deleteRecord() {
        controller.deleteRecord(selectedItem);
        reloadPage();
        return true;
    }

    private void giveGiftShowing(Parent root) {
        ArrayList<PhanQua> phanQua = ((ChauNhoNhanQuaController) controller).getAllGift();
        root.lookup(".date-picker").setStyle("-fx-font: 20px \"System\";");
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) root.lookup(".combo-box");
        comboBox.setStyle("-fx-font: 20px \"System\";");
        for (int i = 0; i < phanQua.size(); i++) {
            comboBox.getItems().add(phanQua.get(i).getMoTa());
        }

        for (Node node : root.lookupAll(".button")) {
            Button button = (Button) node;
            if (button.getText().equals("Dự tính")) {
                button.setOnMouseClicked(s -> {
                    if (comboBox.getSelectionModel().getSelectedIndex() == -1) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText("Chưa chọn đủ phần quà");
                        alert.setContentText("Chưa chọn đủ phần quà");
                        ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
                        alert.showAndWait();
                        ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
                        return;
                    }
                    int index = comboBox.getSelectionModel().getSelectedIndex();
                    sumValue = 0;
                    sumGift = 0;

                    for (int i = 0; i < ((ChauNhoNhanQuaController) controller).countChauNho(); i++) {
                        sumGift++;
                    }
                    sumValue = sumGift * (phanQua.get(index).getGiaTriInt());
                    iDPhanQua = phanQua.get(index).getID();

                    for (Node node1 : root.lookupAll(".text-field")) {
                        TextField textField = (TextField) node1;
                        if (textField.getPromptText() == null)
                            continue;
                        if (textField.getPromptText().equals("Tổng quà")) {
                            textField.setText(sumGift + " suất");
                        }
                        if (textField.getPromptText().equals("Tổng tiền")) {
                            DecimalFormat df = new DecimalFormat();
                            DecimalFormatSymbols dcms = new DecimalFormatSymbols();
                            dcms.setGroupingSeparator('.');
                            df.setDecimalFormatSymbols(dcms);
                            df.setGroupingSize(3);
                            textField.setText(df.format(sumValue) + " VND");
                        }
                    }
                });
            }

            if (button.getText().equals("Apply")) {

                event = new EventHandler<Event>() {

                    @Override
                    public void handle(Event arg0) {

                    }

                    @Override
                    public boolean equals(Object obj) {
                        return onClickApplyButton(root, iDPhanQua);
                    }

                };

            }
    }
    }

    public boolean onClickApplyButton(Parent root, int iDPhanQua) {
        String dip = "";
        for (Node node1 : root.lookupAll(".text-field")) {
            TextField textField = (TextField) node1;
            if (textField.getText() == null || textField.getText().isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Điền đẩy đủ thông tin");
                alert.setContentText("Hãy dự tính trước khi làm nhá!!!");
                ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
                alert.showAndWait();
                ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
                return false;
            }
            if (textField.getPromptText() != null && textField.getPromptText().equals("Dịp phát quà")) {
                dip = textField.getText();
            }
        int soDuQuy = Integer.parseInt(QuyController.xemQuy());
        if (sumValue > soDuQuy) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Không đủ tiền quỹ");
            alert.setContentText("Số quỹ hiện tại không đủ : " + String.valueOf(soDuQuy) + " VNĐ");
            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
            alert.showAndWait();
            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
            return false;
            }
        }

        DatePicker datePicker = ((DatePicker) root.lookup(".date-picker"));
        try {
            ((ChauNhoNhanQuaController) controller).giveGift(Date.valueOf(datePicker.getValue()), dip, iDPhanQua,
                    sumGift, sumValue);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            ThuChiQuy thuChiQuy = new ThuChiQuy(1, datePicker.getValue().toString(), sumValue, "Chi", TrangChuController.user.getUserName(), "Mua quà dịp đặc biệt");
            ThuChiQuyGUIController.chiQuy(thuChiQuy, sumValue);
            return true;
    }

    @Override
    public void onShowing(int index) {
        System.out.println("hasdea");
        Label label = (Label)QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongDacBietLabel");
        label.setText(null);
        label.setStyle("-fx-background-color: transparent;");
    }
        
    @Override
    public boolean actionOnForm() {
        return event.equals(null);
    }

}

                


    

