# Check_Internet_Connection
Check Internet connection using broadcast receiver in all Android OS till Oreo(8.1)

Step 1:
Create a class which extends BroadcastReceiver.
 In My Case, I'm creating MyReceiver class which extends BroadcastReceiver,Please check it.
 
Step 2:
Create AppController class.

Step 3:
Register Broadcast receiver in AndroidManifest.xml

Step 4:
 For above OS than Marshmallow(6.0) register receiver in Activity in onResume() method:
 
    @Override
    protected void onResume() {
        super.onResume();
        /*for registering to broadcast receiver*/
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, filter);
        AppController.getInstance().setConnectivityListener(this);
    }
 
