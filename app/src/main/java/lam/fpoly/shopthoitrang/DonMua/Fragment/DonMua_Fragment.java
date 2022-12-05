package lam.fpoly.shopthoitrang.DonMua.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Adapter.DonMuaAdapter;
import lam.fpoly.shopthoitrang.Dao.TbDonHangDao;
import lam.fpoly.shopthoitrang.Model.TbDonHang;
import lam.fpoly.shopthoitrang.R;


public class DonMua_Fragment extends Fragment {
    public static int stt = 1;
    RecyclerView idRcvDonMua;
    List<TbDonHang> list;
    DonMuaAdapter donMuaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don_mua_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idRcvDonMua = view.findViewById(R.id.idRcvDonMua);

        list = new ArrayList<>();

        donMuaAdapter = new DonMuaAdapter(getActivity(), new DonMuaAdapter.InterClickItemData() {
            @Override
            public void clickitem(TbDonHang tbDonHang) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        idRcvDonMua.setLayoutManager(manager);
        idRcvDonMua.setAdapter(donMuaAdapter);
        loadData();
    }

    private void loadData() {
        TbDonHangDao tbDonHangDao = new TbDonHangDao();
        list = tbDonHangDao.getTrangThai("Chờ xác nhận", DangNhapActivity.ID);
        donMuaAdapter.setData(list);
        System.out.println(list.size());
//        switch (stt){
//            case 1:
//                break;
//        }
    }
}