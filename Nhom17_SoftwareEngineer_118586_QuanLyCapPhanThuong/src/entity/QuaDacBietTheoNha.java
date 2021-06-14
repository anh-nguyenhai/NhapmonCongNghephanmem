package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class QuaDacBietTheoNha {
    private int ID;
    private String diaChi;
    private String hoTen;
    private Date ngay;
    private String dip;
    private int soQua;
    private int soTien;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public int getSoQua() {
        return soQua;
    }

    public void setSoQua(int soQua) {
        this.soQua = soQua;
    }

    public String getSoTien() {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dcms = new DecimalFormatSymbols();
        dcms.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dcms);
        df.setGroupingSize(3);
        return df.format(soTien) + " VND";
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public QuaDacBietTheoNha(int iD, String diaChi, String hoTen, Date ngay, String dip, int soQua, int soTien) {
        ID = iD;
        this.diaChi = diaChi;
        this.hoTen = hoTen;
        this.ngay = ngay;
        this.dip = dip;
        this.soQua = soQua;
        this.soTien = soTien;
    }
    
    
}
