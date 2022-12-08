package lam.fpoly.shopthoitrang.ActivityDatHang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.ActivitySanPham.Activity_ThongTinSP;
import lam.fpoly.shopthoitrang.Adapter.HoaDonAdapter;
import lam.fpoly.shopthoitrang.Dao.TbDonHangDao;
import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.Dao.TbHoaDonChiTietDao;
import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Dao.TbSaleSPDao;
import lam.fpoly.shopthoitrang.Model.TbDonHang;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
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
    TbSaleSPDao tbSaleSPDao;
    int phiShip = 50;
    int tienHang;
    int tongTien = 0;
    String trangThai = "Chờ xác nhận";
    SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");


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
        thongTinGiaoHang();
        ship();
        tongTienHang();
        tvVoucher_HDCT.setText("- 0 đ");

        tvDatHang_HDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbDonHangDao tbDonHangDao = new TbDonHangDao();
                TbDonHang tbDonHang = new TbDonHang(DangNhapActivity.ID,trangThai,dateFormat.format(new Date()),tongTien);
                tbDonHangDao.insertRow(tbDonHang);
                TbHoaDonChiTietDao tbHoaDonChiTietDao = new TbHoaDonChiTietDao();
                for(int i = 0 ; i < list.size() ; i++){
                    TbHoaDonChiTiet tbHoaDonChiTiet = new TbHoaDonChiTiet(tbDonHangDao.getIdDonHang(),
                            list.get(i).getId_sanPham(),list.get(i).getSoLuong(),list.get(i).getGia_sanPham());
                    tbHoaDonChiTietDao.insertRow(tbHoaDonChiTiet);
                }
                Intent intent = new Intent(HoaDonChiTiet.this,DatHangThanhCong.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        tbSaleSPDao = new TbSaleSPDao();
        Intent intent = getIntent();
        SP_ID = intent.getIntExtra("SP_ID",0);
        list = new ArrayList<>();
        if (SP_ID == 0){
            list = MyDataBase_Temporary.getInstance(this).donHangDAO().getListData();
            hoaDonAdapter.setData(list);
        }else{
            TbSanPham tbSanPham = MyDataBase_SP.getInstance(this).sanPhamDAO().getDataIdSP(SP_ID);
            int gia = tbSanPham.getGiaBan();
            if (Activity_ThongTinSP.checkSale){
                gia = gia*(100-tbSaleSPDao.getGia_idsp(tbSanPham.getId_sanPham()))/100;
            }

//            List<DonHang_Temorary> temorary = MyDataBase_Temporary.getInstance(this).donHangDAO().getListData();
//            for (DonHang_Temorary dh : temorary){
//                Log.i("TAG", "tên: "+dh.getTen_sanPham()+" - số lượng: "+dh.getSoLuong());
//            }
            list.add(new DonHang_Temorary(tbSanPham.getId(),tbSanPham.getId_sanPham(),
                   tbSanPham.getTen_sanPham(),gia,
                    tbSanPham.getSrcAnh(),1));
            hoaDonAdapter.setData(list);
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
        List<DonHang_Temorary> list = MyDataBase_Temporary.getInstance(this).donHangDAO().getListData();
        for(int i = 0 ; i < list.size() ; i++){
            tienHang += list.get(i).getGia_sanPham() * list.get(i).getSoLuong();
            tvTienHang_HDCT.setText(tienHang+".000đ");
            tvTongTienHang_HDCT.setText(tienHang+".000đ");
        }
        total();
    }

    private void total(){
        tongTien = tienHang + phiShip;
        tvTongTienThanhToan.setText(tongTien+".000đ");
    }

    private void ship(){
        tvPhiShip_HDCT.setText(phiShip+".000đ");
    }

    @Override
    public void onBackPressed() {
        MyDataBase_Temporary.getInstance(this).donHangDAO().delete();
        super.onBackPressed();
    }
}