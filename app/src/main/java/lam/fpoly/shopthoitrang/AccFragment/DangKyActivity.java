package lam.fpoly.shopthoitrang.AccFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.R;

public class DangKyActivity extends AppCompatActivity {
    EditText edTenKH, edSoDT, edDiaChi, edUserName_dky, edPassWord_dky, edRePassWord;
    TextView btnDky,signin;
    Context context = this;
    List<TbKhachHang> list = new ArrayList<>();
    TbKhachHangDao tbKhachHangDao = new TbKhachHangDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);
        signin=findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(i);
            }
        });
        edSoDT = findViewById(R.id.edSoDT);
        edUserName_dky = findViewById(R.id.edUserName_dky);
        edPassWord_dky = findViewById(R.id.edPassword_dky);
        edRePassWord = findViewById(R.id.edRePassword);
        btnDky = findViewById(R.id.btnDky);
        btnDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUserName_dky.getText().toString();
                String userpass = edPassWord_dky.getText().toString();
                String userRepass = edRePassWord.getText().toString();
                String sdt = edSoDT.getText().toString();

                if(username.equals("") || userpass.equals("") || userRepass.equals("") || sdt.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Nhập thiếu");
                    builder.setPositiveButton("OK",null);
                    builder.show();
                }else if (ktraTontai(username,list)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Người dùng đã tồn tại");
                    builder.setPositiveButton("OK",null);
                    builder.show();
                }else if(!userpass.equals(userRepass)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Không trùng khớp");
                    builder.setPositiveButton("OK",null);
                    builder.show();
                }else {
                    TbKhachHang tbKhachHang = new TbKhachHang("chưa có thông tin!",sdt, "chưa có thông tin!", username, userpass);
                    tbKhachHangDao.insertRow(tbKhachHang);
                    Toast.makeText(context, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public boolean ktraTontai(String username, List<TbKhachHang> list){
        for (TbKhachHang tbKhachHang : list) {
            if (username.equals(tbKhachHang.getUserName())) {
                return true;
            }
        }
        return false;
    }
}