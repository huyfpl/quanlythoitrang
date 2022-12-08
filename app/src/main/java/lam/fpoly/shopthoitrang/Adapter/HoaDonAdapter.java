package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbGioHangDao;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;
import lam.fpoly.shopthoitrang.R;


public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.GioHangViewHolder>{
    private List<DonHang_Temorary> list;
    int number;

    public void setData(List<DonHang_Temorary> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    private InterClickItemData interClickItemData;

    public interface InterClickItemData {
        void clickUp();
        void clickDown();
    }

    public HoaDonAdapter(InterClickItemData interClickItemData) {
        this.interClickItemData = interClickItemData;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem_hoadon,parent,false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int i = position;
        DonHang_Temorary objj = list.get(position);
        if (objj == null){
            return;
        }
        List<DonHang_Temorary> temorary = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getListData();
        if (temorary.size()==0){
            MyDataBase_Temporary.getInstance(holder.context).donHangDAO().insertData(objj);
        }
        DonHang_Temorary obj = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getObjectData(objj.getId_sanPham());

        number = obj.getSoLuong();
        holder.tvName_SanPham.setText(obj.getTen_sanPham());
        holder.tvGia_SanPham.setText(obj.getGia_sanPham()+".000đ");
        Picasso.get().load(obj.getAnh_sanPham()).fit().into(holder.imgSanPham);

        holder.imgItemGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 1) {
                    number--;
                    holder.tvSoLuongItem.setText(String.valueOf(number));
                    MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number,obj.getId_sanPham());
                    interClickItemData.clickDown();
                }
            }
        });

        holder.tvSoLuongItem.setText(String.valueOf(number));

        holder.imgItemTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                holder.tvSoLuongItem.setText(String.valueOf(number));
                MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number,obj.getId_sanPham());
                interClickItemData.clickUp();
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
        TextView tvName_SanPham,tvGia_SanPham,tvSoLuongItem;
        ImageView imgSanPham,imgItemGiam,imgItemTang;
        Context context;
        LinearLayout idLayoutTangGiam;
        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName_SanPham = itemView.findViewById(R.id.tvItemNameSP);
            tvGia_SanPham = itemView.findViewById(R.id.tvItemPriceSP);
            imgSanPham = itemView.findViewById(R.id.imgItemSP);
            imgItemGiam = itemView.findViewById(R.id.imgItemGiam);
            imgItemTang = itemView.findViewById(R.id.imgItemTang);
            tvSoLuongItem = itemView.findViewById(R.id.tvItemSoLuong);
            idLayoutTangGiam = itemView.findViewById(R.id.idLayoutTangGiam);
            context = itemView.getContext();
        }
    }
}
