package lam.fpoly.shopthoitrang.manhinhcho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Dao.TbLogoDao;
import lam.fpoly.shopthoitrang.FragmentViewPager.Create_Fragment;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbLogo;
import lam.fpoly.shopthoitrang.R;

public class manhinhcho extends AppCompatActivity {
    RelativeLayout manhinhchao;
    LottieAnimationView loadingmanhinhchao;
    TbLogoDao tbLogoDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);

        tbLogoDao=new TbLogoDao();
        manhinhchao=findViewById(R.id.manhinhchao);
        loadingmanhinhchao=findViewById(R.id.loadingmanhinhchao);
        loadingmanhinhchao.startAnimation(AnimationUtils.loadAnimation(this,R.anim.loading_plane));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(3000);
                    load();
                    Intent i=new Intent(manhinhcho.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();


    }
    public void load(){

        manhinhchao.startAnimation(AnimationUtils.loadAnimation(this,R.anim.manloading));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}