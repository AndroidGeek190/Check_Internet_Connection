package com.example.check_internet_connection;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/*This class is used for check internet connection */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        MyReceiver.ConnectivityReceiverListener {
    /*Views declaration*/
    Button btnExplicitBroadcast;
    private boolean isConnected;
    private LinearLayout linearNoConnectionAvailable,linearConnectionAvailable;
    private Button errorPageRetry;
    /*My Receiver declaration*/
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myReceiver = new MyReceiver();
        checkConnection();
    }
    /*initial */
    private void initView() {
        linearNoConnectionAvailable = findViewById(R.id.linear_no_connection_available);
        errorPageRetry = findViewById(R.id.error_page_retry);
        linearConnectionAvailable = findViewById(R.id.linear_connection_available);
        errorPageRetry.setOnClickListener(this);
    }

    /*method for check connection*/
    private void checkConnection() {
        isConnected = MyReceiver.isConnected();
        if (isConnected) {
            linearConnectionAvailable.setVisibility(View.VISIBLE);
            linearNoConnectionAvailable.setVisibility(View.GONE);
            Toast.makeText(this, "WiFi/Mobile Networks Connected!", Toast.LENGTH_SHORT).show();
        } else {
            linearConnectionAvailable.setVisibility(View.GONE);
            linearNoConnectionAvailable.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Ooops! No WiFi/Mobile Networks Connected!", Toast.LENGTH_SHORT).show();

        }
    }

    /*on Resume Method*/
    @Override
    protected void onResume() {
        super.onResume();
        /*for registering to broadcast receiver*/
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, filter);
        AppController.getInstance().setConnectivityListener(this);
    }

    /*on stop method  to unregister the broadcast Receiver*/
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.error_page_retry:
                checkConnection();
                break;
        }
    }

    /*Override method of MyReciver Broadcast reciver*/
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        checkConnection();
    }



}