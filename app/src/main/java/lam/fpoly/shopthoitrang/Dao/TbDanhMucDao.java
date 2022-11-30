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
import lam.fpoly.shopthoitrang.Model.TbSanPham;

public class TbDanhMucDao {
    Connection objConn;

    public TbDanhMucDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbDanhMuc> getAll() {
        List<TbDanhMuc> list = new ArrayList<TbDanhMuc>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM danhMuc ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbDanhMuc obj = new TbDanhMuc();
                    obj.setId_danhMuc(resultSet.getInt("id_danhmuc"));
                    obj.setTen_danhMuc(resultSet.getString("ten_danhMuc"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return list;
    }

    public int getID(String name) {
        int id = 0;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT id_danhmuc AS \"id\" from danhMuc WHERE ten_danhMuc = N'"+name+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    id = resultSet.getInt("id");
                }

            }
        } catch (Exception e) {
            Log.i("TAG", "getID: lỗi");
        }
        return id;
    }

    public void insertRow(TbDanhMuc objCat) {
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO SanPham VALUES (" +
                        "'"+objCat.getId_danhMuc()+"'," +
                        "N'"+objCat.getTen_danhMuc()+"')";
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
