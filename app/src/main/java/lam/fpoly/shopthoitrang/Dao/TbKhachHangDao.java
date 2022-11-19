package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;


public class TbKhachHangDao {
    Connection objConn;
    public TbKhachHangDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public List<TbKhachHang> getAll(){
        List<TbKhachHang> listCat = new ArrayList<TbKhachHang>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM KhachHang ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbKhachHang obj = new TbKhachHang();
                    obj.setId_khachHang(resultSet.getInt("id_khachHang"));
                    obj.setTen_khachHang(resultSet.getString("ten_khachHang"));
                    obj.setSdt_khachHang(resultSet.getString("sdt_khachHang"));
                    obj.setDiaChi(resultSet.getString("diaChi"));
                    obj.setUserName(resultSet.getString("userName"));
                    obj.setUserPass(resultSet.getString("userPass"));

                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return  listCat;
    }


    public int getID(String userName){
        int id = 0;
        List<TbKhachHang> listCat = new ArrayList<TbKhachHang>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM khachHang WHERE userName = '"+userName+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    id = resultSet.getInt("id_khachHang");
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }
        return id;
    }

    public void insertRow (TbKhachHang objCat){
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO KhachHang VALUES (N'" + objCat.getTen_khachHang() + "'," +
                        "'"+ objCat.getSdt_khachHang()+"'," +
                        "N'"+objCat.getDiaChi()+"'," +
                        "'"+objCat.getUserName()+"'," +
                        "'"+objCat.getUserPass()+"') ";
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
}