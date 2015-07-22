package bukalapak;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.SearchResultPage;

public class Search_Result_Per_page extends Settings {

	private String keyWord = "Android";
	private int searchResultPerPage24 = 24;
	private int searchResultPerPage48 = 48;
	private int searchResultPerPage96 = 96;
	
	@Test(priority=0)
	public void browseKeyword(){
		BukalapakHomepage blHP = PageFactory.initElements(driver, BukalapakHomepage.class);
		blHP.enterSearchQuery(keyWord);
		blHP.klikSearchButton();
	}
	
	@Test(priority=1)
	public void checkResultPerPage(){
		SearchResultPage resultPage = PageFactory.initElements(driver, SearchResultPage.class);
		Gudang.tungguBeberapaDetik(5);
		
		resultPage.pilihSearchPerPage(String.valueOf(searchResultPerPage24));
		Gudang.tungguBeberapaDetik(3);
		resultPage.verifikasiSearchResultPerPage(searchResultPerPage24);
		
		resultPage.pilihSearchPerPage(String.valueOf(searchResultPerPage48));
		Gudang.tungguBeberapaDetik(3);
		resultPage.verifikasiSearchResultPerPage(searchResultPerPage48);
		
		resultPage.pilihSearchPerPage(String.valueOf(searchResultPerPage96));
		Gudang.tungguBeberapaDetik(3);
		resultPage.verifikasiSearchResultPerPage(searchResultPerPage96);
	}
	
}
