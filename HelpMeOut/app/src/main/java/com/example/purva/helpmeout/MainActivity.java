package com.example.purva.helpmeout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements LocationListener{

    private Button b1;
    private LocationManager locationManager;
    private LocationManager locManager;
    private LocationListener locListener = new MyLocationListener();

    //private LocationListener locListener = new MyLocationListener();

    private boolean gps_enabled = false;
    private boolean network_enabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1= (Button) findViewById(R.id.help_button);
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        b1.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Toast.makeText(getApplicationContext(), "Your toast message.",
                                Toast.LENGTH_SHORT).show();
//                        Intent intent1 = new Intent(MainActivity.this,MyLocationListener.class);
//                        startService(intent1);
                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        //progress.setVisibility(View.VISIBLE);
// exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

// don't start listeners if no provider is enabled
        if (!gps_enabled || !network_enabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Attention!");
            builder.setMessage("Sorry, location is not determined. Please enable location providers");
            builder.setPositiveButton("OK", this);
            builder.setNeutralButton("Cancel", this);
            builder.create().show();
            //progress.setVisibility(View.GONE);
        }

        if (gps_enabled) {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }
        if (network_enabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        }
    }
    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
// This needs to stop getting the location data and save the battery power.
                locManager.removeUpdates(locListener);

                String longitude = "Londitude: " + location.getLongitude();
                String latitude = "Latitude: " + location.getLatitude();
                String altitude = "Altitiude: " + location.getAltitude();
                String accuracy = "Accuracy: " + location.getAccuracy();
                String time = "Time: " + location.getTime();
                //send
                Toast.makeText(getApplicationContext(), "data received.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
// TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
// TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
// TODO Auto-generated method stub

        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_NEUTRAL){
           Toast.makeText(getApplicationContext(), "Sorry, location is not determined. To fix this please enable location providers.",
                   Toast.LENGTH_SHORT).show();
            //setText("Sorry, location is not determined. To fix this please enable location providers");
        }else if (which == DialogInterface.BUTTON_POSITIVE) {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }
}
