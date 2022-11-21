package lam.fpoly.shopthoitrang.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.shopthoitrang.Dao.DanhMucDAO;
import lam.fpoly.shopthoitrang.Dao.SanPhamDAO;
import lam.fpoly.shopthoitrang.Model.TbDanhMuc;
import lam.fpoly.shopthoitrang.Model.TbSanPham;


@Database(entities = {TbDanhMuc.class} , version = 1)
public abstract class MyDataBase_DM extends RoomDatabase {
    public static final String NAME_DATABASE = "danhmuc.db";
    public static MyDataBase_DM instance;

    public static synchronized MyDataBase_DM getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase_DM.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract DanhMucDAO danhMucDAO();
}
