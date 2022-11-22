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
import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapterHome;
import lam.fpoly.shopthoitrang.R;


public class Home_Fragment extends Fragment {
    TabLayout idTabHome;
    ViewPager2 idViewHome;

    String [] title = {"Tất cả","Sản phẩm mới","Gần đây","Khuyến mại"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ScrollTab(view);
    }

    public void ScrollTab(View view){
        idTabHome = view.findViewById(R.id.idTabHome);
        idViewHome = view.findViewById(R.id.idViewHome);
        ViewPagerAdapterHome adapter = new ViewPagerAdapterHome(getActivity());
        idViewHome.setAdapter(adapter);
        new TabLayoutMediator(idTabHome, idViewHome,((tab, position) ->
                tab.setText(title[position]))).attach();
        idTabHome.setTabGravity(TabLayout.GRAVITY_CENTER);
        idTabHome.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}