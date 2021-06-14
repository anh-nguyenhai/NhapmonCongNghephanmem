package entity;

import java.util.*;

public class NhanKhau {

	private int ID;
	private String maNhanKhau;
	private String hoTen;
	private String bietDanh;
	private Date namSinh;
	private String gioiTinh;
	private String noiSinh;
	private String nguyenQuan;
	private String danToc;
	private String tonGiao;
	private String quocTich;
	private String soHoChieu;
	private String noiThuongTru;
	private String diaChiHienNay;
	private String trinhDoHocVan;
	private String trinhDoChuyenMon;
	private String bietTiengDanToc;
	private String trinhDoNgoaiNgu;
	private String ngheNghiep;
	private String noiLamViec;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMaNhanKhau() {
		return maNhanKhau;
	}
	public void setMaNhanKhau(String maNhanKhau) {
		this.maNhanKhau = maNhanKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getBietDanh() {
		return bietDanh;
	}
	public void setBietDanh(String bietDanh) {
		this.bietDanh = bietDanh;
	}
	public Date getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(Date namSinh) {
		this.namSinh = namSinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNoiSinh() {
		return noiSinh;
	}
	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}
	public String getNguyenQuan() {
		return nguyenQuan;
	}
	public void setNguyenQuan(String nguyenQuan) {
		this.nguyenQuan = nguyenQuan;
	}
	public String getDanToc() {
		return danToc;
	}
	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}
	public String getTonGiao() {
		return tonGiao;
	}
	public void setTonGiao(String tonGiao) {
		this.tonGiao = tonGiao;
	}
	public String getQuocTich() {
		return quocTich;
	}
	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}
	public String getSoHoChieu() {
		return soHoChieu;
	}
	public void setSoHoChieu(String soHoChieu) {
		this.soHoChieu = soHoChieu;
	}
	public String getNoiThuongTru() {
		return noiThuongTru;
	}
	public void setNoiThuongTru(String noiThuongTru) {
		this.noiThuongTru = noiThuongTru;
	}
	public String getDiaChiHienNay() {
		return diaChiHienNay;
	}
	public void setDiaChiHienNay(String diaChiHienNay) {
		this.diaChiHienNay = diaChiHienNay;
	}
	public String getTrinhDoHocVan() {
		return trinhDoHocVan;
	}
	public void setTrinhDoHocVan(String trinhDoHocVan) {
		this.trinhDoHocVan = trinhDoHocVan;
	}
	public String getTrinhDoChuyenMon() {
		return trinhDoChuyenMon;
	}
	public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
		this.trinhDoChuyenMon = trinhDoChuyenMon;
	}
	public String getBietTiengDanToc() {
		return bietTiengDanToc;
	}
	public void setBietTiengDanToc(String bietTiengDanToc) {
		this.bietTiengDanToc = bietTiengDanToc;
	}
	public String getTrinhDoNgoaiNgu() {
		return trinhDoNgoaiNgu;
	}
	public void setTrinhDoNgoaiNgu(String trinhDoNgoaiNgu) {
		this.trinhDoNgoaiNgu = trinhDoNgoaiNgu;
	}
	public String getNgheNghiep() {
		return ngheNghiep;
	}
	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}
	public String getNoiLamViec() {
		return noiLamViec;
	}
	public void setNoiLamViec(String noiLamViec) {
		this.noiLamViec = noiLamViec;
	}
	
	public NhanKhau(int iD, String maNhanKhau, String hoTen, String bietDanh, Date namSinh, String gioiTinh,
			String noiSinh, String nguyenQuan, String danToc, String tonGiao, String quocTich, String soHoChieu,
			String noiThuongTru, String diaChiHienNay, String trinhDoHocVan, String trinhDoChuyenMon,
			String bietTiengDanToc, String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec) {
		super();
		ID = iD;
		this.maNhanKhau = maNhanKhau;
		this.hoTen = hoTen;
		this.bietDanh = bietDanh;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.noiSinh = noiSinh;
		this.nguyenQuan = nguyenQuan;
		this.danToc = danToc;
		this.tonGiao = tonGiao;
		this.quocTich = quocTich;
		this.soHoChieu = soHoChieu;
		this.noiThuongTru = noiThuongTru;
		this.diaChiHienNay = diaChiHienNay;
		this.trinhDoHocVan = trinhDoHocVan;
		this.trinhDoChuyenMon = trinhDoChuyenMon;
		this.bietTiengDanToc = bietTiengDanToc;
		this.trinhDoNgoaiNgu = trinhDoNgoaiNgu;
		this.ngheNghiep = ngheNghiep;
		this.noiLamViec = noiLamViec;
	}
	
}
