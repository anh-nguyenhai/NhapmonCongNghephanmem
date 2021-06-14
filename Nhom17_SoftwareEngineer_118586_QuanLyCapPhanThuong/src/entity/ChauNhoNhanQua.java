package entity;

import java.sql.Date;

public class ChauNhoNhanQua {
    private int ID;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private int tuoi;
    private String diaChi;
    private String phuHuynh;

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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPhuHuynh() {
        return phuHuynh;
    }

    public void setPhuHuynh(String phuHuynh) {
        this.phuHuynh = phuHuynh;
    }

    public ChauNhoNhanQua(int iD, String hoTen, String gioiTinh, Date ngaySinh, int tuoi, String diaChi,
            String phuHuynh) {
        ID = iD;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.phuHuynh = phuHuynh;
    }
    public ChauNhoNhanQua(){};
    

}
