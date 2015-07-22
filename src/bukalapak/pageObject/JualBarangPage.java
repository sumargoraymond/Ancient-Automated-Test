package bukalapak.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bukalapak.core.Interaction;

public class JualBarangPage extends Interaction {

	public JualBarangPage(WebDriver driver) {
		super(driver);
	}
	
	public JualBarangPage inputNamaBarang(String namaBarang){
		WebElement fieldNamaBarang = waitForElementByID("product_name");
		fieldNamaBarang.sendKeys(namaBarang + Keys.TAB);
		return this;
	}
	
	public JualBarangPage pilihSaranKategoriTeratas(){
		WebElement optionCategoryPreference = waitForElementsByCssSelector("input[name='radio-suggestion']").get(0);
		optionCategoryPreference.click();
		return this;
	}
	
	public JualBarangPage clickUploadButton(){
		WebElement uploadButton = waitForElementsByXpath("//button[contains(@class,'button secondary btn-upload')]").get(0);
		uploadButton.click();
		return this;
	}
	
	public JualBarangPage pilihMerek(String merek){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_brand').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_brand")));
		select.selectByValue(merek);
		return this;
	}
	
	public JualBarangPage pilihOS(String operatingSystem){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_operating_system').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_operating_system")));
		select.selectByValue(operatingSystem);
		return this;
	}
	
	public JualBarangPage pilihDisplaySize(String displaySize){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_display_size').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_display_size")));
		select.selectByValue(displaySize);
		return this;
	}
	
	public JualBarangPage pilihCamera(String camera){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_camera').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_camera")));
		select.selectByValue(camera);
		return this;
	}
	
	public JualBarangPage pilihGaransi(String garansi){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_garansi').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_garansi")));
		select.selectByValue(garansi);
		return this;
	}
	
	public JualBarangPage pilihNetwork(String network){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_product_detail_attributes_network').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_product_detail_attributes_network")));
		select.selectByValue(network);
		return this;
	}
	
	public JualBarangPage pilihKondisiBarang(String kondisiBarang){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('product_new').style.display='block';");
		Select select = new Select(driver.findElement(By.id("product_new")));
		select.selectByValue(kondisiBarang);
		return this;
	}
	
	public JualBarangPage clickFeature(String feature){
		clickElementByXpath("//label[contains(@class,'checkbox')]//input[contains(@value,'"+feature+"')]");
		return this;
	}
	
	public JualBarangPage enterBeratBarang (int beratBarang){
		enterTextByCssSelector("input[id*='product_weight']", String.valueOf(beratBarang));
		return this;
	}
	
	public JualBarangPage enterStok (int stokBarang){
		enterTextByCssSelector("input[id*='product_stock']", String.valueOf(stokBarang));
		return this;
	}
	
	public JualBarangPage enterPrice (int price){
		enterTextByCssSelector("input[id*='product_price']", String.valueOf(price));
		return this;
	}
	
	public JualBarangPage enterDeskripsi (String deskripsi){
		enterTextByCssSelector("textarea[id*='product_description_bb']", deskripsi);
		return this;
	}
	
	public JualBarangPage clickWajibAsuransi (){
		clickElementByXpath("//label[contains(@class,'checkbox')]//input[contains(@id,'force_insurance')]");
		return this;
	}
	
	public JualBarangPage clickPilihDaerahFreeAsuransi (){
		clickElementByXpath("//a[contains(text(),'Pilih daerah')]");
		return this;
	}
	
	public JualBarangPage clickSeluruhIndonesiaFreeAsuransi (){
		clickElementByXpath("//label[contains(@class,'checkbox')]//input[contains(@id,'product_free_shipping_0')]");
		
		List<WebElement> freeShipping = waitForElementsByXpath("//label[contains(@class,'checkbox')]//input[contains(@id,'product_free_shipping')]");
		
		for(WebElement element: freeShipping){
			if(!element.isSelected()){
				Assert.fail("ERROR! Tidak semua daerah terselect ketika bebas pengiriman seluruh Indonesia dipilih");
			}
		}
		
		return this;
	}
	
	public JualBarangPage clickSimpan(){
		clickElementByXpath("//div[contains(@class,'page-bottom clearfix')]//input[contains(@value,'Simpan')]");
		return this;
	}
	
	
}
