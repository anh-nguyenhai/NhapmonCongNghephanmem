package service;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableLayout {

    private static String keyWord[] = {"id", "ngay" };
    private static String labelDisplayKey[] = {"Tuổi", "Giá trị", "Lớp"};

    public static void layoutTable(TableView table, ResultSetMetaData metaData, List<String> listToDisplay) {
        table.getColumns().clear();
        try {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            for (int i = 0; i < listToDisplay.size(); i++) {
                String label = listToDisplay.get(i);

                boolean flag = true;
                for (int j = 0; j < metaData.getColumnCount(); j++) {
                    if(metaData.getColumnLabel(j + 1).equals(label)){
                        flag = false;
                        break;
                    }
                }

                if(flag) continue;

                TableColumn column = new TableColumn(label);
                column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

                //column.setPrefWidth(label.length() * 25);
                for (int j = 0; j < keyWord.length; j++) {
                    if(label.toLowerCase().contains(keyWord[j])){
                        column.setMinWidth(label.length() * 18);
                        column.setMaxWidth(label.length() * 18);
                        column.setResizable(false);
                    }
                }
                
                table.getColumns().add(column);
            }
            /* for (int i = 0; i < metaData.getColumnCount(); i++) {
                String label = metaData.getColumnLabel(i + 1);
                if(listToDisplay != null && !listToDisplay.contains(label)) continue;
                TableColumn column = new TableColumn(label);
                column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

                //column.setPrefWidth(label.length() * 25);
                for (int j = 0; j < keyWord.length; j++) {
                    if(label.toLowerCase().contains(keyWord[j])){
                        column.setMinWidth(label.length() * 20);
                        column.setMaxWidth(label.length() * 20);
                        column.setResizable(false);
                    }
                }
                
                table.getColumns().add(column);
            }     */
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void layoutTable(TableView table, 
        List<String> listToDisplay, List<String> listLabelsToDisplay) {
        table.getColumns().clear();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn numberCol = new TableColumn("STT");
        numberCol.setMinWidth(40);
        numberCol.setMaxWidth(40);
        numberCol.setResizable(false);
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {
        @Override public ObservableValue<String> call(CellDataFeatures<Object, String> p) {
            return new ReadOnlyObjectWrapper(table.getItems().indexOf(p.getValue()) + 1 + "");
        }
        });   
        numberCol.setSortable(false);
        numberCol.setReorderable(false);
        numberCol.setStyle("-fx-border-color: rgb(100,100,100); -fx-border-style: hidden solid hidden hidden;");
        table.getColumns().add(numberCol);
        
        for (int i = 0; i < listToDisplay.size(); i++) {
            String label = listToDisplay.get(i);

            TableColumn column = new TableColumn(listLabelsToDisplay.get(i));
            
            column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

            // column.setPrefWidth(label.length() * 25);
            for (int j = 0; j < labelDisplayKey.length; j++) {
                if (listLabelsToDisplay.get(i).contains(labelDisplayKey[j])) {
                    column.setMinWidth(label.length() * 20);
                    column.setMaxWidth(label.length() * 20);
                    column.setResizable(false);
                }
            }

            table.getColumns().add(column);
        }
    }

}
