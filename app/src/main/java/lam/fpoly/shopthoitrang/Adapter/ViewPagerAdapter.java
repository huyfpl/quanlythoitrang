package lam.fpoly.shopthoitrang.Adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbDanhMucDao;
import lam.fpoly.shopthoitrang.FragmentViewPager.Create_Fragment;
import lam.fpoly.shopthoitrang.Model.TbDanhMuc;

public class ViewPagerAdapter extends FragmentStateAdapter {

    TbDanhMucDao danhMucDao = new TbDanhMucDao();

    List<TbDanhMuc> getList = new ArrayList<>();

    public List<TbDanhMuc> getGetList() {
        getList = danhMucDao.getAll();
        return getList;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new Create_Fragment();
    }

    @Override
    public int getItemCount() {
        return getGetList().size();
    }
}
