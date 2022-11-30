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
    public static GridView idGridView;
    public static MyAdapter_GirdView myAdapterGirdView;
    public static List<TbSanPham> list;
    public static int id;
    private View view;
    public static Context context;

    public static int ID_DM = 1;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        list = MyDataBase_SP.getInstance(getActivity()).sanPhamDAO().getDataID(ID_DM);
        myAdapterGirdView = new MyAdapter_GirdView(context,list,R.layout.layouitem_danhmuc);
        idGridView.setAdapter(myAdapterGirdView);
    }
}