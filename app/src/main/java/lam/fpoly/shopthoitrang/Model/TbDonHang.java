package lam.fpoly.shopthoitrang.Model;

public class TbDonHang {
    int id_DonHang;
    int id_KhachHang;
    String trangThai;
    String ngayMua;
    int tongTien;

    int slg;

    public int getSlg() {
        return slg;
    }

    public void setSlg(int slg) {
        this.slg = slg;
    }

    public TbDonHang() {
    }

    public TbDonHang(int id_DonHang, int id_KhachHang, String trangThai, String ngayMua, int tongTien) {
        this.id_DonHang = id_DonHang;
        this.id_KhachHang = id_KhachHang;
        this.trangThai = trangThai;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
    }

    public int getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(int id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
