package bukalapak;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.SearchResultPage;

public class Search_Filter_Termurah extends Settings {
	
	private String keyWord = "Android";
	private String filter = "price:asc"; //termurah
	
	@Test(priority=0)
	public void browseKeyword(){
		BukalapakHomepage blHP = PageFactory.initElements(driver, BukalapakHomepage.class);
		blHP.enterSearchQuery(keyWord);
		blHP.klikSearchButton();
	}
	
	@Test(priority=1)
	public void checkFilterTermurah(){
		SearchResultPage resultPage = PageFactory.initElements(driver, SearchResultPage.class);
		Gudang.tungguBeberapaDetik(5);
		resultPage.pilihFilter(filter);
		Gudang.tungguBeberapaDetik(3);
		resultPage.checkIfSearchResultIsFilteredTermurah();
		
	}

}
