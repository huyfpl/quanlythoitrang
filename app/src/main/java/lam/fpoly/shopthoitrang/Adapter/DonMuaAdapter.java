package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.Dao.TbHoaDonChiTietDao;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbDonHang;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;
import lam.fpoly.shopthoitrang.R;


public class DonMuaAdapter extends RecyclerView.Adapter<DonMuaAdapter.GioHangViewHolder>{
    private List<TbDonHang> list;
    private MyAdapter_ItemListView myAdapter_itemListView;
    private Context context;

    public void setData(List<TbDonHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    private InterClickItemData interClickItemData;

    public interface InterClickItemData {
        void clickitem(TbDonHang tbDonHang);
    }

    public DonMuaAdapter(Context context, InterClickItemData interClickItemData) {
        this.context = context;
        this.interClickItemData = interClickItemData;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem_baodonmua,parent,false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int i = position;
        TbDonHang obj = list.get(position);
        if (obj == null){
            return;
        }

        TbHoaDonChiTietDao tbHoaDonChiTietDao = new TbHoaDonChiTietDao();
        System.out.println(list.get(position).getId_DonHang());

        myAdapter_itemListView = new MyAdapter_ItemListView(holder.context, tbHoaDonChiTietDao.getAll(list.get(position).getId_DonHang()));
        holder.idItemListView.setAdapter(myAdapter_itemListView);

        holder.tvItemBuyDate.setText(obj.getNgayMua());
        holder.tvItemTotal.setText(String.valueOf(obj.getTongTien()));
        holder.tvItemButton.setText(obj.getTrangThai());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interClickItemData.clickitem(obj);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemBuyDate,tvItemTotal,tvItemButton;
        ListView idItemListView;
        Context context;
        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemBuyDate = itemView.findViewById(R.id.tvItemBuyDate);
            tvItemTotal = itemView.findViewById(R.id.tvItemTotal);
            tvItemButton = itemView.findViewById(R.id.tvItemButton);
            idItemListView = itemView.findViewById(R.id.idItemListView);
            context = itemView.getContext();
        }
    }
}
