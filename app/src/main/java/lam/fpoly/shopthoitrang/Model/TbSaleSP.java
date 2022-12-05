package lam.fpoly.shopthoitrang.Model;

public class TbSaleSP {
    int id_sanpham;
    int sale;

    public TbSaleSP() {
    }

    public TbSaleSP(int id_sanpham, int sale) {
        this.id_sanpham = id_sanpham;
        this.sale = sale;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}
