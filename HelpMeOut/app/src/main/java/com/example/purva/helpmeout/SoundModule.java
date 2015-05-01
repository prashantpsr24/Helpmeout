package com.example.purva.helpmeout; /**
 * Created by panther on 2/5/15.
 */
import java.net.*;
import java.io.*;

public class SoundModule extends Thread {


    public void run() {
        String serverName = "10.42.0.1";
        int port = 6070;
        try {
            System.out.println("Connecting to " + serverName
                    + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out =
                    new DataOutputStream(outToServer);

            out.writeUTF("app says buzz");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in =
                    new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

