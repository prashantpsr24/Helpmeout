package alarmsystem;

import java.util.Iterator;
import java.util.List;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import java.sql.*;

import org.hibernate.*; 
import org.hibernate.cfg.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class User_m 
{
	private static SessionFactory factory; 
	
	   public void addUser(User user)
	   {
		   try
		   {
		      factory = new Configuration().configure().buildSessionFactory();
		   }
		   catch (Throwable ex) 
		   { 
			   System.err.println("Failed to create sessionFactory object." + ex);
		       throw new ExceptionInInitializerError(ex); 
		   }
		   Session session = factory.openSession();
		   Transaction tx = null;
		   try
		   {
	         tx = session.beginTransaction();
	         session.save(user); 
	         tx.commit();
		   }
		   catch (HibernateException e) 
		   {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		   }
		   finally
		   {
	         session.close(); 
		   }
		   
	   }	
	
	   public void updateUser(int UserId, double latitude,double longitude,int accStatus,long victimuid ){
		   try
		   {
		      factory = new Configuration().configure().buildSessionFactory();
		   }
		   catch (Throwable ex) 
		   { 
			   System.err.println("Failed to create sessionFactory object." + ex);
		       throw new ExceptionInInitializerError(ex); 
		   }
		      Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         User user = 
		                    (User)session.get(User.class, UserId);
		         
		         user.setLatitude(latitude);
		         user.setLongitude(longitude);
				 user.setAccStatus(accStatus);
				 user.setVictimUid(victimuid);
		         
				 tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		   }
	public void listusers(double latitude,double longitude)
	   {
			try
		   {
		      factory = new Configuration().configure().buildSessionFactory();
		   }
		   catch (Throwable ex) 
		   { 
			   System.err.println("Failed to create sessionFactory object." + ex);
		       throw new ExceptionInInitializerError(ex); 
		   }
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try
	      {
	         tx = session.beginTransaction();
	         double minlat=latitude-0.05;
         	 double minlong=longitude-0.05;
         	 double maxlat=latitude+0.05;
         	 double maxlong=longitude+0.05;
         	 
	         String hql = "FROM User E WHERE E.latitude between "+minlat+" AND "+maxlat;
	         List Heroes = session.createQuery(hql).list(); 
	         for (Iterator iterator =Heroes.iterator(); iterator.hasNext();)
	         {
	            User hero = (User) iterator.next(); 
	            System.out.print("uid: " + hero.getId()+"\n"); 
	            //System.out.print("  Last Name: " + hero.getLastName()); 
	            //System.out.println("  Salary: " + hero.getSalary()); 
	         }
	         tx.commit();
	      }
	      catch (HibernateException e) 
	      {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      finally 
	      {
	         session.close(); 
	      }
	   }	
	
}
