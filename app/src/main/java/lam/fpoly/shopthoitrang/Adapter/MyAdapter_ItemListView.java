package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.ActivitySanPham.Activity_ThongTinSP;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.R;

public class MyAdapter_ItemListView extends BaseAdapter {
    private Context context;
    private List<TbHoaDonChiTiet> list = new ArrayList<>();

    public MyAdapter_ItemListView(Context context, List<TbHoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IconViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layoutitem_donmua,null);
            viewHolder.tvItemName = convertView.findViewById(R.id.tvItemName);
            viewHolder.tvItemPrice = convertView.findViewById(R.id.tvItemPrice);
            viewHolder.tvItemAmount = convertView.findViewById(R.id.tvItemAmount);
            viewHolder.imgItemDonMua = convertView.findViewById(R.id.imgItemDonMua);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (IconViewHolder) convertView.getTag();
        }

        TbSanPhamDao sanPhamDao = new TbSanPhamDao();

        viewHolder.tvItemName.setText(sanPhamDao.getSpID(list.get(position).getId_SanPham()).getTen_sanPham());
        viewHolder.tvItemPrice.setText(String.valueOf(sanPhamDao.getSpID(list.get(position).getId_SanPham()).getGiaBan()));
        viewHolder.tvItemAmount.setText(String.valueOf(list.get(position).getSoLuong()));
        Picasso.get().load(sanPhamDao.getSpID(list.get(position).getId_SanPham()).getSrcAnh()).fit().into(viewHolder.imgItemDonMua);

        return convertView;
    }

    public static class IconViewHolder{
        private TextView tvItemName,tvItemPrice,tvItemAmount;
        private ImageView imgItemDonMua;
    }

}
