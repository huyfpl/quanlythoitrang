package lam.fpoly.shopthoitrang.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.AccFragment.DangKyActivity;
import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.Model.TbKhachHang;
import lam.fpoly.shopthoitrang.R;


public class ThongTinFragment extends Fragment {

    TextView user_ten,user_tendangnhap,user_sdt,user_diachi;
    LinearLayout user_edit;
    TbKhachHangDao khDao;
    TbKhachHang kh;
    Context context;
    int id;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thong_tin, container, false);
        user_ten = view.findViewById(R.id.user_ten);
        user_tendangnhap = view.findViewById(R.id.user_tendangnhap);
        user_sdt = view.findViewById(R.id.user_sdt);
        user_diachi = view.findViewById(R.id.user_diachi);
        user_edit = view.findViewById(R.id.user_edit);

        khDao = new TbKhachHangDao();
        context = getActivity();
        id = DangNhapActivity.ID;
        setData();
        user_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        return view;
    }
    private void openDialog(){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them);

        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        dialog.setCancelable(true);

        EditText user_ten_edit,user_sdt_edit,user_diachi_edit,user_dangnhap_edit;
        Button user_btnok,user_btnhuy;
        user_ten_edit = dialog.findViewById(R.id.user_ten_edit);
        user_sdt_edit = dialog.findViewById(R.id.user_sdt_edit);
        user_diachi_edit = dialog.findViewById(R.id.user_diachi_edit);
        user_dangnhap_edit = dialog.findViewById(R.id.user_dangnhap_edit);
        user_btnok = dialog.findViewById(R.id.user_btnok);
        user_btnhuy = dialog.findViewById(R.id.user_btnhuy);

        user_ten_edit.setText(kh.getTen_khachHang());
        user_sdt_edit.setText(kh.getSdt_khachHang());
        user_diachi_edit.setText(kh.getDiaChi());
        user_dangnhap_edit.setText(kh.getUserName());
        user_btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kh.setTen_khachHang(user_ten_edit.getText().toString());
                kh.setSdt_khachHang(user_sdt_edit.getText().toString());
                kh.setDiaChi(user_diachi_edit.getText().toString());
                kh.setUserName(user_dangnhap_edit.getText().toString());
                khDao.updateRow(kh);
                setData();
                dialog.dismiss();
            }
        });

        user_btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setData(){
        try {
            TbKhachHangDao spDao = new TbKhachHangDao();
            kh = spDao.getUser(id);
            user_ten.setText(kh.getTen_khachHang());
            user_tendangnhap.setText(kh.getUserName());
            user_sdt.setText(kh.getSdt_khachHang());
            user_diachi.setText(kh.getDiaChi());
        }catch (Exception e){
        }
    }

}