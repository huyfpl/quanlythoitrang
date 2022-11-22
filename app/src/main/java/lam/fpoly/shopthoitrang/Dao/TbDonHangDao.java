package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbDanhMuc;
import lam.fpoly.shopthoitrang.Model.TbDonHang;

public class TbDonHangDao {
    Connection objConn;

    public TbDonHangDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbDonHang> getAll() {
        List<TbDonHang> list = new ArrayList<>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM donHang ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbDonHang obj = new TbDonHang();
                    obj.setId_DonHang(resultSet.getInt("id_donHang"));
                    obj.setId_KhachHang(resultSet.getInt("id_khachHang"));
                    obj.setTrangThai(resultSet.getString("trangThai"));
                    obj.setNgayMua(resultSet.getString("ngayMua"));
                    obj.setTongTien(resultSet.getInt("tongtien"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return list;
    }


    public void insertRow(TbDonHang objCat) {
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO donHang VALUES " +
                        "('"+objCat.getId_KhachHang()+"'," +
                        "'"+objCat.getTrangThai()+"'," +
                        "'"+objCat.getNgayMua()+"'," +
                        "'"+objCat.getTongTien()+"')";
                String generatedColumns[] = {"ID"};
                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "insertRow: lỗi");
        }
    }

}
