package entity;

public class User {

	private int ID; 
	private String userName;
	private String password;
	private String hoTen;
	private String diaChi;
	private String chucVu;
	private String email;
	private String facebook;
	private String dienthoai;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getDienthoai() {
		return dienthoai;
	}

	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}

	public User(String userName, String hoTen, String diaChi, String chucVu, String email, String facebook,
			String dienthoai) {
		this.userName = userName;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.email = email;
		this.facebook = facebook;
		this.dienthoai = dienthoai;
	}

	public User(){}
	
}
