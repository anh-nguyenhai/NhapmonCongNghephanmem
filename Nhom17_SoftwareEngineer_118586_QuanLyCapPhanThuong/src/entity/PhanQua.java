package entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PhanQua {

	private int ID;
	private String loaiQua;
	private int giaTri;
	private String moTa;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLoaiQua() {
		return loaiQua;
	}
	public void setLoaiQua(String loaiQua) {
		this.loaiQua = loaiQua;
	}
	public int getGiaTriInt() {
		return giaTri;
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
	
	public PhanQua(int iD, String loaiQua, int giaTri, String moTa) {
		super();
		ID = iD;
		this.loaiQua = loaiQua;
		this.giaTri = giaTri;
		this.moTa = moTa;
	}

}
