package lam.fpoly.shopthoitrang.Model;

public class TbFeedBack {
    int id_khachhang;
    int id_sanpham;
    int soStar;
    String mess;
    String anh;

    public TbFeedBack() {
    }

    public TbFeedBack(int id_khachhang, int id_sanpham, int soStar, String mess, String anh) {
        this.id_khachhang = id_khachhang;
        this.id_sanpham = id_sanpham;
        this.soStar = soStar;
        this.mess = mess;
        this.anh = anh;
    }

    public int getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(int id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public int getSoStar() {
        return soStar;
    }

    public void setSoStar(int soStar) {
        this.soStar = soStar;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
