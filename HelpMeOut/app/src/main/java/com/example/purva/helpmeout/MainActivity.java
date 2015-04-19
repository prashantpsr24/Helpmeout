package com.example.purva.helpmeout;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.purva.helpmeout.gpstracking.GPSTracker;
//public class GPSTracker extends Service implements LocationListener {
//
//    private final Context mContext;
//
//    // flag for GPS status
//    boolean isGPSEnabled = false;
//
//    // flag for network status
//    boolean isNetworkEnabled = false;
//
//    // flag for GPS status
//    boolean canGetLocation = false;
//
//    Location location; // location
//    double latitude; // latitude
//    double longitude; // longitude
//
//    // The minimum distance to change Updates in meters
//    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
//
//    // The minimum time between updates in milliseconds
//    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
//
//    // Declaring a Location Manager
//    protected LocationManager locationManager;
//
//    public GPSTracker(Context context) {
//        this.mContext = context;
//        getLocation();
//    }
//
//    public Location getLocation() {
//        try {
//            locationManager = (LocationManager) mContext
//                    .getSystemService(LOCATION_SERVICE);
//
//            // getting GPS status
//            isGPSEnabled = locationManager
//                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//            // getting network status
//            isNetworkEnabled = locationManager
//                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//            if (!isGPSEnabled && !isNetworkEnabled) {
//                // no network provider is enabled
//            } else {
//                this.canGetLocation = true;
//                // First get location from Network Provider
//                if (isNetworkEnabled) {
//                    locationManager.requestLocationUpdates(
//                            LocationManager.NETWORK_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    Log.d("Network", "Network");
//                    if (locationManager != null) {
//                        location = locationManager
//                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                        if (location != null) {
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
//                        }
//                    }
//                }
//                // if GPS Enabled get lat/long using GPS Services
//                if (isGPSEnabled) {
//                    if (location == null) {
//                        locationManager.requestLocationUpdates(
//                                LocationManager.GPS_PROVIDER,
//                                MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                        Log.d("GPS Enabled", "GPS Enabled");
//                        if (locationManager != null) {
//                            location = locationManager
//                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            if (location != null) {
//                                latitude = location.getLatitude();
//                                longitude = location.getLongitude();
//                            }
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return location;
//    }
//
//    /**
//     * Stop using GPS listener
//     * Calling this function will stop using GPS in your app
//     * */
//    public void stopUsingGPS(){
//        if(locationManager != null){
//            locationManager.removeUpdates(GPSTracker.this);
//        }
//    }
//
//    /**
//     * Function to get latitude
//     * */
//    public double getLatitude(){
//        if(location != null){
//            latitude = location.getLatitude();
//        }
//
//        // return latitude
//        return latitude;
//    }
//
//    /**
//     * Function to get longitude
//     * */
//    public double getLongitude(){
//        if(location != null){
//            longitude = location.getLongitude();
//        }
//
//        // return longitude
//        return longitude;
//    }
//
//    /**
//     * Function to check GPS/wifi enabled
//     * @return boolean
//     * */
//    public boolean canGetLocation() {
//        return this.canGetLocation;
//    }
//
//    /**
//     * Function to show settings alert dialog
//     * On pressing Settings button will lauch Settings Options
//     * */
//    public void showSettingsAlert(){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//
//        // Setting Dialog Title
//        alertDialog.setTitle("GPS is settings");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
//
//        // On pressing Settings button
//        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                mContext.startActivity(intent);
//            }
//        });
//
//        // on pressing cancel button
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//    }
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//}
public class MainActivity extends ActionBarActivity {

    private Button b1;
    private LocationManager locationManager;
    private LocationManager locManager;
    //private LocationListener locListener = new MyLocationListener();
    private GPSTracker gps;

    {
        gps = new GPSTracker(this);
    }
    //private LocationListener locListener = new MyLocationListener();

    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.help_button);
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        b1.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Toast.makeText(getApplicationContext(), "Your toast message.",
                                Toast.LENGTH_SHORT).show();
//
                        return true;
                    }
                }
        );
        b1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                return true;
            }
        });

    }


}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


//    @Override
//    public void onClick(View v) {
//        //progress.setVisibility(View.VISIBLE);
//// exceptions will be thrown if provider is not permitted.
//        try {
//            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        } catch (Exception ex) {
//        }
//        try {
//            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception ex) {
//        }
//
//// don't start listeners if no provider is enabled
//        if (!gps_enabled || !network_enabled) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Attention!");
//            builder.setMessage("Sorry, location is not determined. Please enable location providers");
//            builder.setPositiveButton("OK", this);
//            builder.setNeutralButton("Cancel", this);
//            builder.create().show();
//            //progress.setVisibility(View.GONE);
//        }
//
//        if (gps_enabled) {
//            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
//        }
//        if (network_enabled) {
//            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
//        }
//    }
//    class MyLocationListener implements LocationListener {
//        @Override
//        public void onLocationChanged(Location location) {
//            if (location != null) {
//// This needs to stop getting the location data and save the battery power.
//                locManager.removeUpdates(locListener);
//
//                String longitude = "Londitude: " + location.getLongitude();
//                String latitude = "Latitude: " + location.getLatitude();
//                String altitude = "Altitiude: " + location.getAltitude();
//                String accuracy = "Accuracy: " + location.getAccuracy();
//                String time = "Time: " + location.getTime();
//                //send
//                Toast.makeText(getApplicationContext(), "data received.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//// TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//// TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//// TODO Auto-generated method stub
//
//        }
//    }
//
//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        if(which == DialogInterface.BUTTON_NEUTRAL){
//           Toast.makeText(getApplicationContext(), "Sorry, location is not determined. To fix this please enable location providers.",
//                   Toast.LENGTH_SHORT).show();
//            //setText("Sorry, location is not determined. To fix this please enable location providers");
//        }else if (which == DialogInterface.BUTTON_POSITIVE) {
//            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//        }
//    }
//}
