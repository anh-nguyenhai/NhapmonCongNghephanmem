package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ChiTietQuaDacBietTheoDip {
    private int ID;
    private String hoTen;
    private Date ngaySinh;
    private String diaChiHienNay;
    private Date ngay;
    private String dip;
    private int giaTri;
    private String moTa;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChiHienNay() {
        return diaChiHienNay;
    }

    public void setDiaChiHienNay(String diaChiHienNay) {
        this.diaChiHienNay = diaChiHienNay;
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

    public String getGiaTri() {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dcms = new DecimalFormatSymbols();
        dcms.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dcms);
        df.setGroupingSize(3);
        return df.format(giaTri) + " VND";
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ChiTietQuaDacBietTheoDip(int iD, String hoTen, Date ngaySinh, String diaChiHienNay, Date ngay, String dip,
            int giaTri, String moTa) {
        ID = iD;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChiHienNay = diaChiHienNay;
        this.ngay = ngay;
        this.dip = dip;
        this.giaTri = giaTri;
        this.moTa = moTa;
    }

    
}
