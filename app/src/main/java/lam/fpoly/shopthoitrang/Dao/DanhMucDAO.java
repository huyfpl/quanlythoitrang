package lam.fpoly.shopthoitrang.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lam.fpoly.shopthoitrang.Model.TbDanhMuc;
import lam.fpoly.shopthoitrang.Model.TbSanPham;

@Dao
public interface DanhMucDAO {

    @Insert
    void insertData(TbDanhMuc tbDanhMuc);

    @Query("SELECT * FROM danhmuc")
    List<TbDanhMuc> getDataAll();

    @Query("SELECT id_danhMuc FROM danhmuc WHERE ten_danhMuc = :name")
    int getIdDanhMuc(String name);

    @Query("DELETE FROM danhmuc")
    void deleteAll();

}
