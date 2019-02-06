package com.example.check_internet_connection;

import android.app.Application;

public class AppController extends Application {
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(MyReceiver.ConnectivityReceiverListener listener) {
        MyReceiver.connectivityReceiverListener = listener;
    }
}
