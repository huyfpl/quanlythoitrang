package lam.fpoly.shopthoitrang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.shopthoitrang.Dao.TbFeedBackDao;
import lam.fpoly.shopthoitrang.Dao.TbKhachHangDao;
import lam.fpoly.shopthoitrang.Dao.TbSanPhamDao;
import lam.fpoly.shopthoitrang.Model.TbFeedBack;
import lam.fpoly.shopthoitrang.Model.TbHoaDonChiTiet;
import lam.fpoly.shopthoitrang.R;

public class FeedBackAdapter extends BaseAdapter {
    private Context context;
    private List<TbFeedBack> list;

    public FeedBackAdapter(Context context, List<TbFeedBack> list) {
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
        int i = position;
        if(convertView == null){
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_feedback,null);
            viewHolder.fb_name = convertView.findViewById(R.id.fb_name);
            viewHolder.fb_star = convertView.findViewById(R.id.fb_star);
            viewHolder.fb_mess = convertView.findViewById(R.id.fb_mess);
            viewHolder.fb_avatar = convertView.findViewById(R.id.fb_avatar);
            viewHolder.fb_img = convertView.findViewById(R.id.fb_img);
            Log.i("TAG", "adapter: "+list.size());
            TbFeedBack obj = list.get(i);
            TbKhachHangDao tbKhachHangDao = new TbKhachHangDao();
            Picasso.get().load(tbKhachHangDao.getUser(obj.getId_khachhang()).getAvatar()).fit().into(viewHolder.fb_avatar);
            viewHolder.fb_name.setText(tbKhachHangDao.getUser(obj.getId_khachhang()).getTen_khachHang());
            viewHolder.fb_star.setText("Đánh giá: "+obj.getSoStar()+" sao");
            viewHolder.fb_mess.setText(obj.getMess());
            Picasso.get().load(obj.getAnh()).fit().into(viewHolder.fb_img);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (IconViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class IconViewHolder{
        private TextView fb_name,fb_star,fb_mess;
        private ImageView fb_avatar,fb_img;
    }

}
