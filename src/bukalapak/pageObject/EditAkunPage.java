package bukalapak.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bukalapak.core.Interaction;
import bukalapak.core.Utility;

public class EditAkunPage extends Interaction {

	public EditAkunPage(WebDriver driver) {
		super(driver);
	}
	
	public  void gantiFotoProfile() throws InterruptedException, IOException {
		
		try{
			List<WebElement> buttonDelete = driver.findElements(By.xpath("//button[contains(@class,'delete-attach')]"));
			for(int i = 0 ; i < buttonDelete.size(); i++){
				buttonDelete.get(i).click();
			}
		} catch(Exception e){
			
		}
		
		final String dir = System.getProperty("user.dir") + "\\resource\\scriptUploadProfpic.exe";
        System.out.println("current dir = " + dir);
		
        List<WebElement> buttonUpload = driver.findElements(By.xpath("//button[contains(@class,'button secondary btn-upload')]"));
        buttonUpload.get(0).click();
        
        Runtime.getRuntime().exec(dir);
	}

	public  void gantiBanner() throws InterruptedException, IOException {
		
		final String dir = System.getProperty("user.dir") + "\\resource\\scriptUploadBanner.exe";
        System.out.println("current dir = " + dir);
		
        List<WebElement> buttonUpload = driver.findElements(By.xpath("//button[contains(@class,'button secondary btn-upload')]"));
        buttonUpload.get(1).click();
        
        Runtime.getRuntime().exec(dir);
	}
	
	public void verifikasiAvatarBanner(){
		
		 boolean isImageAvailable = Utility.isImageAvailable(waitForElementByCssSelector("img[src*='avatar']"));
	        
	        if(!isImageAvailable)
	        	Assert.fail("ERROR! Avatar image is not loaded correctly!");
	        
	        isImageAvailable = Utility.isImageAvailable(waitForElementByCssSelector("img[src*='banner']"));
	        
	        if(!isImageAvailable)
	        	Assert.fail("ERROR! Header image is not loaded correctly!");
	}
	
	public void clickSimpan(){
		clickElementByCssSelector("input[value='Simpan']");
	}
	
	public void verifikasiEditedAkun(String keyword){
		List<WebElement> listData = waitForElementsByXpath("//div[contains(@id,'setting_account')]//dl[not(@id) and not(@class)]//dd");
		boolean isEdited = false;
		
		for(WebElement element:listData){
			if(element.getText().contains(keyword)){
				isEdited = true;
				break;
			}
		}
		
		if(!isEdited)
			Assert.fail("ERROR! The edited value:*" + keyword +"* is not found!");
	}
	
	public void klikTabTutupLapak(){
		clickElementByCssSelector("a[href*='close_store']");
		
		try{
			clickElementByCssSelector("input[value='Buka Lapak']");
		} catch (Exception e){
			//Lapak sedang tidak tertutup
		}
	}
	
}
