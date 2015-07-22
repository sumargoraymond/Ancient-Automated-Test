package bukalapak.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Interaction {
	
	protected static WebDriver driver;
	private static int timeOutInSeconds = 60;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Interaction.driver = driver;
	}
	
	//For constructor, driver initialization
	public Interaction(WebDriver driver){
		setDriver(driver);
	}
	
	/************************** By ID ******************************************/
	
	public static WebElement waitForElementByID (String id){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}
	
	public static List<WebElement> waitForElementsByID (String id){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
	}
	
	public static void clickElementByID (String id){
		waitForElementByID(id).click();
	}
	
	public static void clickElementByID (String id, int index){
		waitForElementsByID(id).get(index).click();
	}
	
	public static void enterTextByID (String id, String message){
		waitForElementByID(id).sendKeys(message);
	}
	
	public static void enterTextByID (String id, String message, int index){
		waitForElementsByID(id).get(index).sendKeys(message);
	}
	
	public static String getTextByID (String id){
		return waitForElementByID(id).getText();
	}
	
	
	
	/************************ By ClassName ****************************************/
	
	public static WebElement waitForElementByClassName (String className){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
	}
	
	public static List<WebElement> waitForElementsByClassName (String className){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
	}
	
	public static void clickElementByClassName (String className){
		waitForElementByClassName(className).click();
	}
	
	public static void clickElementByClassName (String className, int index){
		waitForElementsByClassName(className).get(index).click();
	}
	
	public static void enterTextByClassName (String className, String message){
		waitForElementByClassName(className).sendKeys(message);
	}
	
	public static void enterTextByClassName (String className, String message, int index){
		waitForElementsByClassName(className).get(index).sendKeys(message);
	}
	
	public static String getTextByClassName (String className){
		return waitForElementByClassName(className).getText();
	}
	
	
	/*******************************By XPath*******************************/
	
	public static WebElement waitForElementByXpath(String locatorXpath){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorXpath)));
	}
	
	public static List<WebElement> waitForElementsByXpath(String locatorXpath){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorXpath)));
	}
	
	public static void clickElementByXpath(String locatorXpath){
		waitForElementByXpath(locatorXpath).click();
	}
	
	public static void clickElementByXpath(String locatorXpath, int index){
		waitForElementsByXpath(locatorXpath).get(index).click();
	}
	
	public static void enterTextByXpath(String locatorXpath, String message){
		waitForElementByXpath(locatorXpath).sendKeys(message);
	}
	
	public static void enterTextByXpath(String locatorXpath, String message, int index){
		waitForElementsByXpath(locatorXpath).get(index).sendKeys(message);
	}
	
	public static String getTextByXpath(String locatorXpath){
		return waitForElementByXpath(locatorXpath).getText();
	}
	
	
	/***************************By CSS Selector**********************************/
	
	public static WebElement waitForElementByCssSelector(String locatorCss){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorCss)));
	}
	
	public static List<WebElement> waitForElementsByCssSelector(String locatorCss){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locatorCss)));
	}
	
	public static void clickElementByCssSelector(String locatorCss){
		waitForElementByCssSelector(locatorCss).click();
	}
	
	public static void clickElementByCssSelector(String locatorCss, int index){
		waitForElementsByCssSelector(locatorCss).get(index).click();
	}
	
	public static void enterTextByCssSelector(String locatorCss, String message){
		waitForElementByCssSelector(locatorCss).sendKeys(message);
	}
	
	public static void enterTextByCssSelector(String locatorCss, String message, int index){
		waitForElementsByCssSelector(locatorCss).get(index).sendKeys(message);
	}
	
	public static String getTextByCssSelector(String locatorCss){
		return waitForElementByCssSelector(locatorCss).getText();
	}
	
	
}
