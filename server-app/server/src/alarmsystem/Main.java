package alarmsystem;

	import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Date;

	public class Main
	{
	   public static void main(String[] args) 
	   {
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
	      ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");

	      Alarm objA = (Alarm) context.getBean("beanAlarm");
	      objA.GenerateAck();

	   }
	
	}
