package controller.entitycontroller;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

public abstract class EntityController <T> {

    protected ArrayList<T> list = null;
    protected ResultSetMetaData metaData = null;
    protected FilteredList<T> filteredList;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    public void creatFilterList(){
        filteredList = new FilteredList<>(FXCollections.observableArrayList(list), p -> true);
    }

    public FilteredList<T> getFilterList() {
        return filteredList;
    }
    public abstract void getDataFromDatabase(int limit, int page);
    public abstract void getDataFromDatabase();
    public abstract void addRecord(T record);
    public abstract void updateRecord(T newRecord);
    public abstract void updateRecord(T oldRecord, T newRecord);
    public abstract void deleteRecord(int index);
    public abstract void deleteRecord(T record);

}