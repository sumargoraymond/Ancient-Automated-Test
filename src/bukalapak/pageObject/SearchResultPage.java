package bukalapak.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bukalapak.core.Interaction;

public class SearchResultPage extends Interaction {

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	public void pilihFilter(String filter){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('search_sort_by').style.display='block';");
		Select select = new Select(driver.findElement(By.id("search_sort_by")));
		select.selectByValue(filter);
	}
	
	public void checkIfSearchResultIsFilteredTermurah(){
		List<WebElement> listElement= waitForElementsByXpath("//div[contains(@class,'product-price')]//span[contains(@class,'amount positive')]");
		List<String> array = new ArrayList<String>();
		
		for(WebElement element: listElement){
			array.add(element.getText());
		}
		
		String[] arrayHarga = new String[ array.size() ];
		array.toArray(arrayHarga);
		
		int[] arrayPrice = new int[arrayHarga.length];
		
		for(int i = 0 ; i < arrayHarga.length; i++){
			arrayPrice[i] = Integer.parseInt(arrayHarga[i].replace(".", ""));
		}
		
		for(int i = 1 ; i < arrayPrice.length; i++){
			int prev = arrayPrice[i-1];
			int cur = arrayPrice[i];
			if(cur < prev)
				Assert.fail("ERROR! Filter mengurutkan berdasarkan yang Termurah tidak sukses!");
		}
	}
	
	public void checkIfSearchResultIsFilteredTermahal(){
		List<WebElement> listElement= waitForElementsByXpath("//div[contains(@class,'product-price')]//span[contains(@class,'amount positive')]");
		List<String> array = new ArrayList<String>();
		
		for(WebElement element: listElement){
			array.add(element.getText());
		}
		
		String[] arrayHarga = new String[ array.size() ];
		array.toArray(arrayHarga);
		
		int[] arrayPrice = new int[arrayHarga.length];
		
		for(int i = 0 ; i < arrayHarga.length; i++){
			arrayPrice[i] = Integer.parseInt(arrayHarga[i].replace(".", ""));
		}
		
		for(int i = 0 ; i < arrayPrice.length; i++)
			System.out.println(arrayPrice[i]);
		
		for(int i = 1 ; i < arrayPrice.length; i++){
			int prev = arrayPrice[i-1];
			int cur = arrayPrice[i];
			if(cur > prev)
				Assert.fail("ERROR! Filter mengurutkan berdasarkan yang Termahal tidak sukses!");
		}
	}
	
	public void klikSideFilter(){
		clickElementByXpath("//div[contains(@class,'side-filter--nav')]//div[contains(text(),'Cari lebih detil')]");
	}
	
	public void pilihAreaGratisOngkir(String area){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('search_free_shipping_coverage').style.display='block';");
		Select select = new Select(driver.findElement(By.id("search_free_shipping_coverage")));
		select.selectByValue(area);
	}
	
	public void verifikasiSemuaBarangGratisOngkir(int searchResultPerPage){
		try{
			List<WebElement> listBarang = waitForElementsByXpath("//span[contains(@class,'product-delivery__free-shipping tooltip tooltipstered')]");

			if(listBarang.size()<searchResultPerPage)
				Assert.fail("ERROR! Ada barang yang tidak free ongkir!");
			
			for(WebElement element:listBarang){
				if(!(element.getText().equals("GRATIS BIAYA KIRIM")))
					Assert.fail("ERROR! Ada barang yang tidak free ongkir!");
			}
		} catch(Exception e){
			e.printStackTrace();
			Assert.fail("ERROR! Ada barang yang tidak free ongkir!");
		}
	}
	
	public void pilihSearchPerPage(String searchPerPage){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('search_per_page').style.display='block';");
		Select select = new Select(driver.findElement(By.id("search_per_page")));
		select.selectByValue(searchPerPage);
	}
	
	public void verifikasiSearchResultPerPage(int searchResultPerPage){
		List<WebElement> listBarang = waitForElementsByXpath("//ul[contains(@class,'products')]//li[contains(@class,'product')]");
		if(listBarang.size()!=searchResultPerPage)
			Assert.fail("ERROR! Search result tidak sesuai! Expected: " + 
						searchResultPerPage +" but found: " + listBarang.size() + " instead");
	}

}
