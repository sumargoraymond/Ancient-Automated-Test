package bukalapak;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.BukalapakHomepage;
import bukalapak.pageObject.SearchResultPage;

public class Search_Filter_Gratis_Ongkir extends Settings{
	
	private String keyWord = "Nokia";
	private String areaGratisOngkir = "0"; //Seluruh Indonesia
	private int searchResultPerPage = 24;
	
	
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
		resultPage.klikSideFilter();
		resultPage.pilihAreaGratisOngkir(areaGratisOngkir);
		Gudang.tungguBeberapaDetik(5);
		resultPage.verifikasiSemuaBarangGratisOngkir(searchResultPerPage);
		
	}

}
