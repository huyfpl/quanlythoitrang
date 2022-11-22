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
        if(convertView == null){
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.itemImg = convertView.findViewById(R.id.item_Img);
            viewHolder.tvSpName = convertView.findViewById(R.id.tvSpName);
            viewHolder.tvSpPrice = convertView.findViewById(R.id.tvSpPrice);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (IconViewHolder) convertView.getTag();
        }
        viewHolder.tvSpName.setText(list.get(position).getTen_sanPham());
        viewHolder.tvSpPrice.setText(String.valueOf(list.get(position).getGiaBan()));
        Picasso.get().load(list.get(position).getSrcAnh()).fit().into(viewHolder.itemImg);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_ThongTinSP.class);
                intent.putExtra("SP_VALUES",list.get(position).getId_sanPham());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public static class IconViewHolder{
        private TextView tvSpName,tvSpPrice;
        private ImageView itemImg;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

}
