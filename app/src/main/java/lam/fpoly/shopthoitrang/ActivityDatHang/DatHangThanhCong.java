package lam.fpoly.shopthoitrang.ActivityDatHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import lam.fpoly.shopthoitrang.DonMua.DonMua_Activity;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.R;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class DatHangThanhCong extends AppCompatActivity {
    LottieAnimationView animationView;
    KonfettiView konfettiView;
    LinearLayout idDonMua, idTrangChu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang_thanh_cong);
        animationView = findViewById(R.id.animationView);
        konfettiView = findViewById(R.id.konfettiView);
        idDonMua = findViewById(R.id.idDonMua);
        idTrangChu = findViewById(R.id.idTrangChu);
        startKonfettiView();

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startKonfettiView();
            }
        });

        idTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHangThanhCong.this, MainActivity.class);
                startActivity(intent);
            }
        });

        idDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHangThanhCong.this, DonMua_Activity.class);
                startActivity(intent);
            }
        });


    }

    private void startKonfettiView(){
        Shape.DrawableShape drawableShapes = new Shape.DrawableShape(AppCompatResources.getDrawable(this,R.drawable.ic_android),true);
        EmitterConfig emitterConfig = new Emitter(300, TimeUnit.MILLISECONDS).max(300);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .shapes(Shape.Circle.INSTANCE ,Shape.Square.INSTANCE ,drawableShapes)
                        .spread(360)
                        .position(0.5,0.25,1,1)
                        .sizes(new Size(8,50,10))
                        .timeToLive(5000).fadeOutEnabled(true)
                        .build()
        );
    }

}