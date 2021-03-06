package alarmsystem;



import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import java.sql.*;
import org.hibernate.HibernateException; 
import org.hibernate.*; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

public class Alarm_m
{
   private static SessionFactory factory; 
   
     public void addAlarm(Alarm alarm)
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
         session.save(alarm); 
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
   /* Method to  READ all the employees 
   public void listEmployees( )
   {
      Session session = factory.openSession();
      Transaction tx = null;
      try
      {
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM Employee").list(); 
         for (Iterator iterator =employees.iterator(); iterator.hasNext();)
         {
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
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
   /* Method to UPDATE salary for an employee 
   public void updateEmployee(Integer EmployeeID, int salary )
   {
      Session session = factory.openSession();
      Transaction tx = null;
      try
      {
         tx = session.beginTransaction();
         Employee employee =(Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
		 session.update(employee); 
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
   /* Method to DELETE an employee from the records 
   public void deleteEmployee(Integer EmployeeID)
   {
      Session session = factory.openSession();
      Transaction tx = null;
      try
      {
         tx = session.beginTransaction();
         Employee employee =(Employee)session.get(Employee.class, EmployeeID); 
         session.delete(employee); 
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


