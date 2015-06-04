package alarmsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NetworkModule extends Thread {
	private DataInputStream in;
	private DataOutputStream out;

	public boolean IsAlarm(String type) {
		if (type.equals("help"))
			return true;
		return false;
	}

	public NetworkModule(DataInputStream in,DataOutputStream out)
	{

		this.in=in;
		this.out=out;
		
		// serverSocket.setSoTimeout(10000);
	}

	public void run() {
		try {
				JSONParser parser = new JSONParser();
				String Msg = in.readUTF();
				System.out.println(Msg);
				JSONObject MsgObj = (JSONObject) parser.parse(Msg);

				String type = MsgObj.get("type").toString();
				long uid = Long.parseLong(MsgObj.get("uid").toString());
				double latitude = Double.parseDouble(MsgObj.get("latitude")
						.toString());
				double longitude = Double.parseDouble(MsgObj.get("longitude")
						.toString());
				int id = Integer.parseInt(MsgObj.get("id").toString());
				int accStatus = 0;
				long victimuid = Long.parseLong(MsgObj.get("uid").toString());

				if (IsAlarm(type)) {
					System.out.println("network thread here");
					ApplicationContext context = new ClassPathXmlApplicationContext(
							"Beans.xml");
					Alarm alarm = (Alarm) context.getBean("beanAlarm");

					accStatus = 1;

					alarm.GenerateAck();
					alarm.setUid(uid);
					alarm.setLatitude(latitude);
					alarm.setLongitude(longitude);
					alarm.setStatus(1);

					// alarm.setDate(date);

					// out.writeUTF("Thank you for connecting to "+
					// server.getLocalSocketAddress() + "\nGoodbye!");

					User_m user_m = new User_m();
					System.out.println("VICTIMID-> "+id);
					user_m.updateUser(id, latitude, longitude, accStatus, uid);

					JSONObject toClient = new JSONObject();
					toClient.put("type", "ack");
					toClient.put("ack", alarm.getAck());
					JSONObject obj2 = (JSONObject) parser.parse(toClient
							.toString());
					System.out.println(obj2.toString());
					out.writeUTF(toClient.toString());
					// {"uid":123456,"type":"help","latitude":26.9339845,"longitude":75.9237571}
					Alarm_m alarm_m = new Alarm_m();
					alarm_m.addAlarm(alarm);
					
					List<User> Heroes = user_m.listusers(latitude, longitude);
					for (Iterator iterator = Heroes.iterator(); iterator
							.hasNext();) {
						User hero = (User) iterator.next();
						int stat = 2;
						if (hero.getAccStatus() != 1
								&& hero.getAccStatus() != 3) {
							user_m.updateUser(hero.getId(), hero.getLatitude(),
									hero.getLongitude(), 2, uid);
						}
						System.out.print("uid: " + hero.getId() + "\n");

					}

				} else if (type.equals("location")) {

					accStatus = 0;
					System.out.println("\n\n\n\nLOCATION PACKET RCVD\n\n\n\n");
					User_m user_m = new User_m();
					List<User> Heroes = user_m.listusers(id);

					for (Iterator iterator = Heroes.iterator(); iterator
							.hasNext();) {
						User hero = (User) iterator.next();
						JSONObject toclient = new JSONObject();
						toclient.put("type", "alarm");
						if (hero.getAccStatus() == 2) {
							List<User> myvictims = user_m.listusers(
									hero.getVictimUid(), "stop");
							for (Iterator it = myvictims.iterator(); iterator
									.hasNext();) {
								User victim = (User) it.next();
								toclient.put("latitude", victim.getLatitude());
								toclient.put("longitude", victim.getLongitude());
							}
							System.out.println("Location Reply->"
									+ toclient.toString());
							out.writeUTF(toclient.toString());
							user_m.updateUser(id, latitude, longitude, 2, uid);
						} else {
							toclient.put("type", "ok");
							System.out.println("Location Reply->"
									+ toclient.toString());
							out.writeUTF(toclient.toString());
							user_m.updateUser(id, latitude, longitude,
									hero.getAccStatus(), -1);
						}

					}

				} else if (type.equals("stop")) {

					User_m user_m = new User_m();
					user_m.updateUser(id, latitude, longitude, 0, uid);
					// list of users with victimuid as uid sent by victim
					List<User> Heroes = user_m.listusers(uid);
					
					for (Iterator iterator = Heroes.iterator(); iterator
							.hasNext();) {
						User hero = (User) iterator.next();
						if (hero.getAccStatus() == 2) {
							System.out.println("Setting accstatus 0 for id"+hero.getId());
							user_m.updateUser(hero.getId(), hero.getLatitude(),
									hero.getLongitude(), 0, hero.getUid());
						}

					}

					JSONObject toclient = new JSONObject();
					toclient.put("type", "fine");
					out.writeUTF(toclient.toString());
				} else if (type.equals("signup")) {

					User_m user_m = new User_m();
					User user = new User();
					user.setUid(uid);
					user.setAccStatus(0);
					user.setLatitude(latitude);
					user.setLongitude(longitude);
					user.setScore(0);
					user.setVictimUid(victimuid);
					user.setId(user_m.addUser(user));
					// list of users with victimuid as uid sent by victim

					JSONObject toclient = new JSONObject();
					toclient.put("type", "signup");
					toclient.put("id", user.getId());
					System.out.println("Signup Reply->" + toclient.toString());
					out.writeUTF(toclient.toString());
				} else if (type.equals("login")) {

					User_m user_m = new User_m();

					System.out.println("inside login module\n");
					List<User> Heroes = user_m.listusers(uid, "login");
					for (Iterator iterator = Heroes.iterator(); iterator
							.hasNext();) {
						User hero = (User) iterator.next();
						System.out.println("uid from db" + hero.getUid()
								+ "uid rcvd" + uid);
						if (hero.getUid() == uid) {

							JSONObject toClient = new JSONObject();
							toClient.put("id", hero.getId());
							toClient.put("success", 0);
							toClient.put("latitude", latitude);
							toClient.put("longitude", longitude);
							// System.out.println("Login Reply" +
							// toClient.toString());
							System.out.println("LOGINREPLYJSON"
									+ toClient.toString());
							out.writeUTF(toClient.toString());
							user_m.updateUser(hero.getId(), hero.getLatitude(),
									hero.getLongitude(), 0, victimuid);
						} else {
							JSONObject toClient = new JSONObject();
							toClient.put("success", 1);
							System.out.println("*******" + toClient.toString());
							out.writeUTF(toClient.toString());
							System.out.println("DIDNT MATCH\n");
						}

					}
				}

		}
		catch(Exception e)
		{
			
		}

	}

}