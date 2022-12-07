package lam.fpoly.shopthoitrang.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;

@Dao
public interface SanPhamDAO {

    @Insert
    void insertData(TbSanPham tbSanPham);

    @Query("SELECT * FROM `tb.sanpham`")
    List<TbSanPham> getDataAll();

    @Query("SELECT * FROM `tb.sanpham` WHERE id_danhmuc = :id")
    List<TbSanPham> getDataID(int id);

    @Query("SELECT * FROM `tb.sanpham` WHERE id_sanPham = :id")
    TbSanPham getDataIdSP(int id);

    @Query("UPDATE `tb.sanpham` SET tonKho = :tonkho")
    void updateTonKho(int tonkho);

    @Query("DELETE FROM `tb.sanpham`")
    void deleteAll();

}
