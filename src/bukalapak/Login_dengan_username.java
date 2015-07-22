package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;

public class Login_dengan_username extends Settings {
	
	private String userName = "ragasl1";
	private String name = "Raga Pinilih";
	private String password = "sl1bukalapak";
	
	@Test(priority = 1)
	public void menekanTombolLogin1(){
		WebElement tombolLogin1= driver.findElement(By.cssSelector("a[class*='btn-auth btn-auth--green login']"));
		tombolLogin1.click();
		Gudang.tungguBeberapaDetik(3);
	}
	
	@Test(priority = 2)
	public void mengisiFieldUsername(){
		BukalapakHomepage blHomepage = PageFactory.initElements(driver, BukalapakHomepage.class);
		blHomepage.mengisiFieldUsername(userName);
	}
	
	@Test(priority = 3)
	public void mengisiFieldPassword() throws InterruptedException{
		BukalapakHomepage blHomepage = PageFactory.initElements(driver, BukalapakHomepage.class);
		blHomepage.mengisiFieldPassword(password);
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
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.bukalapak.com/");
		WebElement namaUser = driver.findElement(By.className("nav-helper__link-alt"));
		Assert.assertTrue(namaUser.isDisplayed());
		Assert.assertEquals(namaUser.getText().substring(0,namaUser.getText().length()), name);
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
