package lam.fpoly.shopthoitrang.FragmentViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Adapter.MyAdapter_GirdView;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;


public class All_Fragment extends Fragment {

    public GridView idGridViewHome;
    MyAdapter_GirdView myAdapterGirdView;
    public List<TbSanPham> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idGridViewHome = view.findViewById(R.id.idGridViewHome);
        list = new ArrayList<>();
        list = MyDataBase_SP.getInstance(getActivity()).sanPhamDAO().getDataAll();
        myAdapterGirdView = new MyAdapter_GirdView(getActivity(),list,R.layout.layoutitem_home);
        idGridViewHome.setAdapter(myAdapterGirdView);
    }
}