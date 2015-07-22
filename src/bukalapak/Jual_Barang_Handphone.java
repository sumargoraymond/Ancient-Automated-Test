package bukalapak;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.core.Utility;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.JualBarangPage;
import bukalapak.pageObject.LapakJualHandphone;

public class Jual_Barang_Handphone extends Settings {

	private String userName = "Raga Pinilih";
	private String email = "testingbukalapak+sl1@gmail.com";
	private String password = "sl1bukalapak";
	private String urlBukalapakSellAfterLogin = "https://www.bukalapak.com/products/new?from=dropdown";
	private String namaBarang = "Alcatel Linux " + Utility.generateRandomNumber(4999, 4000);
	
	public String merekHP = "Alcatel";
	public String operatingSystem = "Linux";
	public String feature1 = "Touchscreen";
	public String feature2 = "3G";
	public String displaySize = "1.8\"";
	public String camera = "No Camera";
	public String garansi = "1-12 bulan";
	public String network = "GSM";
	public String kondisiBarang = "true"; //Baru
	public int beratBarang = 1000;
	public int stokBarang = 1;
	public int price = 10000000;
	public String deskripsi = "Deskripsi untuk Alcatel Linux. Linux adalah operating system tak berbayar sehingga moderen dan terjangkau";
	
	@Test(priority = 1)
	public void menekanTombolJualBarang(){
		WebElement tombolLogin1= driver.findElement(By.cssSelector("a[class*='sell tooltipstered']"));
		tombolLogin1.click();
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
	public void menekanTombolLogin(){
		WebElement tombolLogin2 = driver.findElement(By.name("commit"));
		Assert.assertTrue(tombolLogin2.isDisplayed());
		tombolLogin2.click();
		Assert.assertEquals(driver.getCurrentUrl(), urlBukalapakSellAfterLogin);
		WebElement namaUser = driver.findElement(By.className("nav-helper__link-alt"));
		Assert.assertTrue(namaUser.isDisplayed());
		Assert.assertEquals(namaUser.getText().substring(0,namaUser.getText().length()), userName);
	}
	
	@Test(priority = 6)
	public void isiDataProduk() throws InterruptedException, IOException {
		
		JualBarangPage jualBarang = PageFactory.initElements(driver, JualBarangPage.class);
		jualBarang.inputNamaBarang(namaBarang);
		jualBarang.pilihSaranKategoriTeratas();
		
		WebElement kategori = driver.findElements(By.xpath("//a[contains(@class,'chzn-single')]//span")).get(0);
		WebElement subKategori1 = driver.findElements(By.xpath("//a[contains(@class,'chzn-single')]//span")).get(1);
		
		if(!(kategori.getText().equals("Handphone"))){
			Assert.fail("Error! Kategori tidak sesuai dengan item!");
		} else {
			if(!(subKategori1.getText().equals("HP & Smartphone")))
				Assert.fail("Error! Subkategori tidak sesuai dengan item!");
		}
		
		jualBarang.clickUploadButton();
		final String dir = System.getProperty("user.dir") + "\\resource\\scriptUploadGambarProduct.exe";
		
        Runtime.getRuntime().exec(dir);
		Gudang.tungguBeberapaDetik(2);
		
		jualBarang.pilihMerek(merekHP);
		jualBarang.pilihOS(operatingSystem);
		jualBarang.pilihDisplaySize(displaySize);
		jualBarang.pilihCamera(camera);
		jualBarang.pilihGaransi(garansi);
		jualBarang.pilihNetwork(network);
		jualBarang.pilihKondisiBarang(kondisiBarang);
		
		jualBarang.clickFeature(feature1);
		jualBarang.clickFeature(feature2);
		jualBarang.enterBeratBarang(beratBarang);
		jualBarang.enterStok(stokBarang);
		jualBarang.enterPrice(price);
		jualBarang.enterDeskripsi(deskripsi);
		jualBarang.clickWajibAsuransi().clickPilihDaerahFreeAsuransi().clickSeluruhIndonesiaFreeAsuransi().clickSimpan();
	}
	
	@Test(priority = 7)
	public void verifikasiPembuatanLapak(){
		LapakJualHandphone hasilLapak = PageFactory.initElements(driver, LapakJualHandphone.class);
		hasilLapak.verifikasiGambarAlcatel();
		hasilLapak.verifikasiHarga(price);
		hasilLapak.verifikasiNamaPelapak(userName);
	}
	
}
