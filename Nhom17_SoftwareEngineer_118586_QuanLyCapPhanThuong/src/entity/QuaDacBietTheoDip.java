package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class QuaDacBietTheoDip {
    private Date date;
    private int idQua;
    private int tongQua;
    private int tongTien;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdQua() {
        return idQua;
    }

    public void setIdQua(int idQua) {
        this.idQua = idQua;
    }

    public String getTongQua() {
        return tongQua + " suất";
    }

    public void setTongQua(int tongQua) {
        this.tongQua = tongQua;
    }

    public String getTongTien() {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dcms = new DecimalFormatSymbols();
        dcms.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dcms);
        df.setGroupingSize(3);
        return df.format(tongTien) + " VND";
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public QuaDacBietTheoDip(Date date, int idQua, int tongQua, int tongTien) {
        this.date = date;
        this.idQua = idQua;
        this.tongQua = tongQua;
        this.tongTien = tongTien;
    }

    
    
}
