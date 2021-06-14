package controller.guicontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.QuanLyNhanKhau;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;
import service.DatabaseConnection;
import controller.entitycontroller.TrangChuController;


public class LoginGUIController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    public void login(ActionEvent event) throws IOException {
        String useName;
        String passWord;
        useName = username.getText();
        passWord = password.getText();
        ResultSet resultSet = DatabaseConnection.executeQuery(
                "select * from users where userName = '" + useName + "' and password = '" + passWord + "';");

        try {
            if (resultSet.next()) {
                try {
                    TrangChuController.user.setUserName(resultSet.getString("userName"));
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                MainFrameGUIController mainFrameController;
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent homePage = fxmlLoader.load(getClass().getResource("/fxml/MainFrame.fxml").openStream());
                // Node node = (Node) event.getSource();
                // Stage stage = (Stage)node.getScene().getWindow();
                Scene scene = new Scene(homePage);
                mainFrameController = (MainFrameGUIController) fxmlLoader.getController();
                mainFrameController.init(homePage);
                scene.getStylesheets().add("/css/MainFrameCss.css");
                QuanLyNhanKhau.primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<Event>(){
                    @Override 
                    public void handle(Event e) { 
                        mainFrameController.onClose();
                    } 
                });
                QuanLyNhanKhau.primaryStage.setResizable(true);
                QuanLyNhanKhau.primaryStage.setScene(scene);
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                QuanLyNhanKhau.primaryStage.setX((primScreenBounds.getWidth() - QuanLyNhanKhau.primaryStage.getWidth()) / 2);
                QuanLyNhanKhau.primaryStage.setY((primScreenBounds.getHeight() - QuanLyNhanKhau.primaryStage.getHeight()) / 2);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
            
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }
        
}
