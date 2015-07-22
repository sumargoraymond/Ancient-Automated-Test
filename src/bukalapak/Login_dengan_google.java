package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;

public class Login_dengan_google extends Settings{

	@Test(priority = 1)
	public void menekanTombolLogin1() throws InterruptedException {
		WebElement tombolLogin1 = driver.findElement(By.cssSelector("a[class*='btn-auth btn-auth--green login']"));
		tombolLogin1.click();
		Thread.sleep(300);
	}

	@Test(priority = 2)
	public void menekanTombolGoogle() throws InterruptedException {
		WebElement tombolGoogle = driver.findElement(By
				.linkText("Login dengan Google"));
		Assert.assertTrue(tombolGoogle.isDisplayed());
		tombolGoogle.click();
		Thread.sleep(300);
		Assert.assertTrue(driver.getCurrentUrl().contains(
				"https://accounts.google.com/ServiceLogin?"));
	}

	@Test(priority = 3)
	public void mengisiEmailDanPasswordGoogle() throws InterruptedException {
		WebElement fieldEmail = driver.findElement(By.id("Email"));
		fieldEmail.clear();
		fieldEmail.sendKeys("testingbukalapak@gmail.com");
		WebElement buttonNext = driver.findElement(By.id("next"));
		buttonNext.click();
		WebElement fieldPassword = driver.findElement(By.id("Passwd"));
		WebElement tombolLogin = driver.findElement(By.id("signIn"));
		fieldPassword.clear();
		fieldPassword.sendKeys("lieajalah");
		tombolLogin.click();
		Thread.sleep(300);
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.bukalapak.com/");
	}

	@Test(priority = 4)
	public void menekanFlashMessage() throws InterruptedException {
		WebElement flashMessage = driver.findElement(By
				.id("top_announcement"));
		Assert.assertTrue(flashMessage.isDisplayed());
		flashMessage.click();
		Thread.sleep(300);
		Assert.assertTrue(flashMessage.isDisplayed() == false);
	}

}
