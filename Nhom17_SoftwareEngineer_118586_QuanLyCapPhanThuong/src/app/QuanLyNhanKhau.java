package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.DatabaseConnection;


public class QuanLyNhanKhau extends Application {

    public static Stage primaryStage = null;

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection.initializeConnection("localhost", "3306");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/login.fxml").openStream());

        QuanLyNhanKhau.primaryStage = primaryStage;
        Scene scene = new Scene(root);
        primaryStage.setTitle("Quản lý cấp phần thưởng"); 
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
            
    }
   
            
}