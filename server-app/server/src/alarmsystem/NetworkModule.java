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
   
   public boolean IsAlarm(String type)
   {
	   if(type.equals("help"))
		   return true;
	   		return false;
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
            //System.out.println("\n*******PSR******\n"+in.readUTF());
            
      	  	JSONParser parser = new JSONParser();
      	  	String Msg=in.readUTF();
      	  	JSONObject MsgObj = (JSONObject)parser.parse(Msg);
            String type=MsgObj.get("type").toString();
            long uid=Long.parseLong(MsgObj.get("uid").toString());
        	double latitude=Double.parseDouble(MsgObj.get("latitude").toString());
        	double longitude=Double.parseDouble(MsgObj.get("longitude").toString());
        	int accStatus=0;
            if(IsAlarm(type))
            {
            	System.out.println("network thread here");
            	ApplicationContext context =new ClassPathXmlApplicationContext("Beans.xml");
            	Alarm alarm = (Alarm) context.getBean("beanAlarm");
            	
            	
            	accStatus=1;
            	
            	alarm.GenerateAck();
            	alarm.setUid(uid);
            	alarm.setLatitude(latitude);
            	alarm.setLongitude(longitude);
            	alarm.setStatus(1);
            	
            	
            	//alarm.setDate(date);
            	DataOutputStream out =new DataOutputStream(server.getOutputStream());
                //out.writeUTF("Thank you for connecting to "+ server.getLocalSocketAddress() + "\nGoodbye!");
                
            	User_m user_m=new User_m();
            	user_m.updateUser(3, latitude, longitude, accStatus, uid);
            	
                JSONObject toClient=new JSONObject();
                toClient.put("ack", alarm.getAck());
                JSONObject obj2 = (JSONObject)parser.parse(toClient.toString());
                System.out.println(obj2.toString());
                out.writeUTF(toClient.toString());
                //{"uid":123456,"type":"help","latitude":26.9339845,"longitude":75.9237571}
                Alarm_m alarm_m=new Alarm_m();
            	alarm_m.addAlarm(alarm);
            	
            	
            	
            	
            	
            }
            else if(type.equals("location"))
            {
            	
            	
            		accStatus=0;
            	User_m user_m=new User_m();
            	user_m.updateUser(3, latitude, longitude, accStatus, uid);
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