package controller.guicontroller;

import java.util.Arrays;

import app.QuanLyNhanKhau;
import controller.entitycontroller.EntityController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class TableController<T> {

    private String addForm = "/fxml/FormDemo.fxml";
    private String updateForm = "/fxml/FormDemo.fxml";
    
    protected TableView<T> table;
    protected ToolBar toolBar;

    protected TextField textField;

    protected int page = 0;
    protected boolean hasNext = true;

    protected EntityController<T> controller;
    protected int numRecordPerPage;

    protected int selectedRow = -1;
    protected T selectedItem = null;

    protected Parent addFormRoot = null;
    protected Parent updateFormRoot = null;

    public TableController(TableView<T> table, ToolBar toolBar, EntityController<T> controller,
            String[] displayColumnList, int numRecordPerPage) {
        this.toolBar = toolBar;
        this.table = table;
        this.controller = controller;
        this.numRecordPerPage = numRecordPerPage;

        controller.getDataFromDatabase(numRecordPerPage, 0);

        service.TableLayout.layoutTable(table, controller.getMetaData(), Arrays.asList(displayColumnList));

        table.setItems(FXCollections.observableArrayList(controller.getList()));

        toolBar.getItems().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if (hasNext) {
                    page++;
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    if(controller.getList().size() == 0){
                        page--;
                    }else{
                        if (controller.getList().size() != numRecordPerPage - 1)
                        hasNext = false;
                        table.setItems(FXCollections.observableArrayList(controller.getList()));
                    }
                    
                }
            }

        });

        toolBar.getItems().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if (page > 0) {
                    page--;
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    table.setItems(FXCollections.observableArrayList(controller.getList()));
                    hasNext = true;
                }
                else {
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    table.setItems(FXCollections.observableArrayList(controller.getList()));
                    hasNext = true;
                }
            }

        });

        setUpAddButton();
        setUpDeleteButton();
        setUpUpdateButton();

        table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<T>() {

            @Override
            public void onChanged(Change<? extends T> arg0) {
                if(arg0.getList().size() != 0)
                {
                    selectedItem = arg0.getList().get(0);
                    selectedRow = table.getSelectionModel().getSelectedIndex();
                    toolBar.getItems().get(4).setDisable(false);
                    toolBar.getItems().get(5).setDisable(false);
                }
            }
            
        });
        

    }

    public TableController(TableView<T> table, ToolBar toolBar, TextField textField, 
    EntityController<T> controller, String[] displayColumnList, String[] listLabelsToDisplay, int numRecordPerPage) {
        this.textField = textField;
        this.toolBar = toolBar;
        this.table = table;
        this.controller = controller;
        this.numRecordPerPage = numRecordPerPage;

        controller.getDataFromDatabase(numRecordPerPage, 0);
        controller.creatFilterList();

        service.TableLayout.layoutTable(table, Arrays.asList(displayColumnList), Arrays.asList(listLabelsToDisplay));

        table.setItems(controller.getFilterList());

        toolBar.getItems().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                controller.getDataFromDatabase();
                controller.creatFilterList();
                table.setItems(controller.getFilterList());
            }
        });

        toolBar.getItems().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if (hasNext) {
                    page++;
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    if(controller.getList().size() == 0){
                        hasNext = false;
                        page--;
                    }else{
                        //if (controller.getList().size() != numRecordPerPage - 1)
                        //hasNext = false;
                        controller.getDataFromDatabase(numRecordPerPage, page);
                        controller.creatFilterList();
                        table.setItems(controller.getFilterList());
                    }
                    
                }
            }

        });

        toolBar.getItems().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if (page > 0) {
                    page--;
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    controller.creatFilterList();
                    table.setItems(controller.getFilterList());
                    hasNext = true;
                }
                else {
                    controller.getDataFromDatabase(numRecordPerPage, page);
                    controller.creatFilterList();
                    table.setItems(controller.getFilterList());
                    hasNext = true;
                }
            }

        });

        setUpAddButton();
        setUpDeleteButton();
        setUpUpdateButton();

        table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<T>() {

            @Override
            public void onChanged(Change<? extends T> arg0) {
                if(arg0.getList().size() != 0)
                {
                    selectedItem = arg0.getList().get(0);
                    selectedRow = table.getSelectionModel().getSelectedIndex();
                    toolBar.getItems().get(4).setDisable(false);
                    toolBar.getItems().get(5).setDisable(false);
                    firstClickOnTable();
                }
            }
            
        });

    }

    public TableController(InsideMainFrameTab tab, int index, TextField textField, EntityController<T> controller,
        String[] displayColumnList, String[] listLabelsToDisplay, int numRecordPerPage){
        this(tab.getTable(index), tab.getToolBar()[index],textField,controller, displayColumnList, listLabelsToDisplay, numRecordPerPage);
        tab.getTableControllers().add(this);
    }

    private void setUpUpdateButton() {

        toolBar.getItems().get(4).setDisable(true);
        //delete button
        toolBar.getItems().get(4).setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {
                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);

                    Scene scene;

                    updateFormRoot = FXMLLoader.load(getClass().getResource(updateForm));
                    
                    scene = new Scene(updateFormRoot); 

                    Stage demo = new Stage();

                    demo.setResizable(false);
                    demo.setScene(scene); 
                    demo.setAlwaysOnTop(true);

                    prepareUpdateRecord();

                    for(Node node : updateFormRoot.lookupAll(".button")){
                        String s = ((Button)node).getText();
                        if(s.equals("Apply")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    demo.setAlwaysOnTop(false);
                                    if(updateRecord()){
                                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                        demo.close();
                                    }
                                    demo.setAlwaysOnTop(true);
                                } 
                            });
                        }

                        if(s.equals("Cancel")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                    demo.close();
                                } 
                            });
                        }
                    }

                    demo.setOnCloseRequest(s->{
                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                        demo.close();
                    });

                    demo.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            
        });

    }

    private void setUpDeleteButton() {
        toolBar.getItems().get(5).setDisable(true);
        //delete button
        toolBar.getItems().get(5).setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {
                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);
                
                    Parent warningRoot = FXMLLoader.load(getClass().getResource("/fxml/Warning.fxml"));
                    
                    Scene scene = new Scene(warningRoot); 
                    Stage demo = new Stage();

                    demo.setResizable(false);
                    demo.setScene(scene); 
                    demo.setAlwaysOnTop(true);

                    for(Node node : warningRoot.lookupAll(".button")){
                        String s = ((Button)node).getText();
                        if(s.equals("OK")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                    deleteRecord();
                                    demo.close();
                                } 
                            });
                        }

                        if(s.equals("Cancel")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                    demo.close();
                                } 
                            });
                        }
                    }

                    demo.setOnCloseRequest(s->{
                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                        demo.close();
                    });

                    demo.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            
        });
    }

    private void setUpAddButton() {
        //Add record button
        toolBar.getItems().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                try { 
                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);

                    addFormRoot = FXMLLoader.load(getClass().getResource(addForm));
                    
                    Scene scene = new Scene(addFormRoot); 
                    Stage demo = new Stage();

                    demo.setResizable(false);
                    demo.setScene(scene); 
                    demo.setAlwaysOnTop(true);

                    prepareAddRecord();
                    

                    for(Node node : addFormRoot.lookupAll(".button")){
                        String s = ((Button)node).getText();
                        if(s.equals("Apply")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    demo.setAlwaysOnTop(false);
                                    if(addRecord()){
                                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                        demo.close();
                                    }
                                    demo.setAlwaysOnTop(true);
                                } 
                            });
                        }

                        if(s.equals("Cancel")){
                            ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                                @Override 
                                public void handle(ActionEvent e) { 
                                    QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                                    demo.close();
                                } 
                            });
                        }
                    }

                    demo.setOnCloseRequest(s->{
                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                        demo.close();
                    });


                    demo.show();
                    

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            
        });
    }

    public void reloadPage(){
        controller.getDataFromDatabase(numRecordPerPage, page);
        controller.creatFilterList();
        table.setItems(controller.getFilterList());
        
        // table.setItems(FXCollections.observableArrayList(controller.getList()));
    }

    public TableView<T> getTable() {
        return table;
    }

    public void setTable(TableView<T> table) {
        this.table = table;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public EntityController<T> getController() {
        return controller;
    }

    public void setController(EntityController<T> controller) {
        this.controller = controller;
    }

    public int getSelectedRow(){
        return selectedRow;
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setAddForm(String addForm) {
        this.addForm = addForm;
    }

    public String getUpdateForm() {
        return updateForm;
    }

    public void setUpdateForm(String updateForm) {
        this.updateForm = updateForm;
    }

    public void showAForm(Parent parent){
        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(true);
        Stage demo = new Stage();
        Scene scene = new Scene(parent); 

        demo.setResizable(false);
        demo.setScene(scene); 
        demo.setAlwaysOnTop(true);

        for(Node node : parent.lookupAll(".button")){
            String s = ((Button)node).getText();
            if(s.equals("Apply")){
                ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                    @Override 
                    public void handle(ActionEvent e) { 
                        demo.setAlwaysOnTop(false);
                        if(actionOnForm()){
                            QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                            demo.close();
                        }
                        demo.setAlwaysOnTop(true);
                    } 
                });
            }

            if(s.equals("Cancel")){
                ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                    @Override 
                    public void handle(ActionEvent e) { 
                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                        demo.close();
                    } 
                });
            }
        }

        demo.setOnCloseRequest(s->{
            QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
            demo.close();
        });
        
        demo.show();
    }

    public boolean actionOnForm(){
        return true;
    }

    public Parent createAForm(String path){
        try {

            Parent outParent = FXMLLoader.load(getClass().getResource(path));
            return outParent;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       
    }

    public void disable(int start, int end){
        int index = 0;
        for (Node node : toolBar.getItems()) {
            if(index >= start && index < end) node.setDisable(true);
            index++;
        }
    }
    
    public void firstClickOnTable(){};

    public void prepareAddRecord(){};
    public abstract boolean addRecord();

    public void prepareUpdateRecord(){};
    public abstract boolean updateRecord();

    public boolean deleteRecord(){
        controller.deleteRecord(selectedItem);
        reloadPage();
        return true;
    }

    public void onShowing(int index){}

    public String getAddForm() {
        return addForm;
    }

}
