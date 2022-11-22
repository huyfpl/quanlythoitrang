package lam.fpoly.shopthoitrang.ActivitySanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;

public class Activity_ThongTinSP extends AppCompatActivity {
    ImageView imgSanPham_CT,imgBack_CT;
    TextView tvNameSP_CT,tvPriceSP_CT,tvThongTinSP_CT,idMuaNgay_CT;
    LinearLayout idGioHang_CT;
    int idSP;
    TbSanPham tbSanPham;


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

        loadDataSP();

        idMuaNgay_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkLogin){
                    System.out.println(idSP);
                    Intent intent = new Intent(Activity_ThongTinSP.this,HoaDonChiTiet.class);
                    intent.putExtra("SP_ID",idSP);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Activity_ThongTinSP.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        });

        idGioHang_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkLogin){
                    TbGioHangDao tbGioHangDao = new TbGioHangDao();
                    TbGioHang tbGioHang = new TbGioHang(DangNhapActivity.ID,idSP,1);
                    if (tbGioHangDao.checkExist(tbGioHang)){
                        Log.i("TAG", "onClick: "+tbGioHangDao.checkExist(tbGioHang));
                        tbGioHangDao.updateRow(tbGioHang);
                        tbGioHangDao.insertRow(tbGioHang);
                        Toast.makeText(Activity_ThongTinSP.this,"Đã thêm vào giỏ hàng",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("TAG", "onClick------------: "+tbGioHangDao.checkExist(tbGioHang));
                        tbGioHangDao.insertRow(tbGioHang);
                        Toast.makeText(Activity_ThongTinSP.this,"Đã thêm vào giỏ hàng",
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
        Intent intent = getIntent();
        idSP = intent.getIntExtra("SP_VALUES",0);
        tbSanPham = MyDataBase_SP.getInstance(this).sanPhamDAO().getDataIdSP(idSP);
        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(imgSanPham_CT);
        tvNameSP_CT.setText(tbSanPham.getTen_sanPham());
        tvPriceSP_CT.setText(String.valueOf(tbSanPham.getGiaBan())+" $");
    }

}