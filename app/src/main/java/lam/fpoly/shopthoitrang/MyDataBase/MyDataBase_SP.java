package lam.fpoly.shopthoitrang.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.shopthoitrang.Dao.SanPhamDAO;
import lam.fpoly.shopthoitrang.Model.TbSanPham;


@Database(entities = {TbSanPham.class} , version = 1)
public abstract class MyDataBase_SP extends RoomDatabase {
    public static final String NAME_DATABASE = "sanpham.db";
    public static MyDataBase_SP instance;

    public static synchronized MyDataBase_SP getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase_SP.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract SanPhamDAO sanPhamDAO();
}
