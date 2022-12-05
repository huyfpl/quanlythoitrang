package lam.fpoly.shopthoitrang.DonMua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapter;
import lam.fpoly.shopthoitrang.Adapter.ViewPagerAdapterDonMua;
import lam.fpoly.shopthoitrang.DonMua.Fragment.DonMua_Fragment;
import lam.fpoly.shopthoitrang.FragmentViewPager.Create_Fragment;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_DM;
import lam.fpoly.shopthoitrang.R;

public class DonMua_Activity extends AppCompatActivity {
    TabLayout idTabDonMua;
    ViewPager2 idViewDonMua;
    String [] title = {"Chờ xác nhận","Chờ lấy hàng","Đang giao","Đã giao","Đã hủy"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_mua);
        idTabDonMua = findViewById(R.id.idTabDonMua);
        idViewDonMua = findViewById(R.id.idViewDonMua);

        ScrollTab();
    }

    public void ScrollTab(){
        ViewPagerAdapterDonMua adapter = new ViewPagerAdapterDonMua(this);
        idViewDonMua.setAdapter(adapter);
        new TabLayoutMediator(idTabDonMua, idViewDonMua,((tab, position) -> tab.setText(title[position]))).attach();
        idTabDonMua.setTabGravity(TabLayout.GRAVITY_CENTER);
        idTabDonMua.setTabMode(TabLayout.MODE_SCROLLABLE);
        idTabDonMua.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                DonMua_Fragment.stt = tab.getPosition()+1;
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Create_Fragment.ID_DM = tab.getPosition()+1;
            }
        });
    }

}