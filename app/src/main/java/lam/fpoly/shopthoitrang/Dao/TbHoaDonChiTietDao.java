package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbDonHang;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;

public class TbHoaDonChiTietDao {
    Connection objConn;
    public TbHoaDonChiTietDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public List<TbHoaDonChiTiet> getAll(int id){
        List<TbHoaDonChiTiet> listCat = new ArrayList<TbHoaDonChiTiet>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM chiTietDonHang WHERE id_donHang = '"+id+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbHoaDonChiTiet obj = new TbHoaDonChiTiet();
                    obj.setId_DonHang(resultSet.getInt("id_donHang"));
                    obj.setId_SanPham(resultSet.getInt("id_sanPham"));
                    obj.setSoLuong(resultSet.getInt("soLuong"));
                    obj.setGia(resultSet.getInt("giaMua"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return  listCat;
    }

    public int getGia_idsp(int idsp){
        int gia = 0;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM chiTietDonHang WHERE id_sanpham = '"+idsp+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    gia = resultSet.getInt("giaMua");
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return  gia;
    }


    public void insertRow(TbHoaDonChiTiet objCat) {
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO chiTietDonHang VALUES " +
                        "("+objCat.getId_DonHang()+"," +
                        ""+objCat.getId_SanPham()+"," +
                        ""+objCat.getSoLuong()+"," +
                        ""+objCat.getGia()+")";
                String generatedColumns[] = {"ID"};
                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();
            }
        } catch (Exception e) {
            Log.i("TAG", "insertRow: lỗi");
        }
    }

}
