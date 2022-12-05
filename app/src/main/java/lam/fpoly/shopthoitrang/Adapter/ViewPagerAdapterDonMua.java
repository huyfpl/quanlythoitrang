package lam.fpoly.shopthoitrang.Adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import lam.fpoly.shopthoitrang.DonMua.Fragment.DonMua_Fragment;

public class ViewPagerAdapterDonMua extends FragmentStateAdapter {

    String [] title = {"Chờ xác nhận","Chờ lấy hàng","Đang giao","Đã giao","Đã hủy"};

    public ViewPagerAdapterDonMua(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new DonMua_Fragment();
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
