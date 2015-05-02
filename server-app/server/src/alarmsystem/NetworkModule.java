package alarmsystem;

import java.net.*;
import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class NetworkModule extends Thread
{
   private ServerSocket serverSocket;
   
   public boolean IsAlarm(DataInputStream in)
   {
	   return true;
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
            
      	  	JSONParser parser = new JSONParser();
      	  	//String Msg=in.readUTF();
      	  	//JSONObject obj = (JSONObject)parser.parse(Msg);
            
            if(IsAlarm(in))
            {
            	System.out.println("network thread here");
            	ApplicationContext context =new ClassPathXmlApplicationContext("Beans.xml");
            	Alarm alarm = (Alarm) context.getBean("beanAlarm");
            	//Alarm alarm = new Alarm();
            	alarm.GenerateAck();
            	DataOutputStream out =new DataOutputStream(server.getOutputStream());
                //out.writeUTF("Thank you for connecting to "+ server.getLocalSocketAddress() + "\nGoodbye!");
                
                
                JSONObject toClient=new JSONObject();
                toClient.put("ack", alarm.getAck());
                JSONObject obj2 = (JSONObject)parser.parse(toClient.toString());
                System.out.println(obj2.toString());
                out.writeUTF(toClient.toString());
                //{"uid":123456,"type":"help","latitude":26.9339845,"longitude":75.9237571}
                Alarm_m alarm_m=new Alarm_m();
            	alarm_m.addAlarm(alarm);
            }
           
            server.close();
         }
         
         catch(Exception s)
         {
            s.printStackTrace();
            break;
         }
         
         
      }
   }
   
}