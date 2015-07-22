package bukalapak.pageObject;

import org.openqa.selenium.WebDriver;

import bukalapak.core.Interaction;

public class HalamanUserPage extends Interaction{

	public HalamanUserPage(WebDriver driver) {
		super(driver);
	}

	public void klikTinggalkanPesan(){
		clickElementByXpath("//a[contains(@class,'secondary button btn-create-room')]//span[contains(text(),'Tinggalkan pesan')]");
	}
}
