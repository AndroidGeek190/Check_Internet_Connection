package com.example.check_internet_connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/*This class is MyReceiver Broadcast receiver */
public class MyReceiver extends BroadcastReceiver {
static Context mcontext;
    public static ConnectivityReceiverListener connectivityReceiverListener;

    public MyReceiver() {
        super();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext=context;

        /*get action name from activity which is trigger the broadcast receiver*/
        String action = intent.getAction();

        /*check action name*/
        if (("android.net.conn.CONNECTIVITY_CHANGE").equals(action)) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
            Log.e("receiver","on");
            if (connectivityReceiverListener != null) {
                connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
            }

        }

    }



    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) AppController.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
