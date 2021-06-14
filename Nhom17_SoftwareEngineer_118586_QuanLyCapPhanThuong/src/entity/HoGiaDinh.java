package entity;

import java.util.*;

public class HoGiaDinh {
	
	private int ID;
	private int idChuHo; 
	private String maKhuVuc;
	private String diaChi;
	private Date ngayLap;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIdChuHo() {
		return idChuHo;
	}
	public void setIdChuHo(int idChuHo) {
		this.idChuHo = idChuHo;
	}
	public String getMaKhuVuc() {
		return maKhuVuc;
	}
	public void setMaKhuVuc(String maKhuVuc) {
		this.maKhuVuc = maKhuVuc;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	public HoGiaDinh(int iD, int idChuHo, String maKhuVuc, String diaChi, Date ngayLap) {
		super();
		ID = iD;
		this.idChuHo = idChuHo;
		this.maKhuVuc = maKhuVuc;
		this.diaChi = diaChi;
		this.ngayLap = ngayLap;
	}
	
}
