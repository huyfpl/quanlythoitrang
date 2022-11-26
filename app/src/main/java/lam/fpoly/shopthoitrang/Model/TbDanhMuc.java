package lam.fpoly.shopthoitrang.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "danhmuc")
public class TbDanhMuc {
    @PrimaryKey(autoGenerate = true)
    int id;
    int id_danhMuc;
    String ten_danhMuc;

    public TbDanhMuc() {
    }

    public TbDanhMuc(int id_danhMuc, String ten_danhMuc) {
        this.id_danhMuc = id_danhMuc;
        this.ten_danhMuc = ten_danhMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
