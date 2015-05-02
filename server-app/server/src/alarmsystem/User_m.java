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
	
	
	/*public void listHeroes(double latitude,double longitude)
	   {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try
	      {
	         tx = session.beginTransaction();
	         double minLat=latitude-0.05;
	         double maxLat=latitude+0.05;
	         double minLong;
	         double maxLong;
	         String hql = "FROM User E WHERE E.latitude between "+minLat+" AND "+maxLat;
	         List Heroes = session.createQuery(hql).list(); 
	         for (Iterator iterator =Heroes.iterator(); iterator.hasNext();)
	         {
	            User hero = (User) iterator.next(); 
	            System.out.print("uid: " + hero.getUid()); 
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
	   }*/	
	
}