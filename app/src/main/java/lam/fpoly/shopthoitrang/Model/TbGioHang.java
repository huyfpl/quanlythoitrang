package lam.fpoly.shopthoitrang.Model;

public class TbGioHang {
    int idKhachHang;
    int idSanPham;
    int soLuongSP;

    public TbGioHang() {
    }

    public TbGioHang(int idKhachHang, int idSanPham, int soLuongSP) {
        this.idKhachHang = idKhachHang;
        this.idSanPham = idSanPham;
        this.soLuongSP = soLuongSP;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }
}
