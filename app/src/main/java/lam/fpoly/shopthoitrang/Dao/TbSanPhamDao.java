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

public class TbSanPhamDao {
    Connection objConn;

    public TbSanPhamDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbSanPham> getAll() {
        List<TbSanPham> listCat = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return listCat;
    }

    public List<TbSanPham> getSpDanhMuc(int idDanhMuc) {
        List<TbSanPham> list = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham WHERE id_danhmuc = '"+idDanhMuc+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpDanhMuc: lỗi");
        }

        return list;
    }

    public TbSanPham getSpID(int idSanPham) {
        TbSanPham tbSanPham = new TbSanPham();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham WHERE id_sanpham = '"+idSanPham+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    tbSanPham.setId_sanPham(resultSet.getInt("id_sanpham"));
                    tbSanPham.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    tbSanPham.setSrcAnh(resultSet.getString("anh"));
                    tbSanPham.setGiaBan(resultSet.getInt("giaban"));
                    tbSanPham.setGiaNhap(resultSet.getInt("gianhap"));
                    tbSanPham.setTonKho(resultSet.getInt("tonkho"));
                    tbSanPham.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpID: lỗi");
        }

        return tbSanPham;
    }

    public void insertRow(TbSanPham objCat) {
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO SanPham VALUES (N'" + objCat.getTen_sanPham() + "'," +
                        "'" + objCat.getSrcAnh() + "'," +
                        "" + objCat.getGiaNhap() + "," +
                        "" + objCat.getGiaBan() + "," +
                        "" + objCat.getTonKho() + "," +
                        "" + objCat.getId_danhmuc() + ") ";
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
        }
    }

    public void updateRow(TbSanPham objCat) {
        try {
            if (this.objConn != null) {
                String sqlUpdate = "UPDATE SanPham SET name= N'" + objCat.getTen_sanPham() + "' WHERE id = " + objCat.getId_sanPham();
                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL
            }
        } catch (Exception e) {
        }
    }

    public int count() {
        int sl = 0;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT count(id_sanpham) as 'so luong' FROM SanPham ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) {
                    sl = resultSet.getInt("so luong");
                }
            }
        } catch (Exception e) {
        }
        return sl;
    }

}
