package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;

public class TbHoaDonChiTietDao {
    Connection objConn;
    public TbHoaDonChiTietDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public TbHoaDonChiTiet getAll(int id){
        List<TbHoaDonChiTiet> listCat = new ArrayList<TbHoaDonChiTiet>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "select kh.ten_khachHang as \"Hoten\", kh.sdt_khachHang as \"Sdt\", kh.diaChi\n" +
                        "from khachHang kh, donHang dh\n" +
                        "where kh.id_khachHang = dh.id_khachHang and id_donHang = "+id+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbHoaDonChiTiet obj = new TbHoaDonChiTiet();
                    obj.setHoten(resultSet.getString("hoten"));
                    obj.setSdt(resultSet.getInt("sdt"));
                    obj.setDiachi(resultSet.getString("diachi"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return  listCat.get(0);
    }
}
