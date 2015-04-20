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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

public class MainActivity extends ActionBarActivity {

    private Button b1;
    private LocationManager locationManager;

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
                    JSONObject locationHelp = new JSONObject();
                    try {
                        int tempuid=123456;
                        locationHelp.put("uid",tempuid);
                        locationHelp.put("type", "help");
                        locationHelp.put("latitude",latitude);
                        locationHelp.put("longitude",longitude);

                        HttpClient client = new DefaultHttpClient();
                        HttpPost post = new HttpPost("https://www.google.com");
                        post.setHeader("Content-type", "application/json");

                        StringEntity se = new StringEntity(locationHelp.toString());
                        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                        post.setEntity(se);

                        HttpResponse response = client.execute(post);
                        String responseText = EntityUtils.toString(response.getEntity());
                        JSONObject json = new JSONObject(responseText);
//                        Log.i("tag", temp);
                        // can receive output of this here......

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (ClientProtocolException e){
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
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
