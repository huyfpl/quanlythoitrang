package lam.fpoly.shopthoitrang.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Adapter.SanPhamAdapter;
import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapter;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.R;


public class DanhMuc_Fragment extends Fragment {

    private TabLayout idTabDanhMuc;
    private ViewPager2 pager_DanhMuc;
    SanPhamAdapter adapter;
    List<TbSanPham> list;
    Toolbar toolbar;
    TextView soluong;
    LottieAnimationView loading;
    String [] title = {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_danh_muc, container, false);
        setHasOptionsMenu(true);
        loading=view.findViewById(R.id.loading);
        toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        soluong = view.findViewById(R.id.soluong);
        TbSanPhamDao catDao = new TbSanPhamDao();

        //======== Thêm mới 1 dòng
        TbSanPham newObjCat = new TbSanPham();
        newObjCat.setTen_sanPham("Thể loại mới");

        catDao.insertRow(newObjCat);


        //Sửa dữ liệu:
        TbSanPham objCatUpdate = new TbSanPham();
        objCatUpdate.setId_sanPham(3);
        objCatUpdate.setTen_sanPham("Dép bitis");

        catDao.updateRow(objCatUpdate);


        // bước 9 thì không cần phần trên, dùng DAO để lấy dữ liệu

        List<TbSanPham> listCat = catDao.getAll(); // lấy danh sách cho vào biến

        // duyệt mảng in ra danh sách

        list = new ArrayList<>();

        for (int i = 0; i < listCat.size(); i++) {
            TbSanPham obj = listCat.get(i);
            Log.i("TAG", "onCreate: " + obj.getSrcAnh());
            list.add(new TbSanPham(obj.getTen_sanPham(), obj.getSrcAnh(), obj.getGiaNhap(), obj.getGiaBan(), obj.getTonKho()));
        }

        // up lên rsv
        RecyclerView rsv = view.findViewById(R.id.rsv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rsv.setLayoutManager(linearLayoutManager);

        adapter = new SanPhamAdapter(getActivity(),list);
        rsv.setAdapter(adapter);
        // so luong
        int sl = catDao.count();
        soluong.setText(sl + "");


        return view;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        };
        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);

        SearchView searchView= (SearchView)menu.findItem(R.id.search) .getActionView();
        searchView.setQueryHint("nhập sản phẩm cần tìm nhé....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText.toString());
                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ScrollTab(view);
    }

//    public void ScrollTab(View view){
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
//        idTabDanhMuc = view.findViewById(R.id.idTabDanhMuc);
//        pager_DanhMuc = view.findViewById(R.id.pager_DanhMuc);
//        new TabLayoutMediator(idTabDanhMuc, pager_DanhMuc,((tab, position) -> tab.setText(title[position]))).attach();
//        idTabDanhMuc.setTabGravity(TabLayout.GRAVITY_CENTER);
//        pager_DanhMuc.setAdapter(adapter);
//        idTabDanhMuc.setTabMode(TabLayout.MODE_SCROLLABLE);
//    }
// tìm sản phẩm

}