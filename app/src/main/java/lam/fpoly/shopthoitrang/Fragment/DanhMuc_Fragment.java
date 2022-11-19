package lam.fpoly.shopthoitrang.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapter;
import lam.fpoly.shopthoitrang.R;


public class DanhMuc_Fragment extends Fragment {

    private TabLayout idTabDanhMuc;
    private ViewPager2 pager_DanhMuc;

    String [] title = {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danh_muc, container, false);
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
}