package controller.entitycontroller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import service.DatabaseConnection;
import entity.KhuyenHocTheoNha;
import entity.PhanQua;
import javafx.util.Pair;

public class KhuyenHocTheoNhaController extends EntityController<KhuyenHocTheoNha> {

    private Date date = null;

    @Override
    public void getDataFromDatabase(int limit, int page) {
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }
        if (date == null) { 
            ResultSet result = DatabaseConnection.executeQuery(
                    "SELECT * FROM phat_qua_cuoi_nam INNER JOIN phan_qua ON phan_qua.ID = phat_qua_cuoi_nam.idPhanQua "
                            + "INNER JOIN hoc_sinh ON hoc_sinh.ID = phat_qua_cuoi_nam.idHocSinh LIMIT 1");
            try {
                metaData = result.getMetaData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        ;

        Map<Pair<String, Date>, PhanQua> phanQua = new HashMap<>();
        Map<Pair<String, Date>, KhuyenHocTheoNha> nha = new HashMap<>();

        ResultSet phanQuaResultSet = DatabaseConnection.executeQuery("SELECT phan_qua_khuyen_hoc.date, "
                + "phan_qua_khuyen_hoc.idQuaGioi AS 'Giỏi', phan_qua_khuyen_hoc.idQuaTienTien AS 'Tiên tiến', phan_qua_khuyen_hoc.idQuaTrungBinh AS 'Trung bình', "
                + "gioi.giaTri AS 'giaTri Giỏi', gioi.moTa AS 'moTa Giỏi',"
                + "tienTien.giaTri AS 'giaTri Tiên tiến', tienTien.moTa AS 'moTa Tiên tiến',"
                + "tB.giaTri AS 'giaTri Trung bình', tB.moTa AS 'moTa Trung bình' " + "FROM phan_qua_khuyen_hoc "
                + "INNER JOIN phan_qua gioi ON gioi.ID = phan_qua_khuyen_hoc.idQuaGioi "
                + "INNER JOIN phan_qua tienTien ON tienTien.ID = phan_qua_khuyen_hoc.idQuaTienTien "
                + "INNER JOIN phan_qua tB ON tB.ID = phan_qua_khuyen_hoc.idQuaTrungBinh "
                + "WHERE phan_qua_khuyen_hoc.date = '"+ date +"'");
        try {
            phanQuaResultSet.next();
            String[] hocLuc = { "Giỏi", "Tiên tiến", "Trung bình" };

            for (int i = 0; i < 3; i++) {
                Pair<String, Date> curLoaiPhanQua = new Pair<String, Date>(hocLuc[i], phanQuaResultSet.getDate("date"));

                phanQua.put(curLoaiPhanQua,
                        new PhanQua(phanQuaResultSet.getInt(hocLuc[i]), "Khuyến học",
                                phanQuaResultSet.getInt("giaTri " + hocLuc[i]),
                                phanQuaResultSet.getString("moTa " + hocLuc[i])));

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        ResultSet result = DatabaseConnection.executeQuery(
                "SELECT * FROM phat_qua_cuoi_nam INNER JOIN phan_qua ON phan_qua.ID = phat_qua_cuoi_nam.idPhanQua "
                        + "INNER JOIN hoc_sinh ON hoc_sinh.ID = phat_qua_cuoi_nam.idHocSinh WHERE phat_qua_cuoi_nam.ngay = '"
                        + date + "'");

        try {
            metaData = result.getMetaData();

            while (result.next()) {
                // Pair<String, Date> curLoaiPhanQua = new Pair<String,
                // Date>(result.getString("hocLuc"), phanQuaResultSet.getDate("date"));

                PhanQua cur = getPhanQua(result, phanQua);
                Pair<String, Date> curNhaKey = new Pair<String, Date>(result.getString("diaChi"),
                        result.getDate("ngay"));
                if (!nha.containsKey(curNhaKey)) {
                    KhuyenHocTheoNha temp = new KhuyenHocTheoNha(result.getString("diaChi"), result.getDate("ngay"));
                    nha.put(curNhaKey, temp);
                    String hoTen = result.getString("hoTen");

                    if (!temp.gethocSinhList().contains(hoTen)) {
                        temp.gethocSinhList().add(hoTen);
                    }
                    temp.getMap().put(cur, 1);

                } else {
                    KhuyenHocTheoNha temp = nha.get(curNhaKey);
                    String hoTen = result.getString("hoTen");

                    if (!temp.gethocSinhList().contains(hoTen)) {
                        temp.gethocSinhList().add(hoTen);
                    }

                    if (temp.getMap().containsKey(cur)) {
                        temp.getMap().replace(cur, temp.getMap().get(cur) + 1);
                    } else {
                        temp.getMap().put(cur, 1);
                    }

                }

            }

            for (Map.Entry<Pair<String, Date>, KhuyenHocTheoNha> record : nha.entrySet()) {
                KhuyenHocTheoNha temp = record.getValue();
                Pair<String, Date> gioi = new Pair<String, Date>("Giỏi", temp.getDate());
                Pair<String, Date> kha = new Pair<String, Date>("Tiên tiến", temp.getDate());
                Pair<String, Date> tb = new Pair<String, Date>("Trung bình", temp.getDate());
                String re = "";
                String reMt = "";
                int sum = 0;
                for (Map.Entry<PhanQua, Integer> num : temp.getMap().entrySet()) {
                    String loai = "";
                    if (phanQua.get(gioi).equals(num.getKey())) {
                        loai = gioi.getKey();
                    } else if (phanQua.get(kha).equals(num.getKey())) {
                        loai = kha.getKey();
                    } else if (phanQua.get(tb).equals(num.getKey())) {
                        loai = tb.getKey();
                    }

                    re += " " + num.getValue() + " suất loại " + loai + ", ";
                    reMt += " " + num.getValue() + " suất (" + num.getKey().getMoTa() + "), ";
                    sum += num.getValue() * num.getKey().getGiaTriInt();
                }
                temp.setMoTaSuatQuaDuocNhan(re.substring(0, re.length() - 2));
                temp.setMoTaPhanQuaDuocNhan(reMt.substring(0, reMt.length() - 2));
                temp.setSumValue(sum);
                list.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addRecord(KhuyenHocTheoNha record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoNha newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoNha oldRecord, KhuyenHocTheoNha newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(KhuyenHocTheoNha record) {
        // TODO Auto-generated method stub

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getNgayPhatQua() {
        ArrayList<String> re = new ArrayList<>();
        ResultSet result = DatabaseConnection.executeQuery("SELECT * FROM phan_qua_khuyen_hoc WHERE 1");
        try {
            while (result.next()) {
                re.add(result.getDate("date").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return re;
    }

    public PhanQua getPhanQua(ResultSet resultSet, Map<Pair<String, Date>, PhanQua> phanQua) {
        try {
            String hocLuc = resultSet.getString("hocLuc");
            String thanhTich = resultSet.getString("thanhTichDacBiet");
            int idQua = resultSet.getInt("idPhanQua");
            if (thanhTich != null && !thanhTich.isBlank()) {
                for (Map.Entry<Pair<String, Date>, PhanQua> elm : phanQua.entrySet()) {
                    if (elm.getValue().getID() == idQua) {
                        return elm.getValue();
                    }
                }
                return null;
            } else {
                return phanQua.get(new Pair<>(hocLuc, date));
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void getDataFromDatabase() {
        // TODO Auto-generated method stub

    }

    
}
