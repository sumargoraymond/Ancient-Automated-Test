package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;

public class Login_dengan_email extends Settings {
	
	private String userName = "Raga Pinilih";
	private String email = "testingbukalapak+sl1@gmail.com";
	private String password = "sl1bukalapak";
	private String urlBukalapak = "https://www.bukalapak.com/";
	
	@Test(priority = 1)
	public void menekanTombolLogin1(){
		BukalapakHomepage bLHomePage = PageFactory.initElements(driver, BukalapakHomepage.class);
		bLHomePage.klikTombolLogin();
	}
	
	@Test(priority = 2)
	public void mengisiFieldEmail() throws InterruptedException{
		BukalapakHomepage bLHomePage = PageFactory.initElements(driver, BukalapakHomepage.class);
		bLHomePage.mengisiFieldEmail(email);
	}
	
	@Test(priority = 3)
	public void mengisiFieldPassword() throws InterruptedException{
		BukalapakHomepage bLHomePage = PageFactory.initElements(driver, BukalapakHomepage.class);
		bLHomePage.mengisiFieldPassword(password);
	}
	
	@Test(priority = 4)
	public void menekanChecklistIngatSaya(){
		WebElement checklistIngatSaya = driver.findElement(By.id("user_session_remember_me"));
		Assert.assertTrue(checklistIngatSaya.isDisplayed());
		checklistIngatSaya.click();
	}
	
	@Test(priority = 5)
	public void menekanTombolLogin2(){
		WebElement tombolLogin2 = driver.findElement(By.name("commit"));
		Assert.assertTrue(tombolLogin2.isDisplayed());
		tombolLogin2.click();
		Assert.assertEquals(driver.getCurrentUrl(), urlBukalapak);
		WebElement namaUser = driver.findElement(By.className("nav-helper__link-alt"));
		Assert.assertTrue(namaUser.isDisplayed());
		Assert.assertEquals(namaUser.getText().substring(0,namaUser.getText().length()), userName);
	}

	@Test(priority = 6)
	public void menekanFlashMessage(){
		WebElement flashMessage = driver.findElement(By.id("top_announcement"));
		Assert.assertTrue(flashMessage.isDisplayed());
		flashMessage.click();
		//Gudang.tungguBeberapaDetik(3);
		Assert.assertTrue(flashMessage.isDisplayed()==false);
	}
	
}
