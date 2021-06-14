package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class KhuyenHocTheoDip {

    private Date date;
    private int idQuaGioi;
    private int idQuaTienTien;
    private int idQuaTrungBinh;
    private int tongQua;
    private int tongTien;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdQuaGioi() {
        return idQuaGioi;
    }

    public void setIdQuaGioi(int idQuaGioi) {
        this.idQuaGioi = idQuaGioi;
    }

    public int getIdQuaTienTien() {
        return idQuaTienTien;
    }

    public void setIdQuaTienTien(int idQuaTienTien) {
        this.idQuaTienTien = idQuaTienTien;
    }

    public int getIdQuaTrungBinh() {
        return idQuaTrungBinh;
    }

    public void setIdQuaTrungBinh(int idQuaTrungBinh) {
        this.idQuaTrungBinh = idQuaTrungBinh;
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

    public KhuyenHocTheoDip(Date date, int idQuaGioi, int idQuaTienTien, int idQuaTrungBinh, int tongQua,
            int tongTien) {
        this.date = date;
        this.idQuaGioi = idQuaGioi;
        this.idQuaTienTien = idQuaTienTien;
        this.idQuaTrungBinh = idQuaTrungBinh;
        this.tongQua = tongQua;
        this.tongTien = tongTien;
    }

    
    
}