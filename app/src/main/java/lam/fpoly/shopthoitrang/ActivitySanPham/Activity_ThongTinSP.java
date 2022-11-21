package lam.fpoly.shopthoitrang.ActivitySanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;

public class Activity_ThongTinSP extends AppCompatActivity {
    ImageView imgSanPham_CT,imgBack_CT,imgGioHang_CT;
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