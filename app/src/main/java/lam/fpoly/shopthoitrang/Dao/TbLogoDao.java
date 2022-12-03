package lam.fpoly.shopthoitrang.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lam.fpoly.shopthoitrang.DbSqlServer;
import lam.fpoly.shopthoitrang.Model.TbLogo;
import lam.fpoly.shopthoitrang.Model.TbSanPham;

public class TbLogoDao {
    Connection objConn;

    public TbLogoDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public String getLogo() {
        String link=null;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM LogoShop";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    link=resultSet.getString("logo");
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpID: lỗi");
        }

        return link;
    }
}
