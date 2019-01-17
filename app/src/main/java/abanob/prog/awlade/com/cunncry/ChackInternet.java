package abanob.prog.awlade.com.cunncry;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by egypt on 8/10/2017.
 */

public class ChackInternet {
    private Context context;
    public ChackInternet(Context context)
    {
        this.context=context;
    }
    public  boolean IsConnected(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null)
        {
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo !=null&&networkInfo.isConnected())
            {
                return  true;
            }
        }
        return false;
    }
}
