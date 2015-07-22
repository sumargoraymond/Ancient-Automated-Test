package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.HalamanPesanPage;
import bukalapak.pageObject.HalamanUserPage;

public class Tinggalkan_Pesan extends Settings {
	
	private String userName = "Raga Pinilih";
	private String userName2 = "Siomay Hangat Joss";
	private String email = "testingbukalapak+sl1@gmail.com";
	private String password = "sl1bukalapak";
	private String urlBukalapak = "https://www.bukalapak.com/";
	private String message = "Halo Siomay!";
	private String email2 = "siomayhangatjoss@gmail.com";
	private String password2 = "kangkungBalado";
	
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
	
	@Test (priority = 6)
	public void mengirimPesan(){
		driver.get("https://www.bukalapak.com/siomayhangatjoss");
		HalamanUserPage lamanUser = PageFactory.initElements(driver, HalamanUserPage.class);
		lamanUser.klikTinggalkanPesan();
		
		HalamanPesanPage lamanPesan = PageFactory.initElements(driver, HalamanPesanPage.class);
		lamanPesan.enterPesan(message);
		lamanPesan.klikKirim();
	}
	
	@Test (priority=7)
	public void logOut(){
		Gudang.tekanTombolMyLapak();
		Gudang.tekanTombolLogout();
	}
	
	@Test (priority=8)
	public void loginReceiver() throws InterruptedException{
		driver.get("https://www.bukalapak.com/");
		BukalapakHomepage bLHomePage = PageFactory.initElements(driver, BukalapakHomepage.class);
		bLHomePage.klikTombolLogin();
		bLHomePage.mengisiFieldEmail(email2);
		bLHomePage.mengisiFieldPassword(password2);
		
		WebElement tombolLogin2 = driver.findElement(By.name("commit"));
		Assert.assertTrue(tombolLogin2.isDisplayed());
		tombolLogin2.click();
		Assert.assertEquals(driver.getCurrentUrl(), urlBukalapak);
		WebElement namaUser = driver.findElement(By.className("nav-helper__link-alt"));
		Assert.assertTrue(namaUser.isDisplayed());
		Assert.assertEquals(namaUser.getText().substring(0,namaUser.getText().length()), userName2);
	}
	
	@Test(priority = 9)
	public void checkMessage(){
		BukalapakHomepage bLHomePage = PageFactory.initElements(driver, BukalapakHomepage.class);
		bLHomePage.klikMessage();
		bLHomePage.klikLihatSemuaPesan();
		
		HalamanPesanPage lamanPesan = PageFactory.initElements(driver, HalamanPesanPage.class);
		lamanPesan.verifyMessage(userName, message);
		lamanPesan.pilihSemuaPesan();
		lamanPesan.pilihOption("delete");
		lamanPesan.klikLakukan();
		
		Gudang.tungguBeberapaDetik(7);
		
		lamanPesan.verifikasiInboxKosong();
		
	}
	
	@Test (priority=10)
	public void logOutLast(){
		Gudang.tekanTombolMyLapak();
		Gudang.tekanTombolLogout();
	}
	
}
