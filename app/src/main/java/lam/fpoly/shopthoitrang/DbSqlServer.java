package lam.fpoly.shopthoitrang;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbSqlServer {

    Connection connection;
    public Connection openConnect(){
        String ip = "103.179.188.76", port = "1433", user = "CP17304_n4", pass = "sieunhangao456", db = "CP17304_n4";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db +";user=" + user +";password=" + pass +";";
            this.connection = DriverManager.getConnection(connectUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
