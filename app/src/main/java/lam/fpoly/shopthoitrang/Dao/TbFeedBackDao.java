package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbFeedBack;
import lam.fpoly.shopthoitrang.Model.TbSaleSP;

public class TbFeedBackDao {
    Connection objConn;

    public TbFeedBackDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbFeedBack> getAll(int idSP) {
        List<TbFeedBack> list = new ArrayList<TbFeedBack>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM feedBack WHERE id_sanPham = "+idSP+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbFeedBack obj = new TbFeedBack();
                    obj.setId_sanpham(resultSet.getInt("id_sanPham"));
                    obj.setId_khachhang(resultSet.getInt("id_khachHang"));
                    obj.setSoStar(resultSet.getInt("soStar"));
                    obj.setMess(resultSet.getString("mess"));
                    obj.setAnh(resultSet.getString("anh"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return list;
    }

    public int getGia_idsp(int idsp) {
        int gia = 0;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM saleSP WHERE id_sanpham = "+idsp+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    gia = resultSet.getInt("sale");
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return gia;
    }


}
