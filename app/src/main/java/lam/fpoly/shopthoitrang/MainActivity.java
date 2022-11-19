package lam.fpoly.shopthoitrang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import lam.fpoly.shopthoitrang.Fragment.Acc_Fragment;
import lam.fpoly.shopthoitrang.Fragment.DanhMuc_Fragment;
import lam.fpoly.shopthoitrang.Fragment.GioHang_Fragment;
import lam.fpoly.shopthoitrang.Fragment.Home_Fragment;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNav;

    private final int ID_HOME = 1;
    private final int ID_DANHMUC = 2;
    private final int ID_GIOHANG = 3;
    private final int ID_ACC = 4;

    //private final int CURRENT_FRAGMENT = ID_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.home_heart_fill));
        bottomNav.add(new MeowBottomNavigation.Model(ID_DANHMUC,R.drawable.category));
        bottomNav.add(new MeowBottomNavigation.Model(ID_GIOHANG,R.drawable.shopping_cart));
        bottomNav.add(new MeowBottomNavigation.Model(ID_ACC,R.drawable.user_avatar_filled));

        replaceFragment(new Home_Fragment());

        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        replaceFragment(new Home_Fragment());
                        break;
                    case ID_DANHMUC:
                        replaceFragment(new DanhMuc_Fragment());
                        break;
                    case ID_GIOHANG:
                        replaceFragment(new GioHang_Fragment());
                        break;
                    case ID_ACC:
                        replaceFragment(new Acc_Fragment());
                        break;
                }
            }
        });

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNav.show(ID_HOME,true);

    }



    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment,fragment);
        transaction.commit();
    }


    //animation dưới lên
//    ObjectAnimator anim = ObjectAnimator.ofFloat(imgLogo,"translationY",0,-1000f);
//        anim.setDuration(1500);
//        anim.start();

}