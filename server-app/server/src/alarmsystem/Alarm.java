package alarmsystem;
import java.util.Date;

public class Alarm
{
	   private int id;
	   private long uid;
	   private double latitude;
	   private double longitude;
	   private int status;
	   private long ack;
	   private Date date;
	   
	   
	   public void setLatitude(double latitude)
	   {
		   this.latitude=latitude;
	   }
	   public void setLongitude(double longitude)
	   {
		   this.longitude=longitude;
	   }
	   public double getLatitude()
	   {
		   return this.latitude;
	   }
	   public double getLongitude()
	   {
		   return this.longitude;
	   }
	   public void setId(int id)
	   {
	      this.id  = id;
	   }
	   public int getId()
	   {
		   return id;
	   }
	   public void setUid(long uid)
	   {
	      this.uid  = uid;
	   }
	   public long getUid()
	   {
		   return uid;
	   }
	   
	   public void setAck(long ack)
	   {
	      this.ack  = ack;
	   }
	   public long getAck()
	   {
	      return ack;
	   }
	   
	   public void setStatus(int status)
	   {
	      this.status  =status;
	   }
	   public int getStatus()
	   {
	       return status;
	   }
	  
	   public void setDate(Date date)
	   {
	      this.date  =date;
	   }
	   public Date getDate()
	   {
	      return date;
	   }
	   
	   
	   public void GenerateAck()
	   {
		   
		   setAck(System.currentTimeMillis());
		   System.out.println(ack);
	   }
	   
	   
/*
	   public void getStatus(){
	      System.out.println("World Message1 : " + message1);
	   }

	   public void getMessage2(){
	      System.out.println("World Message2 : " + message2);
	   }
*/
	}
