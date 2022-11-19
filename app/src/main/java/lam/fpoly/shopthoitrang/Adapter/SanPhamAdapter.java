package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> implements Filterable {
    private List<TbSanPham> list;
    private Context mtx;
    private List<TbSanPham> listfull;
    public int filtersoluong;

    public SanPhamAdapter(Context context, List<TbSanPham> mlist) {
        this.mtx = context;
        this.list = mlist;
        this.listfull = new ArrayList<>(list);

    }

    public void setfilterlist(List<TbSanPham> filterlisst) {
        this.list = filterlisst;
        notifyDataSetChanged();
    }
//    public void setData(List<TbSanPham> mlist) {
//        this.list = mlist;
//        this.listfull = new ArrayList<>();
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanpham_item, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        int i = position;
        TbSanPham obj = list.get(i);
        if (obj == null) {
            return;
        }
        holder.sp_ten_item.setText("Tên sản phẩm: " + obj.getTen_sanPham());
        holder.sp_gianhap_item.setText("Giá nhập: " + obj.getGiaNhap());
        holder.sp_giaban_item.setText("Giá bán: " + obj.getGiaBan());
        holder.sp_tonkho_item.setText("Số lượng trong kho: " + obj.getTonKho());
        Picasso.get().load(obj.getSrcAnh()).fit().into(holder.sp_anh_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "huy", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardViewanimation.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.list_animation));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // tìm sản phẩm
    @Override
    public Filter getFilter() {
        return FilterTensp;
    }

    private Filter FilterTensp = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchTensp = constraint.toString().toLowerCase();
            List<TbSanPham> templist = new ArrayList<>();
            if (searchTensp.length() == 0 || searchTensp.isEmpty()) {
                templist.addAll(listfull);

            } else {
                for (TbSanPham item : listfull
                ) {
                    if (item.getTen_sanPham().toLowerCase().contains(searchTensp)) {
                        templist.add(item);


                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = templist;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends TbSanPham>) results.values);
            notifyDataSetChanged();
        }
    };

    ////
    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView sp_ten_item, sp_giaban_item, sp_gianhap_item, sp_tonkho_item;
        ImageView sp_anh_item;
        CardView cardViewanimation;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            sp_ten_item = itemView.findViewById(R.id.sp_ten_item);
            sp_giaban_item = itemView.findViewById(R.id.sp_giaban_item);
            sp_gianhap_item = itemView.findViewById(R.id.sp_gianhap_item);
            sp_tonkho_item = itemView.findViewById(R.id.sp_tonkho_item);
            sp_anh_item = itemView.findViewById(R.id.sp_anh_item);
            cardViewanimation = itemView.findViewById(R.id.cardview);
        }
    }
}
