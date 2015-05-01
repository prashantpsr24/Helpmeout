package alarmsystem;

public class Location
{
	int id;
	private double latitude;
	private double longitude;
	

	public void setId(int id)
	{
		this.id  = id;
	}
	public int getId()
	{
	   return id;
	}
	public void setLatitude(double latitude)
	{
		this.latitude=latitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude=latitude;
	}
	public double getLatitude()
	{
		return latitude;
	}
	public double getLongitude()
	{
		return longitude;
	}
}
