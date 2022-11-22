package lam.fpoly.shopthoitrang.ActivitySanPham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Adapter.HoaDonAdapter;
import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;
import lam.fpoly.shopthoitrang.R;

public class HoaDonChiTiet extends AppCompatActivity {
    TextView tvName_HDCT,tvSDT_HDCT,tvDiaChi_HDCT,tvTongTienHang_HDCT,tvTienHang_HDCT,
            tvPhiShip_HDCT,tvVoucher_HDCT,tvTongTienThanhToan,tvDatHang_HDCT;
    RecyclerView idRcv_HDCT;
    LinearLayout idSelectVoucher;
    int SP_ID = 0;
    List<DonHang_Temorary> list;
    HoaDonAdapter hoaDonAdapter;
    int phiShip = 50000;
    int tienHang;
    int tongTien = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        tvName_HDCT = findViewById(R.id.tvName_HDCT);
        tvSDT_HDCT = findViewById(R.id.tvSDT_HDCT);
        tvDiaChi_HDCT = findViewById(R.id.tvDiaChi_HDCT);
        tvTongTienHang_HDCT = findViewById(R.id.tvTongTienHang_HDCT);
        tvTienHang_HDCT = findViewById(R.id.tvTienHang_HDCT);
        tvPhiShip_HDCT = findViewById(R.id.tvPhiShip_HDCT);
        tvVoucher_HDCT = findViewById(R.id.tvVoucher_HDCT);
        tvTongTienThanhToan = findViewById(R.id.tvTongTienThanhToan);
        tvDatHang_HDCT = findViewById(R.id.tvDatHang_HDCT);
        idRcv_HDCT = findViewById(R.id.idRcv_HDCT);
        idSelectVoucher = findViewById(R.id.idSelectVoucher);

        list = new ArrayList<>();

        hoaDonAdapter = new HoaDonAdapter(new HoaDonAdapter.InterClickItemData() {
            @Override
            public void clickUp() {
                tongTienHang();
            }

            @Override
            public void clickDown() {
                tongTienHang();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        idRcv_HDCT.setLayoutManager(manager);
        idRcv_HDCT.setAdapter(hoaDonAdapter);
        loadData();

        tvDatHang_HDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        thongTinGiaoHang();
        ship();
        tongTienHang();
        tvVoucher_HDCT.setText("- 0 đ");

    }
    private void loadData(){
        Intent intent = getIntent();
        SP_ID = intent.getIntExtra("SP_ID",0);
        if (SP_ID == 0){
            list = MyDataBase_Temporary.getInstance(this).donHangDAO().getListData();
            hoaDonAdapter.setData(list);
        }else{
            //list = MyDataBase_SP.getInstance(this).sanPhamDAO().getSP_ID(SP_ID);
        }
    }

    private void thongTinGiaoHang(){
        TbKhachHangDao tbKhachHangDao = new TbKhachHangDao();
        TbKhachHang tbKhachHang = tbKhachHangDao.getUser(DangNhapActivity.ID);
        tvName_HDCT.setText(tbKhachHang.getTen_khachHang());
        tvSDT_HDCT.setText(tbKhachHang.getSdt_khachHang());
        tvDiaChi_HDCT.setText(tbKhachHang.getDiaChi());
    }

    private void tongTienHang(){
        tienHang = 0;
        loadData();
        for(int i = 0 ; i < list.size() ; i++){
            tienHang += list.get(i).getGia_sanPham() * list.get(i).getSoLuong();
            tvTienHang_HDCT.setText(String.valueOf(tienHang)+" đ");
            tvTongTienHang_HDCT.setText(String.valueOf(tienHang)+" đ");
        }
        total();
    }

    private void total(){
        tongTien = tienHang + phiShip;
        tvTongTienThanhToan.setText(String.valueOf(tongTien)+" đ");
    }

    private void ship(){
        tvPhiShip_HDCT.setText(String.valueOf(phiShip)+" đ");
    }

    @Override
    public void onBackPressed() {
        MyDataBase_Temporary.getInstance(this).donHangDAO().delete();
        super.onBackPressed();
    }
}