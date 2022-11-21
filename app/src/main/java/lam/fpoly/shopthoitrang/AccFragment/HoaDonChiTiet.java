package lam.fpoly.shopthoitrang.AccFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbHoaDonChiTietDao;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;
import lam.fpoly.shopthoitrang.R;

public class HoaDonChiTiet extends AppCompatActivity {
    TextView hdct_hoten, hdct_sdt, hdct_diachi, hdct_trangthai, hdct_tensp_item, hdct_gia_item, hdct_soluong_item, hdct_tt_tensp, hdct_tt_tienhang, hdct_tt_ship, hdct_tt_tongtien;
    ImageView hdct_anh_item;
    Button btnSave;
    TbHoaDonChiTietDao tbHoaDonChiTietDao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        hdct_hoten = findViewById(R.id.hdct_hoten);
        hdct_sdt = findViewById(R.id.hdct_sdt);
        hdct_diachi = findViewById(R.id.hdct_diachi);
        hdct_trangthai = findViewById(R.id.hdct_trangthai);
        hdct_tensp_item = findViewById(R.id.hdct_tensp_item);
        hdct_gia_item = findViewById(R.id.hdct_gia_item);
        hdct_soluong_item = findViewById(R.id.hdct_soluong_item);
        hdct_tt_tensp = findViewById(R.id.hdct_tt_tensp);
        hdct_tt_tienhang = findViewById(R.id.hdct_tt_tienhang);
        hdct_tt_ship = findViewById(R.id.hdct_tt_ship);
        hdct_tt_tongtien = findViewById(R.id.hdct_tt_tongtien);
        hdct_anh_item = findViewById(R.id.hdct_anh_item);
        btnSave = findViewById(R.id.btnSave_hdct);

        tbHoaDonChiTietDao = new TbHoaDonChiTietDao();


        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten_sp");
        int id = intent.getIntExtra("id",0);
        int gia = intent.getIntExtra("gia_sp",0);
        int soluongsp = intent.getIntExtra("soluong_sp",0);
        String anh = intent.getStringExtra("anh_sp");
        String trangthai = intent.getStringExtra("trangthai_sp");

        TbHoaDonChiTiet obj = tbHoaDonChiTietDao.getAll(id);
        hdct_hoten.setText(obj.getHoten());
        hdct_sdt.setText(obj.getSdt()+"");
        hdct_diachi.setText(obj.getDiachi());

        hdct_trangthai.setText(trangthai);
        hdct_tensp_item.setText(ten);
        hdct_gia_item.setText(gia+".000đ");
        hdct_soluong_item.setText(""+soluongsp);
        Picasso.get().load(anh).fit().into(hdct_anh_item);

        int tienhang = gia * soluongsp;
        int tienship = 34;

        hdct_tt_tensp.setText(ten+" x "+soluongsp);
        hdct_tt_tienhang.setText(tienhang+".000đ");
        hdct_tt_ship.setText("34.000đ");
        hdct_tt_tongtien.setText(tienhang + tienship+".000đ");
    }
}