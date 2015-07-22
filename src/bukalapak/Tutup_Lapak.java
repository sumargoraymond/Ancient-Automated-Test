package bukalapak;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.EditAkunPage;
import bukalapak.pageObject.TutupLapakPage;

public class Tutup_Lapak extends Settings{

	private String userName = "ragasl1";
	private String name = "Raga Pinilih";
	private String password = "sl1bukalapak";
	private String alasanTutup = "Rehat dulu";
	
	SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy");
	
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
	
	@Test (priority=6)
	public void menutupLapak(){
		Gudang.tekanTombolMyLapak();
		Gudang.tekanTombolPengaturan();
		
		EditAkunPage editAkun = PageFactory.initElements(driver, EditAkunPage.class);
		
		editAkun.klikTabTutupLapak();
		
		TutupLapakPage tutupLapak = PageFactory.initElements(driver, TutupLapakPage.class);
		tutupLapak.enterAlasanTutupLapak(alasanTutup);
		
		Date date = Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		
		//Today
		date = c.getTime();
		String[] tanggalSekarang = df.format(date).toString().split("/");
		
		//Tomorrow
		c.add(Calendar.DAY_OF_YEAR, 1);
		date = c.getTime();
		String[] tanggalBesok = df.format(date).toString().split("/");
		
		
		//Yesterday
		date = Calendar.getInstance().getTime();
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		date=c.getTime();
		String[] tanggalKemarin = df.format(date).toString().split("/");
		
		//For verification
		date = Calendar.getInstance().getTime();
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		date = c.getTime();
		Locale id = new Locale("in", "ID");
		SimpleDateFormat df = new SimpleDateFormat("dd/MMMM/yyyy",id);
		String bulanUntukVerifikasi =  df.format(date).toString().split("/")[1];

		tutupLapak.masukkanTanggalMulaiTutupLapak(tanggalKemarin);
		tutupLapak.verifikasiTutupLapakSeharusnyaDisabled();

		tutupLapak.masukkanTanggalMulaiTutupLapak(tanggalSekarang);
		tutupLapak.masukkanTanggalLapakBukaKembali(tanggalSekarang);
		tutupLapak.verifikasiTutupLapakSeharusnyaDisabled();
		
		tutupLapak.masukkanTanggalLapakBukaKembali(tanggalBesok);
		tutupLapak.verifikasiTutupLapakSeharusnyaEnabled();
		
		tutupLapak.klikButtonTutupLapak();
		
		tutupLapak.verifikasiLapakTertutupPemilikAkun(tanggalBesok[0], bulanUntukVerifikasi, tanggalBesok[2]);
	}
	
	@Test (priority=7)
	public void logOut(){
		Gudang.tekanTombolMyLapak();
		Gudang.tekanTombolLogout();
	}
	
	@Test(priority=8)
	public void checkLapakTertutup(){
		driver.get("https://www.bukalapak.com/"+userName);
		
		Date date = Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		date = Calendar.getInstance().getTime();
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		date = c.getTime();
		Locale id = new Locale("in", "ID");
		SimpleDateFormat df = new SimpleDateFormat("dd/MMMM/yyyy",id);
		String[] tanggalVerifikasi =  df.format(date).toString().split("/");
		
		TutupLapakPage tutupLapak = PageFactory.initElements(driver, TutupLapakPage.class);
		tutupLapak.verifikasiLapakTertutupBuyer(tanggalVerifikasi[0], tanggalVerifikasi[1], tanggalVerifikasi[2]);
		
	}
	
}
