package lam.fpoly.shopthoitrang.FragmentViewPager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Adapter.MyAdapter_GirdView;
import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapter;
import lam.fpoly.shopthoitrang.Dao.TbDanhMucDao;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbDanhMuc;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_DM;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;


public class Create_Fragment extends Fragment {
    public GridView idGridView;
    public MyAdapter_GirdView myAdapterGirdView;
    public List<TbSanPham> list;
    public int id = 1;
    private View view;
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idGridView = view.findViewById(R.id.idGridView);
        context = view.getContext();
        list = new ArrayList<>();

        list = MyDataBase_SP.getInstance(getActivity()).sanPhamDAO().getDataID(3);

        myAdapterGirdView = new MyAdapter_GirdView(context,list,R.layout.layouitem_danhmuc);
        idGridView.setAdapter(myAdapterGirdView);
    }

}