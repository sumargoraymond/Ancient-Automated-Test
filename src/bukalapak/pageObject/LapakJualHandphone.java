package bukalapak.pageObject;

import java.text.NumberFormat;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bukalapak.core.Interaction;
import bukalapak.core.Utility;

public class LapakJualHandphone extends Interaction{

	public LapakJualHandphone(WebDriver driver) {
		super(driver);
	}
	
	
	public void verifikasiGambarAlcatel(){
		 boolean isImageAvailable = Utility.isImageAvailable(waitForElementByXpath("//img[contains(@src,'alcatel')]"));
	        
	        if(!isImageAvailable)
	        	Assert.fail("ERROR! Image is not loaded correctly!");
	}
	
	public void verifikasiHarga(int harga){
		WebElement textHarga = waitForElementByXpath("//span[contains(@itemprop,'price')]//span[contains(@class,'amount')]");
		if(!textHarga.getText().equals(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).format(harga))))
			Assert.fail("ERROR! Harga tidak sesuai!");
	}
	
	public void verifikasiNamaPelapak(String pelapak){
		WebElement namaPelapak = waitForElementByXpath("//h5[contains(@class,'user__name')]//a");
		if(!namaPelapak.getText().equals(pelapak))
			Assert.fail("ERROR! Nama pelapak salah!");
	}
	
}
