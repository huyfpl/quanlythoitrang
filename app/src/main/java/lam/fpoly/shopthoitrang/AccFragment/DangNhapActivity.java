package lam.fpoly.shopthoitrang.AccFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Fragment.ThongTinFragment;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.R;

public class DangNhapActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    TextView btnLogin, btnSignup;
    Context context;
    List<TbKhachHang> list;
    TbKhachHangDao tbKhachHangDao;
    ImageView idBackActivity;
    public static int ID;
    LinearLayout animlogin;
    public static SharedPreferences sharedPreferences;
    public static String usernameSave, PassSave;
    public static boolean checked = false;

    public void saveSharedPref(int idkh, Boolean check) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idkh", idkh);
        editor.putBoolean("checked", check);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        list = new ArrayList<>();
        context = this;
        edUserName = findViewById(R.id.edUserName);
        idBackActivity = findViewById(R.id.idBackActivity);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignUp);

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        checked=sharedPreferences.getBoolean("checked", Boolean.parseBoolean(""));
        //Log.i("TAG", "onCreate checked: "+checked);


        animlogin = findViewById(R.id.anim_login);
        animlogin.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animation_login));

        idBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserName.getText().toString();
                String userPass = edPassword.getText().toString();
                if (userName.equals("") || userPass.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn chưa nhập đủ thông tin");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                    return;
                }
                tbKhachHangDao = new TbKhachHangDao();
                list = tbKhachHangDao.getAll();
                boolean check = false;
                for (TbKhachHang tbKhachHang : list) {
                    if (userName.equals(tbKhachHang.getUserName())) {
                        check = true;
                        ID = tbKhachHangDao.getID(userName);
                        break;
                    }
                }
                if (check) {
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
                                saveSharedPref(ID,true);
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Người dùng không tồn tại!");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        });
    }

    private void saveInfor() {
        saveSharedPref(ID, MainActivity.checkLogin);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ThongTinFragment.clear) {
            sharedPreferences.edit().clear().commit();
        } else {
            ID = sharedPreferences.getInt("idkh", 0);
            MainActivity.checkLogin = sharedPreferences.getBoolean("checked", Boolean.parseBoolean(""));
            Log.i("TAG", "onCreate: " + MainActivity.checkLogin);

        }
        if (checked){
            MainActivity.checkLogin = true;
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        }
//        checked = MainActivity.checkLogin;
//        Log.i("TAG", "onResume: " + checked);
//        Log.i("TAG", "onResume: " + ID);
//        if (checked) {
//            MainActivity.checkLogin = checked;
//            String userName = edUserName.getText().toString();
//            String userPass = edPassword.getText().toString();
//            tbKhachHangDao = new TbKhachHangDao();
//            list = tbKhachHangDao.getAll();
//            boolean check = false;
//            for (TbKhachHang tbKhachHang : list) {
//                if (userName.equals(tbKhachHang.getUserName())) {
//                    check = true;
//                    ID = tbKhachHangDao.getID(userName);
//                    break;
//                }
//            }
//            if (check) {
//                for (TbKhachHang tbKhachHang : list) {
//                    if (userName.equals(tbKhachHang.getUserName())) {
//                        if (userPass.equals(tbKhachHang.getUserPass())) {
//                            MainActivity.checkLogin = true;
//                            saveSharedPref(ID, true);
//                            finish();
//                        }
//                    }
//                }
//            }
//        }
    }
}

