package lam.fpoly.shopthoitrang.AccFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button btnDky, btnHuyDky;
    Context context = this;
    List<TbKhachHang> list = new ArrayList<>();
    TbKhachHangDao tbKhachHangDao = new TbKhachHangDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);
        edTenKH = findViewById(R.id.edTenKH);
        edSoDT = findViewById(R.id.edSoDT);
        edDiaChi = findViewById(R.id.edDiaChi);
        edUserName_dky = findViewById(R.id.edUserName_dky);
        edPassWord_dky = findViewById(R.id.edPassword_dky);
        edRePassWord = findViewById(R.id.edRePassword);
        btnDky = findViewById(R.id.btnDky);
        btnHuyDky = findViewById(R.id.btnHuyDky);
        btnDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUserName_dky.getText().toString();
                String userpass = edPassWord_dky.getText().toString();
                String userRepass = edRePassWord.getText().toString();
                String tenKH = edTenKH.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String sdt = edSoDT.getText().toString();

                if(username.equals("") || userpass.equals("") || userRepass.equals("") || tenKH.equals("")|| diachi.equals("")|| sdt.equals("")){
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
                    TbKhachHang tbKhachHang = new TbKhachHang(tenKH,sdt, diachi, username, userpass);
                    tbKhachHangDao.insertRow(tbKhachHang);
                    Toast.makeText(context, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        btnHuyDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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