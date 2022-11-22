package lam.fpoly.shopthoitrang.Model;

public class TbHoaDonChiTiet {
    int id_DonHang;
    int id_SanPham;
    int soLuong;

    public TbHoaDonChiTiet() {
    }

    public TbHoaDonChiTiet(int id_DonHang, int id_SanPham, int soLuong) {
        this.id_DonHang = id_DonHang;
        this.id_SanPham = id_SanPham;
        this.soLuong = soLuong;
    }

    public int getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(int id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public int getId_SanPham() {
        return id_SanPham;
    }

    public void setId_SanPham(int id_SanPham) {
        this.id_SanPham = id_SanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
