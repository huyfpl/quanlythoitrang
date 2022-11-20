package lam.fpoly.shopthoitrang.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lam.fpoly.shopthoitrang.AccFragment.DangKyActivity;
import lam.fpoly.shopthoitrang.AccFragment.DangNhapActivity;
import lam.fpoly.shopthoitrang.AccFragment.ThongTinFragment;
import lam.fpoly.shopthoitrang.MainActivity;
import lam.fpoly.shopthoitrang.R;


public class Acc_Fragment extends Fragment {
    Button btnDangNhap, btnDangKy;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acc, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        context = view.getContext();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DangKyActivity.class);
                startActivity(intent);
            }
        });
        Log.i("TAG", "onViewCreated: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (DangNhapActivity.checkLogin){
            replaceFragment(new ThongTinFragment());
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(MainActivity.frame,fragment);
        transaction.commit();
    }
}