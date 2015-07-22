package bukalapak.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bukalapak.core.Interaction;

public class HalamanPesanPage extends Interaction {

	public HalamanPesanPage(WebDriver driver) {
		super(driver);
	}

	public void enterPesan (String message){
		enterTextByCssSelector("textarea[id*='messages_message_message']", message);
	}
	
	public void klikKirim(){
		clickElementByXpath("//div[contains(@class,'message-chat-btn')]//input[contains(@name,'commit')]");
	}
	
	public void verifyMessage(String pengirim, String isiPesan){
		if(!(waitForElementByXpath("//a[contains(@class,'block-link')]//div[contains(@class,'sender')]//h5")).getText().contains(pengirim))
		Assert.fail("ERROR! Pengirim tidak sesuai!");
		
		if(!(waitForElementByXpath("//a[contains(@class,'block-link')]//div[contains(@class,'subject')]//p")).getText().contains(isiPesan))
			Assert.fail("ERROR! Isi pesan tidak sesuai!");
	}
	
	public void pilihSemuaPesan(){
		List<WebElement> listPesan = waitForElementsByCssSelector("input[id*='inbox_id']");
		for(WebElement element:listPesan){
			element.click();	
		}
	}
	
	public void pilihOption(String option){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('toggle_action').style.display='block';");
		Select select = new Select(driver.findElement(By.id("toggle_action")));
		select.selectByValue(option);
	}
	
	public void klikLakukan(){
		clickElementByCssSelector("input[name*='commit']");
	}
	
	public void verifikasiInboxKosong(){
		try{
			waitForElementByXpath("//div[contains(@class,'sender') and not(h5)]");
			waitForElementByXpath("//div[contains(@class,'subject')and not(p)]");
		} catch (Exception e){
			e.printStackTrace();
			Assert.fail("ERROR! Inbox tidak kosong!");
		}
			
	}
}
