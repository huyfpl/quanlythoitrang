package lam.fpoly.shopthoitrang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Fragment.ThongTinFragment;
import lam.fpoly.shopthoitrang.Dao.TbDanhMucDao;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Fragment.DanhMuc_Fragment;
import lam.fpoly.shopthoitrang.Fragment.GioHang_Fragment;
import lam.fpoly.shopthoitrang.Fragment.Home_Fragment;
import lam.fpoly.shopthoitrang.Model.TbDanhMuc;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_DM;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_SP;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNav;

    private final int ID_HOME = 1;
    private final int ID_DANHMUC = 2;
    private final int ID_GIOHANG = 3;
    private final int ID_ACC = 4;

    public static int frame;
    SharedPreferences sharedPreferences;
    public static boolean checkLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);

        MyDataBase_Temporary.getInstance(this).donHangDAO().delete();
        downloadSanPhamLocal();
        downloadDanhMucLocal();
        bottomNav = findViewById(R.id.bottomNav);
        frame = R.id.flFragment;
        //dang nhap to main
        if(checkLogin){
            replaceFragment(new ThongTinFragment());
        }else{
            replaceFragment(new Home_Fragment());
        }
        bottomNav.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.home_heart_fill));
        bottomNav.add(new MeowBottomNavigation.Model(ID_DANHMUC,R.drawable.category));
        bottomNav.add(new MeowBottomNavigation.Model(ID_GIOHANG,R.drawable.shopping_cart));
        bottomNav.add(new MeowBottomNavigation.Model(ID_ACC,R.drawable.user_avatar_filled));

        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        replaceFragment(new Home_Fragment());
                        MyDataBase_Temporary.getInstance(MainActivity.this).donHangDAO().delete();
                        break;
                    case ID_DANHMUC:
                        replaceFragment(new DanhMuc_Fragment());
                        MyDataBase_Temporary.getInstance(MainActivity.this).donHangDAO().delete();
                        break;
                    case ID_GIOHANG:
                        replaceFragment(new GioHang_Fragment());
                        MyDataBase_Temporary.getInstance(MainActivity.this).donHangDAO().delete();
                        break;
                    case ID_ACC:
                        if (checkLogin) {
                            replaceFragment(new ThongTinFragment());
                        }else{
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }
                        MyDataBase_Temporary.getInstance(MainActivity.this).donHangDAO().delete();
                        break;
                }
            }
        });

        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
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
                        if (checkLogin) {
                            replaceFragment(new ThongTinFragment());
                        }else{
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        });

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });


        if(checkLogin){
            bottomNav.show(ID_ACC,true);
        }else{
            bottomNav.show(ID_HOME,true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        DangNhapActivity.ID = sharedPreferences.getInt("idkh", 0);
        checkLogin = sharedPreferences.getBoolean("checked", Boolean.parseBoolean(""));
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frame,fragment);
        transaction.commit();
    }


    private void downloadSanPhamLocal(){
        MyDataBase_SP.getInstance(this).sanPhamDAO().deleteAll();
        TbSanPhamDao sanPhamDao = new TbSanPhamDao();
        List<TbSanPham> list = sanPhamDao.getAll();
        for (int i = 0 ; i < list.size() ; i++){
            int idSp = list.get(i).getId_sanPham();
            String tenSP = list.get(i).getTen_sanPham();
            String anhSP = list.get(i).getSrcAnh();
            int giaNhapSP = list.get(i).getGiaNhap();
            int giaBanSP = list.get(i).getGiaBan();
            int tonKho = list.get(i).getTonKho();
            int danhMuc = list.get(i).getId_danhmuc();
            String info = list.get(i).getInfo();
            TbSanPham tbSanPham = new TbSanPham(idSp,tenSP,anhSP,giaNhapSP,giaBanSP,tonKho,danhMuc,info);
            MyDataBase_SP.getInstance(this).sanPhamDAO().insertData(tbSanPham);
        }
    }

    private void downloadDanhMucLocal(){
        MyDataBase_DM.getInstance(this).danhMucDAO().deleteAll();
        TbDanhMucDao danhMucDao = new TbDanhMucDao();
        List<TbDanhMuc> list = danhMucDao.getAll();
        for (int i = 0 ; i < list.size() ; i++){
            int idDM = list.get(i).getId_danhMuc();
            String tenDM = list.get(i).getTen_danhMuc();
            TbDanhMuc tbDanhMuc = new TbDanhMuc(idDM,tenDM);
            MyDataBase_DM.getInstance(this).danhMucDAO().insertData(tbDanhMuc);
        }
    }

    @Override
    protected void onDestroy() {
        MyDataBase_Temporary.getInstance(this).donHangDAO().delete();
        super.onDestroy();
    }



    //animation dưới lên
//    ObjectAnimator anim = ObjectAnimator.ofFloat(imgLogo,"translationY",0,-1000f);
//        anim.setDuration(1500);
//        anim.start();

}