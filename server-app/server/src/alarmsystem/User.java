package alarmsystem;

public class User 
{
	private int id;
	private long uid;
	private double latitude;
	private double longitude;
	private int accStatus;
	private int score;
	private long victimUid;
	
	   public void setLatitude(float latitude)
	   {
		   this.latitude=latitude;
	   }
	   public void setLongitude(float longitude)
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
	   public int getAccStatus()
	   {
		   return accStatus;
	   }
	   public void setAccStatus(int accStatus)
	   {
		   this.accStatus=accStatus;
	   }
	   
	   
	   public int getscore()
	   {
		   return score;
	   }
	   public void setscore(int score)
	   {
		   this.score=score;
	   }
	   
	   
	   public void setUid(int uid)
	   {
	      this.uid  = uid;
	   }
	   public long getUid()
	   {
		   return uid;
	   }

	   public void victimUid(int victimuid)
	   {
	      this.victimUid  = victimUid;
	   }
	   public long victimUid()
	   {
		   return victimUid;
	   }

}
