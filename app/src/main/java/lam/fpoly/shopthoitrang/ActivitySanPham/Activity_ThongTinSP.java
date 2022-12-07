package lam.fpoly.shopthoitrang.ActivitySanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.ActivityDatHang.HoaDonChiTiet;
import lam.fpoly.shopthoitrang.Adapter.FeedBackAdapter;
import lam.fpoly.shopthoitrang.Dao.TbFeedBackDao;
import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.Dao.TbHoaDonChiTietDao;
import lam.fpoly.shopthoitrang.Dao.TbSaleSPDao;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbFeedBack;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;

public class Activity_ThongTinSP extends AppCompatActivity {
    ImageView imgSanPham_CT,imgBack_CT;
    TextView tvNameSP_CT,tvPriceSP_CT,idMuaNgay_CT,tvPriceSP_CT_sale,tvInfo;
    LinearLayout idGioHang_CT;
    int idSP;
    TbSanPham tbSanPham;
    TbSaleSPDao tbSaleSPDao;
    TbFeedBackDao tbFeedBackDao;
    ListView rsv_fb;
    List<TbFeedBack> listFb;
    FeedBackAdapter feedBackAdapter;
    public static boolean checkSale = false;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiti_thong_tin_sp);
        imgSanPham_CT = findViewById(R.id.imgSanPham_CT);
        tvNameSP_CT = findViewById(R.id.tvNameSP_CT);
        tvPriceSP_CT = findViewById(R.id.tvPriceSP_CT);
        idMuaNgay_CT = findViewById(R.id.idMuaNgay_CT);
        idGioHang_CT = findViewById(R.id.idGioHang_CT);
        tvPriceSP_CT_sale = findViewById(R.id.tvPriceSP_CT_sale);
        tvInfo = findViewById(R.id.tvInfo);
        rsv_fb = findViewById(R.id.rsv_feedBack);
        loadDataSP();
        listFb = new ArrayList<>();
        tbFeedBackDao = new TbFeedBackDao();
        listFb = tbFeedBackDao.getAll(idSP);
        feedBackAdapter = new FeedBackAdapter(this,listFb);
        Log.i("TAG", "activity: "+listFb.size());
        rsv_fb.setAdapter(feedBackAdapter);
        imgBack_CT = findViewById(R.id.imgBack_CT);
        imgBack_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idMuaNgay_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkLogin){
                    Intent intent = new Intent(Activity_ThongTinSP.this, HoaDonChiTiet.class);
                    intent.putExtra("SP_ID",idSP);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Activity_ThongTinSP.this, DangNhapActivity.class);
                    intent.putExtra("mua",1);
                    startActivity(intent);
                }
            }
        });

        idGioHang_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkLogin){
                    TbGioHangDao tbGioHangDao = new TbGioHangDao();
                    int gia = tbSanPham.getGiaBan();
                    if (checkSale){
                        gia = gia*(100-tbSaleSPDao.getGia_idsp(idSP))/100;
                    }
                    TbGioHang tbGioHang = new TbGioHang(DangNhapActivity.ID,idSP,1,gia);
                    if (tbGioHangDao.checkExist(tbGioHang)){
                        tbGioHangDao.updateRow(tbGioHang);
                        Toast.makeText(Activity_ThongTinSP.this,"Đã thêm vào giỏ hàng - update",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        tbGioHangDao.insertRow(tbGioHang);
                        Toast.makeText(Activity_ThongTinSP.this,"Đã thêm vào giỏ hàng - insert",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent intent = new Intent(Activity_ThongTinSP.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadDataSP(){
        tbSaleSPDao = new TbSaleSPDao();
        Intent intent = getIntent();
        idSP = intent.getIntExtra("SP_VALUES",0);
        tbSanPham = MyDataBase_SP.getInstance(this).sanPhamDAO().getDataIdSP(idSP);
        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(imgSanPham_CT);
        tvNameSP_CT.setText(tbSanPham.getTen_sanPham());
        tvInfo.setText(tbSanPham.getInfo());
        int gia = tbSanPham.getGiaBan();
        tvPriceSP_CT.setText(gia+".000đ");
        tvPriceSP_CT_sale.setVisibility(View.INVISIBLE);
        if (checkSale){
            int gia_sale = tbSanPham.getGiaBan()*(100-tbSaleSPDao.getGia_idsp(idSP))/100;
            tvPriceSP_CT_sale.setVisibility(View.VISIBLE);
            tvPriceSP_CT.setText(gia_sale+".000đ");
            tvPriceSP_CT_sale.setText(gia+".000đ");
            tvPriceSP_CT_sale.setPaintFlags(tvPriceSP_CT_sale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}