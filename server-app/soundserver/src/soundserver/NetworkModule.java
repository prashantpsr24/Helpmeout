package soundserver;

import java.net.*;
import java.io.*;
import java.util.*;
public class NetworkModule extends Thread
{
   AlarmSpeaker a;
   private ServerSocket serverSocket;
   public NetworkModule(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	System.out.println("Sound Server waiting on port " +serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "+ server.getRemoteSocketAddress());
           
            DataInputStream in =new DataInputStream(server.getInputStream());
            System.out.println("\n*******PSR******\n");
            if(in.readUTF().equals("app says buzz"))
            {
            	System.out.println("MATCHED\n");

          	  a=new AlarmSpeaker();
          	  a.playSound();
            }
            else
            {
            	System.out.println("\n\n\n\n\n***********Oops*******\n\n\n\n");
            	a.stopSound();
            }
         }
         catch(Exception e)
         {
            e.printStackTrace();
            break;
         }
      }
   	}
}
   
