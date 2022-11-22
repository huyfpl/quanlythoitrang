package lam.fpoly.shopthoitrang.Adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import lam.fpoly.shopthoitrang.FragmentViewPager.All_Fragment;
import lam.fpoly.shopthoitrang.FragmentViewPager.NewProduct_Fragment;
import lam.fpoly.shopthoitrang.FragmentViewPager.Recent_Fragment;
import lam.fpoly.shopthoitrang.FragmentViewPager.Sale_Fragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {

    String [] title = {"Tất cả","Sản phẩm mới","Gần đây","Khuyến mại"};

    public ViewPagerAdapterHome(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                new All_Fragment();
                break;
            case 2:
                new NewProduct_Fragment();
                break;
            case 3:
                new Recent_Fragment();
                break;
            case 4:
                new Sale_Fragment();
                break;
        }
        return new All_Fragment();
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
