package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
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
        DonHang_Temorary obj = list.get(position);
        if (obj == null){
            return;
        }
        TbSanPhamDao sanPhamDao = new TbSanPhamDao();

        TbSanPham tbSanPham = sanPhamDao.getSpID(obj.getId_sanPham());
        if (tbSanPham == null){
            return;
        }

        number = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getSoLuong(i);

        holder.tvName_SanPham.setText(tbSanPham.getTen_sanPham());
        holder.tvGia_SanPham.setText(String.valueOf(tbSanPham.getGiaBan()));
        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(holder.imgSanPham);

        holder.imgItemGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 1) {
                    number--;
                    holder.tvSoLuongItem.setText(String.valueOf(number));
                    DonHang_Temorary donHang_temorary = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getObjectData(i);
                    MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number, donHang_temorary.getId());
                    interClickItemData.clickDown();
                }
            }
        });

        holder.tvSoLuongItem.setText(String.valueOf(obj.getSoLuong()));

        holder.imgItemTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                holder.tvSoLuongItem.setText(String.valueOf(number));
                DonHang_Temorary donHang_temorary = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getObjectData(i);
                MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number, donHang_temorary.getId());
                interClickItemData.clickUp();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
