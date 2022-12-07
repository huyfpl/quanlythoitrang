package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbSaleSP;

public class TbSaleSPDao {
    Connection objConn;

    public TbSaleSPDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbSaleSP> getAll() {
        List<TbSaleSP> list = new ArrayList<TbSaleSP>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM saleSP ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSaleSP obj = new TbSaleSP();
                    obj.setId_sanpham(resultSet.getInt("id_sanPham"));
                    obj.setSale(resultSet.getInt("sale"));
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
