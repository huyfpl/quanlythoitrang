package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbHoaDon;

public class TbHoaDonDao {
    Connection objConn;
    public TbHoaDonDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public List<TbHoaDon> getAll(){
        List<TbHoaDon> listCat = new ArrayList<TbHoaDon>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "select dh.id_donHang,sp.anh, sp.ten_sanPham, giaBan, soLuong, trangThai\n" +
                        "from sanPham sp, donHang dh, chiTietDonHang ctdh\n" +
                        "where sp.id_sanPham = ctdh.id_sanPham and ctdh.id_donHang = dh.id_donHang";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbHoaDon obj = new TbHoaDon();
                    obj.setId(resultSet.getInt("id_donHang"));
                    obj.setAnh(resultSet.getString("anh"));
                    obj.setTensp(resultSet.getString("ten_sanpham"));
                    obj.setSoluong(resultSet.getInt("soluong"));
                    obj.setTrangthai(resultSet.getString("trangthai"));
                    obj.setGia(resultSet.getInt("giaban"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return  listCat;
    }
}
