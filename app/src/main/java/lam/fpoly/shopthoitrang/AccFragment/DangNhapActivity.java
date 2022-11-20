package lam.fpoly.shopthoitrang.AccFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.R;

public class DangNhapActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    CheckBox chkRememberPass;
    Button btnLogin, btnCancel;
    Context context;
    List<TbKhachHang> list;
    TbKhachHangDao tbKhachHangDao;
    public static int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        list = new ArrayList<>();
        context = this;
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
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
                                Intent intent = new Intent(context,ThongTinTaiKhoan.class);
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
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

