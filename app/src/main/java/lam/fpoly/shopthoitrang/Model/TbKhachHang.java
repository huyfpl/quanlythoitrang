package lam.fpoly.shopthoitrang.Model;

public class TbKhachHang {
    int id_khachHang;
    String ten_khachHang;
    String sdt_khachHang;
    String diaChi;
    String userName;
    String userPass;

    public TbKhachHang() {
    }

    public TbKhachHang(String tenKH, String SDT, String diaChi, String user, String pass) {
        this.ten_khachHang = tenKH;
        this.sdt_khachHang = SDT;
        this.diaChi = diaChi;
        userName = user;
        userPass = pass;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public String getTen_khachHang() {
        return ten_khachHang;
    }

    public void setTen_khachHang(String ten_khachHang) {
        this.ten_khachHang = ten_khachHang;
    }

    public String getSdt_khachHang() {
        return sdt_khachHang;
    }

    public void setSdt_khachHang(String sdt_khachHang) {
        this.sdt_khachHang = sdt_khachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
