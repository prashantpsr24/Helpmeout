package alarmsystem;

import java.net.*;
import java.io.*;

public class NetworkModule extends Thread
{
   private ServerSocket serverSocket;
   
   public void closesocket(Socket server)
   {
	   try
	   {
		   server.close();
	   }
	   catch (IOException e) 
	   {
		
		   e.printStackTrace();
	   }
   }
   public NetworkModule(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	System.out.println("Waiting for client on port " +serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "+ server.getRemoteSocketAddress());
            DataInputStream in =new DataInputStream(server.getInputStream());
            
            System.out.println("\n*******PSR******\n"+in.readUTF());
            
            DataOutputStream out =new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "+ server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }
         
         catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }
         
         catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   
}