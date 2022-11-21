package lam.fpoly.shopthoitrang.AccFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.Adapter.HoaDonAdapter;
import lam.fpoly.shopthoitrang.Dao.TbHoaDonDao;
import lam.fpoly.shopthoitrang.Model.TbHoaDon;
import lam.fpoly.shopthoitrang.R;

public class QuanLyDonHang extends AppCompatActivity {
    RecyclerView recyclerView;
    TbHoaDonDao tbHoaDonDao;
    List<TbHoaDon> list;
    HoaDonAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);
        recyclerView = findViewById(R.id.rsv_dh);

        adapter = new HoaDonAdapter();
        list = new ArrayList<>();
        tbHoaDonDao = new TbHoaDonDao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = tbHoaDonDao.getAll();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }
}