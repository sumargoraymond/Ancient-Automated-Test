package bukalapak.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bukalapak.core.Interaction;

public class TutupLapakPage extends Interaction{

	public TutupLapakPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterAlasanTutupLapak(String alasan){
		enterTextByID("user_store_closed_reason", alasan);
	}
	
public void masukkanTanggalMulaiTutupLapak(String[]tanggal){
		
		//Tanggal
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('user_store_closed_start_date_3i').style.display='block';");
		Select select = new Select(driver.findElement(By.id("user_store_closed_start_date_3i")));
		select.selectByValue(tanggal[0]);
		
		//Bulan
		js.executeScript("document.getElementById('user_store_closed_start_date_2i').style.display='block';");
		select = new Select(driver.findElement(By.id("user_store_closed_start_date_2i")));
		select.selectByValue(tanggal[1]);
		
		//Tahun
		js.executeScript("document.getElementById('user_store_closed_start_date_1i').style.display='block';");
		select = new Select(driver.findElement(By.id("user_store_closed_start_date_1i")));
		select.selectByValue(tanggal[2]);
		}
	
	public void masukkanTanggalLapakBukaKembali(String[] tanggal){
		
		//Tanggal
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('user_store_closed_end_date_3i').style.display='block';");
		Select select = new Select(driver.findElement(By.id("user_store_closed_end_date_3i")));
		select.selectByValue(tanggal[0]);
		
		//Bulan
		js.executeScript("document.getElementById('user_store_closed_end_date_2i').style.display='block';");
		select = new Select(driver.findElement(By.id("user_store_closed_end_date_2i")));
		select.selectByValue(tanggal[1]);
		
		//Tahun
		js.executeScript("document.getElementById('user_store_closed_end_date_1i').style.display='block';");
		select = new Select(driver.findElement(By.id("user_store_closed_end_date_1i")));
		select.selectByValue(tanggal[2]);
		}
	
	public void verifikasiTutupLapakSeharusnyaEnabled(){
		if(!waitForElementByClassName("btn-tutup-lapak").isEnabled())
			Assert.fail("ERROR! Button Tutup Lapak is not enabled!");
	}
	
	public void verifikasiTutupLapakSeharusnyaDisabled(){
		if(waitForElementByClassName("btn-tutup-lapak").isEnabled())
			Assert.fail("ERROR! Button Tutup Lapak seharusnya disabled!");
	}
	
	public void klikButtonTutupLapak(){
		clickElementByClassName("btn-tutup-lapak");
	}
	
	public void verifikasiLapakTertutupPemilikAkun(String tanggal, String bulan, String tahun){
		if(!waitForElementByXpath("//div[contains(@class,'tutup-lapak-notification')]//p").getText().contains("Anda menutup lapak Anda sampai tanggal "+tanggal+" "+bulan+" "+tahun))
			Assert.fail("ERROR! Lapak tidak tertutup!");
	}
	
	public void verifikasiLapakTertutupBuyer(String tanggal, String bulan, String tahun){
		if(!(waitForElementByXpath("//div[contains(@class,'tutup-lapak-notification')]//p").getText().contains("Lapak ini ditutup sementara oleh pemiliknya dan akan dibuka kembali") &&
				waitForElementByXpath("//div[contains(@class,'tutup-lapak-notification')]//p").getText().contains(tanggal+" " + bulan +" " + tahun)))
			Assert.fail("ERROR! Lapak tidak tertutup!");

	}
	

}
