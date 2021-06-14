package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KhuyenHocTheoNha {
    private String diaChi;
    private Date date;
    private String moTaPhanQuaDuocNhan;
    private String moTaSuatQuaDuocNhan; //like 2 Giỏi, 2 Khuyến khích
    private Map<PhanQua, Integer> map; //key = PhanQua, value = so phan qua
    private ArrayList<String> hocSinhList;
    private int sumValue = 0;

    public KhuyenHocTheoNha(String diaChi, Date date) {
        this.diaChi = diaChi;
        this.date = date;

        this.map = new HashMap<>();
        this.hocSinhList = new ArrayList<>();
    }

    public KhuyenHocTheoNha(String diaChi, String moTaPhanQuaDuocNhan, String moTaSuatQuaDuocNhan) {
        this.diaChi = diaChi;
        this.moTaPhanQuaDuocNhan = moTaPhanQuaDuocNhan;
        this.moTaSuatQuaDuocNhan = moTaSuatQuaDuocNhan;

        this.map = new HashMap<>();
        this.hocSinhList = new ArrayList<>();
    }


    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTaPhanQuaDuocNhan() {
        return moTaPhanQuaDuocNhan;
    }

    public void setMoTaPhanQuaDuocNhan(String moTaPhanQuaDuocNhan) {
        this.moTaPhanQuaDuocNhan = moTaPhanQuaDuocNhan;
    }

    public String getMoTaSuatQuaDuocNhan() {
        return moTaSuatQuaDuocNhan;
    }

    public void setMoTaSuatQuaDuocNhan(String moTaSuatQuaDuocNhan) {
        this.moTaSuatQuaDuocNhan = moTaSuatQuaDuocNhan;
    }

    public String getHocSinhList() {
        String re = hocSinhList.toString();
        return re.substring(1, re.length() - 1);
    }

    public ArrayList<String> gethocSinhList() {
        return hocSinhList;
    }

    public void setHocSinhList(ArrayList<String> hocSinhList) {
        this.hocSinhList = hocSinhList;
    }

    public Map<PhanQua, Integer> getMap() {
        return map;
    }

    public void setMap(Map<PhanQua, Integer> map) {
        this.map = map;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // public int getSumValue() {
    //     return sumValue;
    // }

    public String getSumValue() {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dcms = new DecimalFormatSymbols();
        dcms.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dcms);
        df.setGroupingSize(3);
        return df.format(sumValue) + " VND";
	}

    public void setSumValue(int sumValue) {
        this.sumValue = sumValue;
    }

}
