package lam.fpoly.shopthoitrang.AccFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Fragment.ThongTinFragment;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.R;

public class DangNhapActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    TextView btnLogin,btnSignup;
    ImageView idBackActivity;
    Context context;
    List<TbKhachHang> list;
    TbKhachHangDao tbKhachHangDao;
    public static int ID;
    LinearLayout animlogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        list = new ArrayList<>();
        context = this;
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignUp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        idBackActivity=findViewById(R.id.idBackActivity);

        idBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        animlogin=findViewById(R.id.anim_login);
        animlogin.startAnimation(AnimationUtils.loadAnimation(this,R.anim.animation_login));
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserName.getText().toString();
                String userPass = edPassword.getText().toString();
                if(userName.equals("") || userPass.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn chưa nhập đủ thông tin");
                    builder.setPositiveButton("OK",null);
                    builder.show();
                    return;
                }
                tbKhachHangDao = new TbKhachHangDao();
                list = tbKhachHangDao.getAll();
                boolean check = false;
                for (TbKhachHang tbKhachHang : list) {
                    if (userName.equals(tbKhachHang.getUserName())){
                        check = true;
                        ID = tbKhachHangDao.getID(userName);
                        break;
                    }
                }
                if (check){
                    for (TbKhachHang tbKhachHang : list) {
                        if (userName.equals(tbKhachHang.getUserName())) {
                            if (!userPass.equals(tbKhachHang.getUserPass())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Thông báo");
                                builder.setMessage("Sai mật khẩu!");
                                builder.setPositiveButton("OK", null);
                                builder.show();
                            } else {
                                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                MainActivity.checkLogin = true;
                                Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                                intent.putExtra("ID_ACC",4);
                                startActivity(intent);
                            }
                        }
                    }
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Người dùng không tồn tại!");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

