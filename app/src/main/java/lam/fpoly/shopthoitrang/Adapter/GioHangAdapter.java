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
import lam.fpoly.shopthoitrang.Fragment.GioHang_Fragment;
import lam.fpoly.shopthoitrang.Model.TbGioHang;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.MyDataBase.MyDataBase_Temporary;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;
import lam.fpoly.shopthoitrang.R;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder>{
    private List<TbGioHang> list;
    private List<TbSanPham> listSP;
    int number = 1;
    public void setData(List<TbGioHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    private InterClickItemData interClickItemData;

    public interface InterClickItemData {
        void clickCheck(TbSanPham tbSanPham,int position,TbGioHang tbGioHang,boolean isCheck);
        void clickUp();
        void clickDown();
    }

    public GioHangAdapter(InterClickItemData interClickItemData) {
        this.interClickItemData = interClickItemData;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem_giohang,parent,false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int i = position;
        TbGioHang obj = list.get(position);
        if (obj == null){
            return;
        }


        TbSanPhamDao sanPhamDao = new TbSanPhamDao();
        TbGioHangDao tbGioHangDao = new TbGioHangDao();

        TbSanPham tbSanPham = sanPhamDao.getSpID(obj.getIdSanPham());
        if (tbSanPham == null){
            return;
        }
        if (GioHang_Fragment.checkAll){
            holder.imgItemCheck.setChecked(true);
        }else {
            holder.imgItemCheck.setChecked(false);
        }
        holder.idLayoutTangGiam.setVisibility(View.VISIBLE);
       // interClickItemData.clickCheck(tbSanPham,i,obj,checkall);


        holder.tvName_SanPham.setText(tbSanPham.getTen_sanPham());
        holder.tvGia_SanPham.setText(tbSanPham.getGiaBan()+".000đ");
        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(holder.imgSanPham);

        holder.imgItemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                interClickItemData.clickCheck(tbSanPham,i,obj,isChecked);
                if (isChecked){
                    holder.idLayoutTangGiam.setVisibility(View.VISIBLE);
                    Log.d("ccc", "onCheckedChanged: "+holder.imgItemCheck.isChecked());
                }else {
                    holder.idLayoutTangGiam.setVisibility(View.GONE);
                }
            }
        });
        holder.imgItemGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 1) {
                    number = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getSoLuong(i);
                    number--;
                    holder.tvSoLuongItem.setText(String.valueOf(number));
                    DonHang_Temorary donHang_temorary = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getObjectData(i);
                    MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number, donHang_temorary.getId());
                    tbGioHangDao.updateRow(obj);
                    interClickItemData.clickDown();
                }
            }
        });

        holder.tvSoLuongItem.setText(String.valueOf(obj.getSoLuongSP()));

        holder.imgItemTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getSoLuong(i);
                number++;
                holder.tvSoLuongItem.setText(String.valueOf(number));
                DonHang_Temorary donHang_temorary = MyDataBase_Temporary.getInstance(holder.context).donHangDAO().getObjectData(i);
                MyDataBase_Temporary.getInstance(holder.context).donHangDAO().update(number, donHang_temorary.getId());
                tbGioHangDao.updateRow(obj);
                interClickItemData.clickUp();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static void checkAll(){
      //  checkall=true;

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
        CheckBox imgItemCheck;
        Context context;
        LinearLayout idLayoutTangGiam;
        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName_SanPham = itemView.findViewById(R.id.tvItemNameSP);
            tvGia_SanPham = itemView.findViewById(R.id.tvItemPriceSP);
            imgSanPham = itemView.findViewById(R.id.imgItemSP);
            imgItemCheck = itemView.findViewById(R.id.imgItemCheckBox);
            imgItemGiam = itemView.findViewById(R.id.imgItemGiam);
            imgItemTang = itemView.findViewById(R.id.imgItemTang);
            tvSoLuongItem = itemView.findViewById(R.id.tvItemSoLuong);
            idLayoutTangGiam = itemView.findViewById(R.id.idLayoutTangGiam);
            context = itemView.getContext();
        }
    }

}
