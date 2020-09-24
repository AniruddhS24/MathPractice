import java.util.*;
import java.io.*;

public class CurrentUserInfo {
	
	private static String cuname;
	private static String cuage;
	private static String cuusername;
	private static String cupassword;
	private static String analysis;
	
	public static void init() throws IOException {
		Scanner cup = new Scanner(new File("currentuser.txt"));
		String cuis = cup.next();
		Scanner cu = new Scanner(new File("logs/" + cuis + ".txt"));
		
		cuusername = cu.next();
		cupassword = cu.next();
		cuname = cu.next();
		cuage = cu.next();
		
		cu.close();
		cup.close();
	}
	
	public static String getName()
	{
		return cuname;
	}
	
	public static String getAge()
	{
		return cuage;
	}
	
	public static String getUsername()
	{
		return cuusername;
	}
	
	public static String getPassword()
	{
		return cupassword;
	}
	
	public static String getUserFileName()
	{
		return "logs/" + cuusername + ".txt";
	}
	
	public static String getUserFileNameImage()
	{
		return "imgs/" + cuusername + ".jpeg";
	}
	
	public static void setUserAnalysis(String s)
	{
		analysis = s;
	}
}
