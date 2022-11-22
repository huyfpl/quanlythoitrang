package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;


public class TbGioHangDao {
    Connection objConn;
    public TbGioHangDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public List<TbGioHang> getKhachHang(int idKhachHang){
        List<TbGioHang> listCat = new ArrayList<>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM gioHang WHERE id_khachHang = '"+idKhachHang+"' ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbGioHang obj = new TbGioHang();
                    obj.setIdKhachHang(resultSet.getInt("id_khachHang"));
                    obj.setIdSanPham(resultSet.getInt("id_sanPham"));
                    obj.setSoLuongSP(resultSet.getInt("soluong"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getKhachHang: lỗi");
        }

        return  listCat;
    }

    public void deleteRow(int id_khachHang, int id_sanPham){
        try {
            if (this.objConn != null) {
                String sqlDelete = "DELETE FROM gioHang WHERE id_khachHang = '"+id_khachHang+"' AND id_sanPham = '"+id_sanPham+"'";
                PreparedStatement stmt = this.objConn.prepareStatement(sqlDelete);
                stmt.execute(); // thực thi câu lệnh SQL
            }
        } catch (Exception e) {
            Log.i("TAG", "deleteRow: lỗi");
        }
    }

    public void updateRow(TbGioHang obj){
        try {
            if (this.objConn != null) {
                String sqlUpdate = "UPDATE gioHang SET soluong = '"+obj.getSoLuongSP()+"' " +
                        "WHERE id_khachHang = '"+obj.getIdKhachHang()+"' AND id_sanPham = '"+ obj.getIdSanPham()+"'";

                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL
            }
        } catch (Exception e) {
            Log.i("TAG", "updateRow: lỗi");
        }
    }

    public void insertRow (TbGioHang objCat){
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO gioHang VALUES (" +
                        "'"+objCat.getIdKhachHang()+"'," +
                        "'"+objCat.getIdSanPham()+"',"+
                        "'"+objCat.getSoLuongSP()+"')";
                String generatedColumns[] = { "ID" };
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

    public boolean checkExist(TbGioHang tbGioHang){
        boolean check = false;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM gioHang " +
                        "WHERE id_khachHang = '"+tbGioHang.getIdKhachHang()+"' " +
                        "AND id_sanPham = '"+tbGioHang.getIdSanPham()+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    if(resultSet.wasNull()){
                        check = true;
                    }else{
                        check = false;
                    }
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getKhachHang: lỗi");
        }

        return  check;
    }

}
