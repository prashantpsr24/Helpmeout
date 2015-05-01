package soundserver;

import sun.audio.*;

import java.io.*;


 
public class AlarmSpeaker
{
  public static void main(String[] args) 
  {
	  try
      {
	   	int port=6070;
         Thread t = new NetworkModule(port);
         t.start();
         
      }
   catch(IOException e)
      {
         e.printStackTrace();
      }
  }
  
  public void playSound() 
  {
    try
    {
      // get the sound file as a resource out of my jar file;
      // the sound file must be in the same directory as this class file.
      // the input stream portion of this recipe comes from a javaworld.com article.
    	System.out.println("Trying");
      InputStream inputStream = getClass().getResourceAsStream("husten.wav");
      System.out.println(inputStream);
      AudioStream audioStream = new AudioStream(inputStream);
      AudioPlayer.player.start(audioStream);
      
    }
    catch (Exception e)
    {
    	 System.out.println("Tried2");
      // a special way i'm handling logging in this application
      e.printStackTrace();
    }
  }
}