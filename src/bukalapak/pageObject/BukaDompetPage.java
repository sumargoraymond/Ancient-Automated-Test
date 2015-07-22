package bukalapak.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bukalapak.core.Interaction;

public class BukaDompetPage extends Interaction{

	public BukaDompetPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isOnBukaDompetPage () throws InterruptedException{
		boolean isOnPage = false;
		try{
			@SuppressWarnings("unused")
			WebElement breadCrumb = driver.findElement(By.xpath("//li[contains(text(),'BukaDompet')]"));
			@SuppressWarnings("unused")
			WebElement mutasiOption = driver.findElement(By.xpath("//a[contains(text(),'Mutasi')]"));
			@SuppressWarnings("unused")
			WebElement pendingOption = driver.findElement(By.xpath("//a[contains(text(),'Pending')]"));
			isOnPage = true;
		} catch (Exception e){
			isOnPage = false;
		}
		return isOnPage;
		
	}
	
	public BukaDompetPage klikTambahSaldo () {
		WebElement buttonTambahSaldo = driver.findElement(By.xpath("//a[contains(text(),'Tambah Saldo')]"));
		buttonTambahSaldo.click();
		return this;
	}
	
	public boolean isOnTambahSaldo () throws InterruptedException{
		boolean isOnPage = false;
		try{
			@SuppressWarnings("unused")
			WebElement breadCrumb = driver.findElement(By.xpath("//li[contains(text(),'Tambah Saldo')]"));
			@SuppressWarnings("unused")
			WebElement mutasiOption = driver.findElement(By.xpath("//input[contains(@id,'deposit_topup_amount')]"));
			@SuppressWarnings("unused")
			WebElement pendingOption = driver.findElement(By.xpath("//div[contains(@id,'deposit_topup_bank_account_id_chzn')]"));
			isOnPage = true;
		} catch (Exception e){
			isOnPage = false;
		}
		return isOnPage;
		
	}

}
