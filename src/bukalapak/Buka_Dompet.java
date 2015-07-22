package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import bukalapak.core.Settings;
import bukalapak.pageObject.BukaDompetPage;
import bukalapak.pageObject.BukalapakHomepage;

public class Buka_Dompet extends Settings {
	
	private String userName = "Raga Pinilih";
	private String email = "testingbukalapak+sl1@gmail.com";
	private String password = "sl1bukalapak";
	private String urlBukalapak = "https://www.bukalapak.com/";
	
	@Test(priority = 1)
	public void menekanTombolLogin1(){
		WebElement tombolLogin1= driver.findElement(By.cssSelector("a[class*='btn-auth btn-auth--green login']"));
		//WebElement kotakLogin = driver.findElement(By.id("login_box"));
		/*Assert.assertTrue(tombolLogin1.isDisplayed());*/
		tombolLogin1.click();
		//Gudang.tungguBeberapaDetik(3);
	/*	Assert.assertTrue(kotakLogin.isDisplayed());*/
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
	
	@Test(priority = 6)
	public void mengecekBukaDompet() throws InterruptedException{
	BukalapakHomepage bLHome = PageFactory.initElements(driver, BukalapakHomepage.class);
	bLHome.klikBukaDompet().klikHistoryBukaDompet();
	BukaDompetPage bukaDompet = PageFactory.initElements(driver, BukaDompetPage.class);
	if(!bukaDompet.isOnBukaDompetPage())
		Assert.fail("ERROR! Not navigating to BukaDompet page!");
	}
	
	@Test(priority = 7)
	public void mengecekTambahSaldo() throws InterruptedException{
		BukaDompetPage bukaDompet = PageFactory.initElements(driver, BukaDompetPage.class);
		bukaDompet.klikTambahSaldo();
		Gudang.tungguBeberapaDetik(4);
		if(!bukaDompet.isOnTambahSaldo()){
			Assert.fail("ERROR! Not navigating to Tambah Saldo page!");
		}
	}
	
	

}
