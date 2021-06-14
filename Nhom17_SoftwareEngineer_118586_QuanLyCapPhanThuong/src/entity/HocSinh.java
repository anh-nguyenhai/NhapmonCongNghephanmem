package entity;

public class HocSinh {

	private int ID;
	private String hoTen;
	private String lop;
	private String truong;
	private String hocLuc;
	private String thanhTichDacBiet;
	private String diaChi;
	private String minhChungHocLuc;
	private String minhChungThanhTich;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getTruong() {
		return truong;
	}
	public void setTruong(String truong) {
		this.truong = truong;
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

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMinhChungHocLuc() {
		return minhChungHocLuc;
	}

	public void setMinhChungHocLuc(String minhChungHocLuc) {
		this.minhChungHocLuc = minhChungHocLuc;
	}

	public String getMinhChungThanhTich() {
		return minhChungThanhTich;
	}

	public void setMinhChungThanhTich(String minhChungThanhTich) {
		this.minhChungThanhTich = minhChungThanhTich;
	}

	public HocSinh(int iD, String hoTen, String lop, String truong, String hocLuc,
			String thanhTichDacBiet, String diaChi, String minhChungHocLuc, String minhChungThanhTich) {
		ID = iD;
		this.hoTen = hoTen;
		this.lop = lop;
		this.truong = truong;
		this.hocLuc = hocLuc;
		this.thanhTichDacBiet = thanhTichDacBiet;
		this.diaChi = diaChi;
		this.minhChungHocLuc = minhChungHocLuc;
		this.minhChungThanhTich = minhChungThanhTich;
	}
	
}
