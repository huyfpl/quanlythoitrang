package lam.fpoly.shopthoitrang.Model;

public class TbDanhMuc {
    int id_danhMuc;
    String ten_danhMuc;

    public TbDanhMuc() {
    }

    public TbDanhMuc(int id_danhMuc, String ten_danhMuc) {
        this.id_danhMuc = id_danhMuc;
        this.ten_danhMuc = ten_danhMuc;
    }

    public int getId_danhMuc() {
        return id_danhMuc;
    }

    public void setId_danhMuc(int id_danhMuc) {
        this.id_danhMuc = id_danhMuc;
    }

    public String getTen_danhMuc() {
        return ten_danhMuc;
    }

    public void setTen_danhMuc(String ten_danhMuc) {
        this.ten_danhMuc = ten_danhMuc;
    }

}
