package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PhatQuaCuoiNam {
    int ID;
    int idHocSinh;
    String hoTen;
    String hocLuc;
    String thanhTichDacBiet;
    Date ngay;
    String namHoc;
    String moTa;
    int giaTri;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(int idHocSinh) {
        this.idHocSinh = idHocSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHocLuc() {
        return hocLuc;
    }

    public void setHocLuc(String hocLuc) {
        this.hocLuc = hocLuc;
    }

    public String getThanhTichDacBiet() {
        return thanhTichDacBiet;
    }

    public void setThanhTichDacBiet(String thanhTichDacBiet) {
        this.thanhTichDacBiet = thanhTichDacBiet;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // public int getGiaTri() {
    //     return giaTri;
    // }

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

    public PhatQuaCuoiNam(int iD, int idHocSinh, String hoTen, String hocLuc, String thanhTichDacBiet, Date ngay,
            String namHoc, String moTa, int giaTri) {
        ID = iD;
        this.idHocSinh = idHocSinh;
        this.hoTen = hoTen;
        this.hocLuc = hocLuc;
        this.thanhTichDacBiet = thanhTichDacBiet;
        this.ngay = ngay;
        this.namHoc = namHoc;
        this.moTa = moTa;
        this.giaTri = giaTri;
    }

    

}
