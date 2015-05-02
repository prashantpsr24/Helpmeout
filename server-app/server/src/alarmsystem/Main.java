package alarmsystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.hibernate.*; 
import org.hibernate.cfg.*;

import java.io.IOException;
import java.util.Date;

import org.hibernate.*;
	public class Main
	{
		
	   public static void main(String[] args) 
	   {
		  /* Alarm alarm = new Alarm();
       	alarm.GenerateAck();
		   Alarm_m alarm_m=new Alarm_m();
       	alarm_m.addAlarm(alarm);
       	*/
       /*	User user = new User();
       	//alarm.GenerateAck();
		   User_m user_m=new User_m();
       	user_m.addUser(user);*/
		   try
		      {
			   	int port=6069;
		         Thread t = new NetworkModule(port);
		         t.start();
		         
		      }
		   catch(IOException e)
		      {
		         e.printStackTrace();
		      }
	      
	   }
	
	}
