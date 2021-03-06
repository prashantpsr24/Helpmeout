package alarmsystem;

public class User {
	private int id;
	private long uid;
	private double latitude;
	private double longitude;
	private int accStatus;
	private int score;
	private long victimUid;

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
	
			this.accStatus = accStatus;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setVictimUid(long victimuid) {
		this.victimUid = victimuid;
	}

	public long getVictimUid() {
		return victimUid;
	}

}
