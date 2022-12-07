package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.ActivitySanPham.Activity_ThongTinSP;
import lam.fpoly.shopthoitrang.Dao.TbSaleSPDao;
import lam.fpoly.shopthoitrang.Model.TbSaleSP;
import lam.fpoly.shopthoitrang.Model.TbSanPham;
import lam.fpoly.shopthoitrang.R;

public class MyAdapter_GirdView extends BaseAdapter implements Filterable {
    private Context context;
    private List<TbSanPham> list = new ArrayList<>();
    private int layout;

    public MyAdapter_GirdView(Context context, List<TbSanPham> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        if (convertView == null) {
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.itemImg = convertView.findViewById(R.id.item_Img);
            viewHolder.tvSpName = convertView.findViewById(R.id.tvSpName);
            viewHolder.tvSpPrice = convertView.findViewById(R.id.tvSpPrice);
            viewHolder.tvSale = convertView.findViewById(R.id.tv_sale);
            viewHolder.tvSpPrice_sale = convertView.findViewById(R.id.tvSpPrice_sale);
            viewHolder.layout_sale = convertView.findViewById(R.id.layout_sale);
            viewHolder.layout_item = convertView.findViewById(R.id.layout_item);
            viewHolder.layout_sale.setVisibility(View.INVISIBLE);
            Picasso.get().load(list.get(position).getSrcAnh()).fit().into(viewHolder.itemImg);
            viewHolder.tvSpName.setText(list.get(position).getTen_sanPham());
            viewHolder.tvSpPrice.setText(list.get(position).getGiaBan() + ".000đ");
            //sale
            TbSaleSPDao tbSaleSPDao = new TbSaleSPDao();
            List<TbSaleSP> listSale = tbSaleSPDao.getAll();
            for (TbSaleSP sp : listSale) {
                if (sp.getId_sanpham() == list.get(position).getId_sanPham()) {
                    viewHolder.layout_sale.setVisibility(View.VISIBLE);
                    viewHolder.tvSale.setText(sp.getSale() + "%");
                    viewHolder.tvSpPrice.setText(list.get(position).getGiaBan() * (100 - sp.getSale()) / 100 + ".000đ");
                    viewHolder.tvSpPrice_sale.setText(list.get(position).getGiaBan() + ".000đ");
                    viewHolder.tvSpPrice_sale.setPaintFlags(viewHolder.tvSpPrice_sale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                }
            }

            viewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TbSaleSPDao tbSaleSPDao = new TbSaleSPDao();
                    List<TbSaleSP> listSale = tbSaleSPDao.getAll();
                    for (TbSaleSP sp : listSale) {
                        if (sp.getId_sanpham() == list.get(position).getId_sanPham()) {
                            Activity_ThongTinSP.checkSale = true;
                            break;
                        } else {
                            Activity_ThongTinSP.checkSale = false;
                        }
                    }
                    Intent intent = new Intent(context, Activity_ThongTinSP.class);
                    intent.putExtra("SP_VALUES", list.get(position).getId_sanPham());
                    context.startActivity(intent);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IconViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class IconViewHolder {
        private TextView tvSpName, tvSpPrice, tvSale, tvSpPrice_sale;
        private ImageView itemImg;
        private LinearLayout layout_sale;
        private CardView layout_item;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
