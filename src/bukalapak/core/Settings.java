package bukalapak.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Settings {
	
	protected static  WebDriver driver = null;
	private String urlBukalapak = "https://www.bukalapak.com/";
	private String url;
	private String browserType ="FF";
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Settings.driver = driver;
	}
	
	//Singleton
	private static Settings instance = null;
	public Settings() {
		if(getInstance() == null){
			Settings.setInstance(this);
		}
	}
	
	//BeforeClass; launching drivers and browsers
	@BeforeClass(alwaysRun=true)
	public void openBrowser (ITestContext context){
		browserType = Utility.returnTestParameter(context, "browser");
		if(browserType==null)
			browserType = "FF";
		if(browserType.equalsIgnoreCase("FF") || browserType.equalsIgnoreCase("Firefox")){
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setEnableNativeEvents(true);
			firefoxProfile.setPreference("browser.download.folderList",2);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
			setDriver(new EventFiringWebDriver(new FirefoxDriver(firefoxProfile)));
			driver.manage().window().maximize();
		} else if(browserType.equalsIgnoreCase("GC") || browserType.equalsIgnoreCase("Chrome")){
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "./resource/chromedriver/chromedriver.exe");
			ChromeDriverService service = ChromeDriverService.createDefaultService();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			setDriver(new EventFiringWebDriver(new ChromeDriver(service, options)));
		}
		
		url = Utility.returnTestParameter(context,"url");
		if(url!=null){
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		} else {
			driver.get(urlBukalapak);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		}

	}
	
	//AfterClass; closing browsers setelah tiap eksekusi
	@AfterClass(alwaysRun=true)
	public void closeBrowser(ITestContext context){
		try {
            driver.close();
            Thread.sleep(3000);
        } catch (Exception b) {
            b.getMessage();
        }
	}

	public static Settings getInstance() {
		return instance;
	}

	public static void setInstance(Settings instance) {
		Settings.instance = instance;
	}

	//Close driver
	public  void closeDriver(WebDriver driver){
		driver.close();
	}
}
