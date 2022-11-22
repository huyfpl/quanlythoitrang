package lam.fpoly.shopthoitrang.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;

@Dao
public interface DonHangDAO {

    @Insert
    void insertData(DonHang_Temorary donHang);

    @Query("SELECT * FROM donhang")
    List<DonHang_Temorary> getListData();

    @Query("SELECT * FROM donhang WHERE id = :id")
    DonHang_Temorary getObjectData(int id);


    @Query("SELECT soLuong FROM donhang WHERE id = :id")
    int getSoLuong(int id);

    @Query("DELETE FROM donhang")
    void delete();

    @Query("UPDATE donhang set soLuong = :soLuong where id = :id")
    void update(int soLuong,int id);

    @Query("DELETE FROM donhang WHERE id = :position")
    void deletePosition(int position);

}
