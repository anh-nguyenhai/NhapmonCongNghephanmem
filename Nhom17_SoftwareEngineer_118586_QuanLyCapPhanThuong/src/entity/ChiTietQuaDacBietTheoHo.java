package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ChiTietQuaDacBietTheoHo {
    private String hoTen;
    private Date ngaySinh;
    private Date ngayNhan;
    private String dip;
    private int giaTri;
    private String moTa;

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

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
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

    public ChiTietQuaDacBietTheoHo(String hoTen, Date ngaySinh, Date ngayNhan, String dip, int giaTri,
            String moTa) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.ngayNhan = ngayNhan;
        this.dip = dip;
        this.giaTri = giaTri;
        this.moTa = moTa;
    }

}
