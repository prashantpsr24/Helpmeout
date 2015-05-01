package alarmsystem;
import java.util.Date;

public class Alarm
{
	   private int id;
	   private int uid;
	   private float latitude;
	   private float longitude;
	   private int status;
	   private long ack;
	   private Date date;
	   
	   
	   public void setLatitude(float latitude)
	   {
		   this.latitude=latitude;
	   }
	   public void setLongitude(float longitude)
	   {
		   this.longitude=longitude;
	   }
	   public float getLatitude()
	   {
		   return this.latitude;
	   }
	   public float getLongitude()
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
	   public void setUid(int uid)
	   {
	      this.uid  = uid;
	   }
	   public int getUid()
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
