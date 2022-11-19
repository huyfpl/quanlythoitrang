package lam.fpoly.shopthoitrang.Model;

public class TbKhachHang {
    int id_KhachHang;
    String tenKH;
    String SDT;
    String DiaChi;
    String userName;
    String userPass;

    public TbKhachHang() {
    }

    public TbKhachHang(String tenKH, String SDT, String diaChi, String user, String pass) {
        this.tenKH = tenKH;
        this.SDT = SDT;
        DiaChi = diaChi;
        userName = user;
        userPass = pass;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
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
