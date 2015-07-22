package bukalapak.core;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;


public class Utility extends Settings {
	
	public static String username;
	
	
	public static String returnTestParameter(ITestContext context, String parameter){
		try{
			if(context.getCurrentXmlTest().getParameter(parameter) == null){
				return getProperty(parameter)[0];
			}
			else
				return context.getCurrentXmlTest().getParameter(parameter);
		} catch ( Exception e) {
			throw propertyNotSet(parameter);
		}
	}
	
	public static String[] getProperty(String propertyName) {
		String propertyValue = null;
		try{
			Properties properties = new Properties(); 
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));
			propertyValue = properties.getProperty(propertyName);
		}catch (IOException e){
			e.printStackTrace();
		}
		if (propertyValue == null){
			return null;
		}
		else
			return propertyValue.split(",");
	}
	
	private static IllegalArgumentException propertyNotSet(String parameter) {
		return new IllegalArgumentException(("Invalid Property value [" + parameter + "]. Please set the property [" + parameter + "] with the correct value"));
	}
	
	public static String ambilUsernameAcak() {
		String[] part1 = { "joko", "ani", "budi", "arif", "putri", "dini",
				"rivan", "dani", "danu", "genta" }, part2 = { "lapak",
				"distro", "toko", "store", "dagang", "makmur", "jaya",
				"elektronik", "sembako", "antique" };
		Random rand = new Random();
		int part3 = rand.nextInt(1001);
		int random1 = rand.nextInt(part1.length);
		int random2 = rand.nextInt(part2.length);
		String Username = part1[random1] + part2[random2]
				+ String.valueOf(part3);
		return Username;
	}
	
	public static String ambilNamaAcak() {
		String[] part1 = { "Joko", "Ani", "Budi", "Arif", "Putri", "Dini",
				"Rivan", "Dani", "Danu", "Genta", "Renny", "Kautzar", "Tyas",
				"Sidiq" }, part2 = { "Anwar", "Manurung", "Andini", "Hamzah",
				"Permana", "Putra", "Abdi", "Sihab", "Dyansah", "Kyosaki",
				"Wijayanti", "Prawira", "Sistha" };
		Random rand = new Random();
		int random1 = rand.nextInt(part1.length);
		int random2 = rand.nextInt(part2.length);
		String Name = part1[random1] + " " + part2[random2];
		return Name;
	}
	
	public static String ambilEmail() {
		String[] emails = { "gmail.com"/* , "yahoo.com", "yahoo.co.id" */};
		Random rand = new Random();
		int randomEmail = rand.nextInt(emails.length);
		String email = emails[randomEmail];
		return email;
	}
	
	public static int generateRandomNumber(int min, int max) {

		min = 0;
		max = 9;
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static boolean isImageAvailable(WebElement image){
		boolean isImageAvailable = false;
		try{
			HttpResponse response = new DefaultHttpClient().execute(new HttpGet(image.getAttribute("src")));
			if (response.getStatusLine().getStatusCode() != 200)
				isImageAvailable = false;
			else
				isImageAvailable = true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return isImageAvailable;
	}
}
