package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


import lam.fpoly.shopthoitrang.AccFragment.HoaDonChiTiet;
import lam.fpoly.shopthoitrang.Model.TbHoaDon;
import lam.fpoly.shopthoitrang.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>{
    private List<TbHoaDon> list;

    public void setData(List<TbHoaDon> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadon_item,parent,false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        int i = position;
        TbHoaDon obj = list.get(i);
        if (obj == null){
            return;
        }
        holder.hd_ten_item.setText(obj.getTensp());
        holder.hd_soluong_item.setText(""+obj.getSoluong());
        holder.hd_gia_item.setText(obj.getGia()+".000đ");
        holder.hd_trangthai_item.setText(obj.getTrangthai());
        Picasso.get().load(obj.getAnh()).fit().into(holder.hd_anh_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, HoaDonChiTiet.class);
                intent.putExtra("id",obj.getId());
                intent.putExtra("ten_sp",obj.getTensp());
                intent.putExtra("gia_sp",obj.getGia());
                intent.putExtra("soluong_sp",obj.getSoluong());
                intent.putExtra("trangthai_sp",obj.getTrangthai());
                intent.putExtra("anh_sp",obj.getAnh());
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder{
        TextView hd_ten_item,hd_soluong_item,hd_gia_item,hd_trangthai_item;
        ImageView hd_anh_item;
        Context context;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            hd_anh_item = itemView.findViewById(R.id.hd_anh_item);
            hd_ten_item = itemView.findViewById(R.id.hd_ten_item);
            hd_soluong_item = itemView.findViewById(R.id.hd_soluong_item);
            hd_gia_item = itemView.findViewById(R.id.hd_gia_item);
            hd_trangthai_item = itemView.findViewById(R.id.hd_trangthai_item);
            context = itemView.getContext();
        }
    }
}
