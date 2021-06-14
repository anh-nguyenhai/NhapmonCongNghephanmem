package entity;

import java.sql.Date;

public class PhatQuaDipDacBiet {
    private int ID;
    private int idNhanKhau;
    private Date ngay;
    private String dip;   
    private int idPhanQua;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
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

    public int getIdPhanQua() {
        return idPhanQua;
    }

    public void setIdPhanQua(int idPhanQua) {
        this.idPhanQua = idPhanQua;
    }

    public PhatQuaDipDacBiet(int iD, int idNhanKhau, Date ngay, String dip, int idPhanQua) {
        ID = iD;
        this.idNhanKhau = idNhanKhau;
        this.ngay = ngay;
        this.dip = dip;
        this.idPhanQua = idPhanQua;
    }
    
    
}
