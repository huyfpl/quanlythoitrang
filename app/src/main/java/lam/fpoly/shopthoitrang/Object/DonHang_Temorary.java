package lam.fpoly.shopthoitrang.Object;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "donhang")
public class DonHang_Temorary {
    @PrimaryKey
    int id;
    int id_sanPham;
    String ten_sanPham;
    int gia_sanPham;
    String anh_sanPham;
    int soLuong;

    public DonHang_Temorary(int id, int id_sanPham, String ten_sanPham, int gia_sanPham, String anh_sanPham, int soLuong) {
        this.id = id;
        this.id_sanPham = id_sanPham;
        this.ten_sanPham = ten_sanPham;
        this.gia_sanPham = gia_sanPham;
        this.anh_sanPham = anh_sanPham;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(int id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    public String getTen_sanPham() {
        return ten_sanPham;
    }

    public void setTen_sanPham(String ten_sanPham) {
        this.ten_sanPham = ten_sanPham;
    }

    public int getGia_sanPham() {
        return gia_sanPham;
    }

    public void setGia_sanPham(int gia_sanPham) {
        this.gia_sanPham = gia_sanPham;
    }

    public String getAnh_sanPham() {
        return anh_sanPham;
    }

    public void setAnh_sanPham(String anh_sanPham) {
        this.anh_sanPham = anh_sanPham;
    }
}
