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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Adapter.MyAdapter_GirdView;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Fragment.DanhMuc_Fragment;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.R;


public class Create_Fragment extends Fragment {
    public static GridView idGridView;
    public static MyAdapter_GirdView myAdapterGirdView;
    public static List<TbSanPham> list;
    public static int id;
    private View view;
    public static Context context;
    private TextView soLuongSP;
    public static int ID_DM = 1;
    TbSanPhamDao sanPhamDao;
    public static boolean checkSearch = false;
    public static String name_search;

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
        soLuongSP = view.findViewById(R.id.soLuongSP);
        context = view.getContext();
        list = new ArrayList<>();
        sanPhamDao = new TbSanPhamDao();


    }



    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        Log.i("TAG", "onResume: "+name_search+" - "+checkSearch);
        if(checkSearch){
            list = MyDataBase_SP.getInstance(getActivity()).sanPhamDAO().getDataname(name_search,ID_DM);
        }else {
            list = MyDataBase_SP.getInstance(getActivity()).sanPhamDAO().getDataID(ID_DM);
        }
        myAdapterGirdView = new MyAdapter_GirdView(context, list, R.layout.layouitem_danhmuc);
        idGridView.setAdapter(myAdapterGirdView);
        soLuongSP.setText(list.size() + " sản phẩm");
    }
}