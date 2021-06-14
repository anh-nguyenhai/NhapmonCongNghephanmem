package entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ThuChiQuy {

	private int ID; 
	private String ngay;
	private int soTien;
	private String loaiThuChi;
	private String userNguoiThucHien;
	private String moTa;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getSoTien() {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dcms = new DecimalFormatSymbols();
        dcms.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dcms);
        df.setGroupingSize(3);
        return df.format(soTien) + " VND";
	}
	
	public int getSoTienInt(){
		return soTien;
	}

	public void setSoTien(int soTien) {
		this.soTien = soTien;
	}

	public String getLoaiThuChi() {
		return loaiThuChi;
	}

	public void setLoaiThuChi(String loaiThuChi) {
		this.loaiThuChi = loaiThuChi;
	}

	public String getUserNguoiThucHien() {
		return userNguoiThucHien;
	}

	public void setUserNguoiThucHien(String userNguoiThucHien) {
		this.userNguoiThucHien = userNguoiThucHien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public ThuChiQuy(int iD, String ngay, int soTien, String loaiThuChi, String userNguoiThucHien, String moTa) {
		ID = iD;
		this.ngay = ngay;
		this.soTien = soTien;
		this.loaiThuChi = loaiThuChi;
		this.userNguoiThucHien = userNguoiThucHien;
		this.moTa = moTa;
	}

	public ThuChiQuy(){};
	
	
}