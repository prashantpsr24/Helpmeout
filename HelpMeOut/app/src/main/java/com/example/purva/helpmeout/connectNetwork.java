package com.example.purva.helpmeout;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class connectNetwork extends AsyncTask<String, Void, String> {
    /**
     * The system calls this to perform work in a worker thread and
     * delivers it the parameters given to AsyncTask.execute()
     */
//    public AsyncResponse delegate=null;
    MainActivity activity;
    Login NActivity;
    int flag;
    public connectNetwork(MainActivity act)
    {
        flag=0;
        this.activity=act;
    }
    public connectNetwork(){}
    public connectNetwork(Login act)
    {
        this.NActivity = act;
        flag=1;
    }

    protected String doInBackground(String... dataA) {
        DataInputStream in = null;
        StringBuffer sb = null;
        String serverName = "10.42.0.1";
        int port = 6069;
        try {
            System.out.println("Connecting to " + serverName
                    + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out =
                    new DataOutputStream(outToServer);

            out.writeUTF(dataA[0]);
            InputStream inFromServer = client.getInputStream();
            in =
                    new DataInputStream(inFromServer);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            sb = new StringBuffer();
            String s="";
            while((s=in1.readLine())!=null)
            {
                sb.append(s);
            }
            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    protected void onPostExecute(String result)
    {
        //delegate.processFinish(result);
        //result1=result;
        if(flag==0)
            activity.print(result);
        else if(flag==1)
        {
            NActivity.print(result);
        }
    }

}
