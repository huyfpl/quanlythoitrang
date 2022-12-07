package lam.fpoly.shopthoitrang.Model;

public class TbGioHang {
    int idKhachHang;
    int idSanPham;
    int soLuongSP;
    int gia;

    public TbGioHang() {
    }

    public TbGioHang(int idKhachHang, int idSanPham, int soLuongSP, int gia) {
        this.idKhachHang = idKhachHang;
        this.idSanPham = idSanPham;
        this.soLuongSP = soLuongSP;
        this.gia = gia;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
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
