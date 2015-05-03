package com.example.purva.helpmeout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends ActionBarActivity {
    connectNetwork connect;
    private Button bt1;
    private EditText et1;
    private EditText et2;
    private String userid;
    private int flag;
    private String pswd;
    private Login curActivity;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        flag=0;id=6;
       // sharedpreferences = getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedpreferences.edit();
        curActivity = this;
        et1 = (EditText) findViewById(R.id.login);
        et1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    userid = et1.getText().toString();
                    flag = 1;
                }
                return true;
            }
        });
        et2 = (EditText) findViewById(R.id.Password);
        et2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT && flag == 1) {
                    pswd = et2.getText().toString();
                }
                return true;
            }
        });

        bt1 = (Button) findViewById(R.id.logb);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject logObj = new JSONObject();
                try {
                    logObj.put("type","login");
                    logObj.put("uid", et1.getText().toString());
                    logObj.put("password", et2.getText().toString());
                    logObj.put("id",-1);
                    logObj.put("latitude",65535);
                    logObj.put("longitude",65535);
                    et1.setText("");
                    et2.setText("");
                    connect = new connectNetwork(curActivity);
                    connect.execute(logObj.toString());
                    System.out.println(logObj.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    void print(String login)
    {
        System.out.println("\n\n\n********fdgfdddd*****"+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n\n");
        Toast.makeText(getApplicationContext(),login,Toast.LENGTH_SHORT).show();
        try {
            JSONObject loginV = new JSONObject(login);
            System.out.print("\n\n\n*************"+loginV.toString()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n\n");
            //t1.setText(ackUID.getString("ack"));
            if(loginV.getInt("success")==1)
            {
                editor.putString("uid",userid);
                editor.putString("password",pswd);
                editor.putInt("id",id);
                editor.putInt("type",loginV.getInt("type"));
                editor.commit();
                Intent i2 = new Intent(Login.this,MainActivity.class);
                startActivity(i2);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No entries found corresponding to this combination. Please try again!!!",Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"error in receiving json object",Toast.LENGTH_SHORT).show();
        }

    }
}
