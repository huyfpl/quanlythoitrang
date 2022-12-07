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
                    obj.setInfo(resultSet.getString("info"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return listCat;
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
                    tbSanPham.setInfo(resultSet.getString("info"));
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpID: lỗi");
        }
        return tbSanPham;
    }
    public TbSanPham getSpID_name(String name) {
        TbSanPham tbSanPham = new TbSanPham();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham WHERE ten_sanpham = N'"+name+"'";
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
                    tbSanPham.setInfo(resultSet.getString("info"));
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpID: lỗi");
        }
        return tbSanPham;
    }
}
