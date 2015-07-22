package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;

public class Keranjang_belanja_quickbuyer extends Settings{


	@Test(priority = 1)
	public void cekKeranjangKosong() {
		Gudang.cekKeranjangKosong1();
		Gudang.cekHalamanKeranjangKosong();
	}

	@Test(priority = 2)
	public void cariBarang() {
		Gudang.cariBarangdiSearchBarDenganFieldKosong();
		Gudang.tungguBeberapaDetik(5);
	}

	@Test(priority = 3)
	public void memilihBarangRandom() {
		Gudang.pilihBarangRandomDariHalamanPencarian();
	}

	@Test(priority = 4)
	public void klikBeli() {
		boolean adaTombolNego = false;
		WebElement tombolBeli;
		
		try{
			@SuppressWarnings("unused")
			WebElement buttonNego = driver.findElement(By.xpath("//a[contains(@class,'button secondary product-offer-action')]"));
			adaTombolNego = true;
		} catch (Exception e){
			adaTombolNego = false;
		}
		if(adaTombolNego){
			tombolBeli = driver
					.findElement(By
							.xpath("//input[contains(@class,'button-cart-icon')]"));
		} else {
			tombolBeli = driver
					.findElement(By
							.xpath("//input[contains(@class,'full-width button-cart-icon')]"));
		}
	
		tombolBeli.click();
		Gudang.tungguBeberapaDetik(3);
	}

	@Test(priority = 5)
	public void klikTambahBarangLain() {
		WebElement linkTambahBarangLain = Gudang.driver.findElement(By
				.linkText("Tambah barang lain"));
		Assert.assertTrue(linkTambahBarangLain.isDisplayed());
		linkTambahBarangLain.click();
		Gudang.tungguBeberapaDetik(3);
	}

	@Test(priority = 6)
	public void cekKeranjangBertambahSatu() {
		Gudang.tekanTombolKeranjang();
		WebElement notifKeranjangBertambah = Gudang.driver.findElement(By
				.xpath("//li[contains(@class,'cart')]//span[contains(@class,'nav-header__count')]"));
		WebElement notifTotalBarang = Gudang.driver.findElement(By
				.xpath("//div[contains(@class,'menu-history__small')]"));
		Assert.assertTrue(notifKeranjangBertambah.isDisplayed());
		Assert.assertEquals(notifKeranjangBertambah.getText(), "1");
		Assert.assertTrue(notifTotalBarang.isDisplayed());
		Assert.assertEquals(notifTotalBarang.getText(), "Total: 1 barang");
	}

	@Test(priority = 7)
	public void cekHalamanKeranjangBertambahSatu() {
		Gudang.klikLihatKeranjang();
		WebElement amount = driver.findElement(By.xpath("//span[contains(@class,'on-right')]//strong"));
		Assert.assertEquals(amount.getText(), "1 barang");
	}

	@Test(priority = 8)
	public void delete1ItemDariKeranjang() {
		WebElement buttonRemove = driver.findElement(By.cssSelector("a[class*='cbox-remove-cart']"));
		buttonRemove.click();
		Gudang.tungguBeberapaDetik(20);
		WebElement buttonHapusBarang = driver.findElement(By.linkText("hapus barang"));
		buttonHapusBarang.click();
	}

	@Test(priority = 9)
	public void cekKeranjangBerkurang1() {
		Gudang.cariBarangdiSearchBarDenganFieldKosong();
		Gudang.cekKeranjangKosong();
		Gudang.cekHalamanKeranjangKosong();
	}

}
