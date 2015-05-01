package alarmsystem;

import java.net.*;
import java.io.*;

import org.json.simple.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class NetworkModule extends Thread
{
   private ServerSocket serverSocket;
   
   public boolean IsAlarm(DataInputStream in)
   {
	   return true;
   }
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
	  // JSONObject obj = new JSONObject();
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
            
            if(IsAlarm(in))
            {
            	System.out.println("network thread here");
            	ApplicationContext context =new ClassPathXmlApplicationContext("Beans.xml");
            	Alarm alarm = (Alarm) context.getBean("beanAlarm");
            	//Alarm alarm = new Alarm();
            	alarm.GenerateAck();
            	DataOutputStream out =new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to "+ server.getLocalSocketAddress() + "\nGoodbye!");
                out.writeUTF("Your Ack is"+ alarm.getAck());
                Alarm_m alarm_m=new Alarm_m();
            	alarm_m.addAlarm(alarm);
            }
           
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