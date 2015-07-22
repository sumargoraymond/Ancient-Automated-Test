package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;

public class Login_dengan_facebook extends Settings {

	@Test(priority = 1)
	public void menekanTombolLogin1() {
		WebElement tombolLogin1 = driver.findElement(By.cssSelector("a[class*='btn-auth btn-auth--green login']"));
		tombolLogin1.click();
		Gudang.tungguBeberapaDetik(3);
	}

	@Test(priority = 2)
	public void menekanTombolFacebook() throws InterruptedException {
		WebElement tombolFacebook = driver.findElement(By
				.linkText("Login dengan Facebook"));
		Assert.assertTrue(tombolFacebook.isDisplayed());
		tombolFacebook.click();
		Thread.sleep(300);
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/login.php?"));
	}

	@Test(priority = 3)
	public void mengisiEmailDanPasswordFacebook() {
		WebElement fieldEmail = driver.findElement(By.id("email"));
		WebElement fieldPassword = driver.findElement(By.id("pass"));
		WebElement tombolLogin = driver
				.findElement(By.id("loginbutton"));
		fieldEmail.clear();
		fieldEmail.sendKeys("gajahmangkrak@mail.com");
		fieldPassword.clear();
		fieldPassword.sendKeys("sarunggajah");
		tombolLogin.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.bukalapak.com/#_=_");
	}

	@Test(priority = 4)
	public void menekanFlashMessage(){
		WebElement flashMessage = driver.findElement(By.id("top_announcement"));
		Assert.assertTrue(flashMessage.isDisplayed());
		flashMessage.click();
		//Gudang.tungguBeberapaDetik(3);
		Assert.assertTrue(flashMessage.isDisplayed()==false);
	}	

}
