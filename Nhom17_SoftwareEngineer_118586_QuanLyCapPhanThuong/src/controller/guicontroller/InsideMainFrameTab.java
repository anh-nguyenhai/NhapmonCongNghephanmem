package controller.guicontroller;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class InsideMainFrameTab {
    // toggleButton[i] ---> pane[i] and a pane[i] has a table view
    private Node[] toggleButtons;
    private Node[] pane;
    private StackPane stackPane;
    private ToolBar[] toolBars;
    private Label[] label;
    private TextField[] textField;
    public static ObservableList<Object> list1;

    private ArrayList<TableController> tableControllers;

    InsideMainFrameTab(Set<Node> buttons, Set<Node> inPane, Node inStackPane, Set<Node> intoolBars, Set<Node> label
    , Set<Node> textField, Set<Node> button){
        this.pane = inPane.toArray(new Node[0]);
        this.toggleButtons = buttons.toArray(new Node[0]);
        this.stackPane = (StackPane)inStackPane;
        this.toolBars = intoolBars.toArray(new ToolBar[0]);
        this.label = label.toArray(new Label[0]);
        this.textField = textField.toArray(new TextField[0]);
        tableControllers = new ArrayList<>();

        ((ToggleButton)toggleButtons[0]).setSelected(true);
        ((ToggleButton)toggleButtons[0]).setStyle(
            "-fx-border-color: #a9a9a9;-fx-border-style: solid hidden solid hidden;"
        );
        pane[0].toFront();
        if(toolBars.length != 0) toolBars[0].toFront();

        for (int i = 0; i < toggleButtons.length; i++) {
            ToggleButton node = (ToggleButton)toggleButtons[i];
            int index = i;
            node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override 
                public void handle(MouseEvent e) { 
                    for (Node node2 : toggleButtons) {
                        ((ToggleButton) node2).setSelected(false);
                    }
                    ((ToggleButton) node).setSelected(true);

                    pane[index].toFront();
                    if(index > -1){
                        if(index < toolBars.length) toolBars[index].toFront();
                        if(index < tableControllers.size()) tableControllers.get(index).onShowing(index);
                    }
                    
                } 
            });
        }
    }


    public void setTable(int index, TableView<Object> table){
        AnchorPane anchorPane = (AnchorPane)pane[index];
        anchorPane.getChildren().addAll(table);
        table.toFront();
    }

    public TableView getTable(int index){
        return (TableView)pane[index].lookup(".table-view");
    }

    public Node[] getToggleButtons() {
        return this.toggleButtons;
    }

    public void setToggleButtons(Node[] toggleButtons) {
        this.toggleButtons = toggleButtons;
    }

    public Node[] getPane() {
        return this.pane;
    }

    public void setPane(Node[] pane) {
        this.pane = pane;
    }

    public StackPane getStackPane() {
        return this.stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public ToolBar[] getToolBar() {
        return toolBars;
    }

    public void setToolBar(ToolBar[] toolBars) {
        this.toolBars = toolBars;
    }

    public ArrayList<TableController> getTableControllers() {
        return tableControllers;
    }

    public void setTableControllers(ArrayList<TableController> tableControllers) {
        this.tableControllers = tableControllers;
    }

    public Label[] getLabel() {
        return label;
    }

    public void setLabel(Label[] label) {
        this.label = label;
    }

    public TextField[] getTextField() {
        return textField;
    }

    public void setTextField(TextField[] textField) {
        this.textField = textField;
    }

}
