package controller.guicontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.QuanLyNhanKhau;
import controller.entitycontroller.TrangChuController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Screen;
import service.DatabaseConnection;

public class MainFrameGUIController extends Node implements Initializable {

    @FXML
    private TabPane root;
    private ArrayList<InsideMainFrameTab> tabs = new ArrayList<>();

    @FXML
    private Label lb_nhankhau, lb_hogiadinh, lb_sochaunho, lb_sohocsinh;

    @FXML
    private Label lb_hovaten, lb_username, lb_chucvu, lb_sodienthoai, lb_diachi, lb_email, lb_facebook;

    @FXML
    private Button dangXuat;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        dangXuat.setOnAction(s -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                Parent root = fxmlLoader.load(getClass().getResource("/fxml/login.fxml").openStream());
                Scene scene = new Scene(root);
                QuanLyNhanKhau.primaryStage.setScene(scene);
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                QuanLyNhanKhau.primaryStage.setX((primScreenBounds.getWidth() - QuanLyNhanKhau.primaryStage.getWidth()) / 2);
                QuanLyNhanKhau.primaryStage.setY((primScreenBounds.getHeight() - QuanLyNhanKhau.primaryStage.getHeight()) / 2);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            lb_nhankhau.setText(TrangChuController.countNhanKhau());
            lb_hogiadinh.setText(TrangChuController.countHoGiaDinh());
            lb_sochaunho.setText(TrangChuController.countChauNho());
            lb_sohocsinh.setText(TrangChuController.countHocSinh());
            TrangChuController.setDataUser();
            lb_hovaten.setText(TrangChuController.user.getHoTen());
            lb_username.setText(TrangChuController.user.getUserName());
            lb_chucvu.setText(TrangChuController.user.getChucVu());
            lb_sodienthoai.setText(TrangChuController.user.getDienthoai());
            lb_diachi.setText(TrangChuController.user.getDiaChi());
            lb_email.setText(TrangChuController.user.getEmail());
            lb_facebook.setText(TrangChuController.user.getFacebook());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void init(Parent root) {
        try {
            this.root = (TabPane)root;
        } catch (Exception e) {
            System.out.println("MainGUIController erorr!!!");
        }

        ObservableList<Tab> allTabs = this.root.getTabs();
        allTabs.get(0).setOnSelectionChanged(s->{
            try {
                lb_nhankhau.setText(TrangChuController.countNhanKhau());
                lb_hogiadinh.setText(TrangChuController.countHoGiaDinh());
                lb_sochaunho.setText(TrangChuController.countChauNho());
                lb_sohocsinh.setText(TrangChuController.countHocSinh());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        
        for (int i = 0; i < allTabs.size(); i++) {
            Parent curRoot = ((Parent) allTabs.get(i).getContent());

            tabs.add(new InsideMainFrameTab(curRoot.lookupAll(".toggle-button"), 
                curRoot.lookupAll(".anchor-pane"), curRoot.lookup(".stack-pane"), curRoot.lookupAll(".tool-bar"), 
                curRoot.lookupAll(".label"), curRoot.lookupAll(".text-field"), curRoot.lookupAll(".button")));
        }


        PhanQuaGuiController phanQuaGuiController =
            new PhanQuaGuiController(tabs.get(3).getTable(0), tabs.get(3).getToolBar()[0], 
            tabs.get(3).getTextField()[0]);

        ChauNhoNhanQuaTableController chauNhoNhanQuaTableController =
            new ChauNhoNhanQuaTableController(tabs.get(2), 0, tabs.get(2).getTextField()[0]);
        ThuChiQuyGUIController thuChiQuyGUIController =
            new ThuChiQuyGUIController(tabs.get(3).getTable(1), tabs.get(3).getToolBar()[1], tabs.get(3).getTextField()[1], 
            tabs.get(3).getLabel()[1]);
        DSHocSinhController dsHocSinhController = new DSHocSinhController(tabs.get(1), 0,
         tabs.get(1).getTextField()[0]);
        TableThongKeKhuyenHocTheoNha khuyenHocTheoNhaController = new TableThongKeKhuyenHocTheoNha(tabs.get(1), 1,
         tabs.get(1).getTextField()[1]);
        TableThongKeKhuyenHocTheoDip tableThongKeKhuyenHocTheoDip = new TableThongKeKhuyenHocTheoDip(tabs.get(1), 2,
        tabs.get(1).getTextField()[2]);
        TableQuaDacBietTheoNhaController tableQuaDacBietTheoNhaController = new TableQuaDacBietTheoNhaController(tabs.get(2), 1, 
        tabs.get(2).getTextField()[1]);
        TableQuaDacBietTheoDipController tableQuaDacBietTheoDipController = new TableQuaDacBietTheoDipController(tabs.get(2), 2,
        tabs.get(2).getTextField()[2]);
        
        
    }

    public void onClose(){
        DatabaseConnection.closeConnection();
    }

    public void print(){
        /* for(Object obj : InsideMainFrameTab.list1){
            System.out.println(((Demo)obj).getName());
        } */
    }

}
