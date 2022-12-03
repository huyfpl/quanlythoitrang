package lam.fpoly.shopthoitrang.Fragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.ActivitySanPham.HoaDonChiTiet;
import lam.fpoly.shopthoitrang.Adapter.GioHangAdapter;
import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;
import lam.fpoly.shopthoitrang.R;


public class GioHang_Fragment extends Fragment {
    CheckBox imgCheckBox_GH;
    RecyclerView idRcv_GH;
    LinearLayout idLayOutMuaHang;
    TextView tvMuaNgay_GH, tvTongTien_GH;
    List<TbGioHang> list;
    TbGioHangDao tbGioHangDao;
    GioHangAdapter gioHangAdapter;
    int total = 0;
    int showLayout = 0;
    DonHang_Temorary donHang;
    int tienHang = 0;
    public static boolean checkAll = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgCheckBox_GH = view.findViewById(R.id.imgCheckBox_GH);
        idRcv_GH = view.findViewById(R.id.idRcv_GH);
        idLayOutMuaHang = view.findViewById(R.id.idLayOutMuaHang);
        tvMuaNgay_GH = view.findViewById(R.id.tvMuaNgay_GH);
        tvTongTien_GH = view.findViewById(R.id.tvTongTien_GH);

        imgCheckBox_GH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loadData();
                if (isChecked){
                    checkAll = true;
                }else {
                    checkAll = false;
                }
            }
        });


        tbGioHangDao = new TbGioHangDao();
        gioHangAdapter = new GioHangAdapter(new GioHangAdapter.InterClickItemData() {
            @Override
            public void clickCheck(TbSanPham tbSanPham, int position, TbGioHang tbGioHang, boolean isCheck) {
                if (isCheck) {
                    total += tbSanPham.getGiaBan();
                    tvTongTien_GH.setText(String.valueOf(total));
                    showLayout++;
                    showLayOut();
                    donHang = new DonHang_Temorary(position, tbSanPham.getId_sanPham(),
                            tbSanPham.getTen_sanPham(), tbSanPham.getGiaBan(),
                            tbSanPham.getSrcAnh(), tbGioHang.getSoLuongSP());
                    MyDataBase_Temporary.getInstance(getActivity()).donHangDAO().insertData(donHang);
                    tongTienHang();
                } else {
                    total -= tbSanPham.getGiaBan();
                    tvTongTien_GH.setText(String.valueOf(total));
                    showLayout--;
                    showLayOut();
                    MyDataBase_Temporary.getInstance(getActivity()).donHangDAO().deletePosition(position);
                }
            }

            @Override
            public void clickUp() {
                tongTienHang();
            }

            @Override
            public void clickDown() {
                tongTienHang();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        idRcv_GH.setLayoutManager(manager);
        idRcv_GH.setAdapter(gioHangAdapter);
        loadData();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(idRcv_GH);

        tvMuaNgay_GH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HoaDonChiTiet.class);
                startActivity(intent);
            }
        });

    }

    private void tongTienHang() {
        List<DonHang_Temorary> mList = new ArrayList<>();
        mList = MyDataBase_Temporary.getInstance(getActivity()).donHangDAO().getListData();
        tienHang = 0;
        for (int i = 0; i < mList.size(); i++) {
            tienHang += mList.get(i).getGia_sanPham() * mList.get(i).getSoLuong();
            tvTongTien_GH.setText(String.valueOf(tienHang));
        }
    }

    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }


        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getLayoutPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    tbGioHangDao.deleteRow(DangNhapActivity.ID, list.get(position).getIdSanPham());
                    list.remove(position);
                    idRcv_GH.getAdapter().notifyItemRemoved(position);
                    gioHangAdapter.setData(list);
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_forever_24)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.red))
                    .addSwipeLeftBackgroundColor(getResources().getColor(R.color.gray))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void showLayOut() {
        if (showLayout > 0) {
            idLayOutMuaHang.setVisibility(View.VISIBLE);
        } else {
            idLayOutMuaHang.setVisibility(View.GONE);
        }
    }

    public void loadData() {
        list = tbGioHangDao.getKhachHang(DangNhapActivity.ID);
        gioHangAdapter.setData(list);
    }


}