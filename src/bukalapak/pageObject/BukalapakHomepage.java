package bukalapak.pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bukalapak.Gudang;
import bukalapak.core.Interaction;

public class BukalapakHomepage extends Interaction {

	public BukalapakHomepage(WebDriver driver) {
		super(driver);
	}
	
	
	public void menekanTombolLogin1(){
		WebElement tombolLogin1= driver.findElement(By.linkText("LOGIN"));
		WebElement kotakLogin = driver.findElement(By.id("login_box"));
		Assert.assertTrue(tombolLogin1.isDisplayed());
		tombolLogin1.click();
		Gudang.tungguBeberapaDetik(3);
		Assert.assertTrue(kotakLogin.isDisplayed());
	}
	
	public void mengisiFieldEmail(String email) throws InterruptedException{
		WebElement fieldEmail = driver.findElement(By.id("user_session_username"));
		Assert.assertTrue(fieldEmail.isDisplayed());
		fieldEmail.clear();
		Thread.sleep(1000);
		fieldEmail.sendKeys(email);
	}
	
	public void mengisiFieldUsername(String userName){
		WebElement fieldEmail = driver.findElement(By.id("user_session_username"));
		Assert.assertTrue(fieldEmail.isDisplayed());
		fieldEmail.clear();
		fieldEmail.sendKeys(userName);
	}
	
	public void mengisiFieldPassword(String passWord) throws InterruptedException{
		WebElement fieldEmail = driver.findElement(By.id("user_session_password"));
		Assert.assertTrue(fieldEmail.isDisplayed());
		fieldEmail.clear();
		Thread.sleep(1000);
		fieldEmail.sendKeys(passWord);
	}
	
	public BukalapakHomepage klikBukaDompet(){
		WebElement buttonBukaDompet = driver.findElement(By.cssSelector("a[class*='dompet']"));
		buttonBukaDompet.click();
		return this;
	}
	
	public void klikHistoryBukaDompet(){
		WebElement buttonBukaDompet = driver.findElement(By.xpath("//div[contains(@class,'dropdown-menu__footer')]//a[contains(text(),'BukaDompet')]"));
		buttonBukaDompet.click();
	}
	
	public void klikTombolLogin(){
		WebElement tombolLogin1= waitForElementByCssSelector("a[class*='btn-auth btn-auth--green login']");
		tombolLogin1.click();
	}
	
	public void klikMessage(){
		WebElement tombolLogin1= waitForElementByCssSelector("a[class*='nav-header__link nav-header__link--message']");
		tombolLogin1.click();
	}
	
	public void klikLihatSemuaPesan(){
		WebElement tombolLogin1= waitForElementByXpath("//a[contains(text(),'Lihat semua pesan')]");
		tombolLogin1.click();
	}
	
	public void enterSearchQuery(String keyWord){
		enterTextByID("search_keywords", keyWord);
	}
	
	public void klikSearchButton(){
		clickElementByClassName("omnisearch__submit");
	}
	
	
}
