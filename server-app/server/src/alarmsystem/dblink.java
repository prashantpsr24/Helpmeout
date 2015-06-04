package alarmsystem;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class dblink extends Thread  {

	public static double randInt() {

		int max=1000;
		int min=-1000;
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum*1.0/10000000.0;
	}
	public void run()
	{
		while(true)
		{
		try {
			sleep(10000);
			
			
			User_m user_m = new User_m();
			List <User> Heroes=user_m.listusers(0, 0);
			for (Iterator iterator =Heroes.iterator(); iterator.hasNext();)
	        {
				double p=randInt();
				if(p<0.000090)
					continue;
	           User hero = (User) iterator.next(); 
	           user_m.updateUser(hero.getId(), hero.getLatitude()+randInt(), hero.getLongitude()+randInt(), hero.getAccStatus(), hero.getVictimUid()); 
	           //System.out.print("  Last Name: " + hero.getLastName()); 
	           //System.out.println("  Salary: " + hero.getSalary()); 
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
}
