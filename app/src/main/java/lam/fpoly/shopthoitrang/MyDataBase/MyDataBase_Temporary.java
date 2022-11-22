package lam.fpoly.shopthoitrang.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.shopthoitrang.Dao.DonHangDAO;
import lam.fpoly.shopthoitrang.Object.DonHang_Temorary;


@Database(entities = {DonHang_Temorary.class} , version = 1)
public abstract class MyDataBase_Temporary extends RoomDatabase {
    public static final String NAME_DATABASE = "donhang.db";
    public static MyDataBase_Temporary instance;

    public static synchronized MyDataBase_Temporary getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase_Temporary.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract DonHangDAO donHangDAO();
}
