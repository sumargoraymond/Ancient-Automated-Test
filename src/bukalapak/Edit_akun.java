package bukalapak;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.pageObject.EditAkunPage;

public class Edit_akun extends Settings {

	@Test(priority = 1)
	public void menekanTombolLogin1() {
		WebElement tombolLogin1 = driver.findElement(By
				.linkText("LOGIN"));
		Assert.assertTrue(tombolLogin1.isDisplayed());
		tombolLogin1.click();
		Gudang.tungguBeberapaDetik(3);
	}

	@Test(priority = 2)
	public void mengisiFieldEmail() {
		WebElement fieldEmail = driver.findElement(By
				.id("user_session_username"));
		Assert.assertTrue(fieldEmail.isDisplayed());
		fieldEmail.clear();
		fieldEmail.sendKeys("testingbukalapak+sl1@gmail.com");
	}

	@Test(priority = 3)
	public void mengisiFieldPassword() {
		WebElement fieldPassword = driver.findElement(By
				.id("user_session_password"));
		Assert.assertTrue(fieldPassword.isDisplayed());
		fieldPassword.clear();
		fieldPassword.sendKeys("sl1bukalapak");
	}

	@Test(priority = 4)
	public void menekanChecklistIngatSaya() {
		WebElement checklistIngatSaya = driver.findElement(By
				.id("user_session_remember_me"));
		Assert.assertTrue(checklistIngatSaya.isDisplayed());
		checklistIngatSaya.click();
	}

	@Test(priority = 5)
	public void menekanTombolLogin2() {
		WebElement tombolLogin2 = driver.findElement(By.name("commit"));
		Assert.assertTrue(tombolLogin2.isDisplayed());
		tombolLogin2.click();
		WebElement haloPlaceHolder = driver.findElement(By.xpath("//li[contains(@class,'username')]"));

		Assert.assertTrue(haloPlaceHolder.isDisplayed());
		Assert.assertEquals(haloPlaceHolder.getText(), "Halo, Raga Pinilih!");
	}


	@Test(priority = 7)
	public void menekanTombolMyLapak() {
		Gudang.tekanTombolMyLapak();
	}

	@Test(priority = 8)
	public void menekanTombolPengaturan() {
		Gudang.tekanTombolPengaturan();
	}

	@Test(priority = 9)
	public void menekanTombolEdit() {
		Gudang.tekanTombolEdit();
	}

	@Test(priority = 10)
	public void gantiFotoProfile() throws InterruptedException, IOException {
		EditAkunPage editAkun = PageFactory.initElements(driver, EditAkunPage.class);
		editAkun.gantiFotoProfile();
	}

	@Test(priority = 11)
	public void gantiBanner() throws InterruptedException, IOException {
		EditAkunPage editAkun = PageFactory.initElements(driver, EditAkunPage.class);
		editAkun.gantiBanner();
	}

	@Test(priority = 12)
	public void mengisiDeskripsi() {
		Gudang.isiDeskripsi();
	}

	@Test(priority = 13)
	public void mengisiNama() {
		Gudang.isiNama();
	}

	@Test(priority = 14)
	public void memilihJenisKelamin() {
		Gudang.memilihJenisKelaminLakiLaki();
	}
	
	@Test(priority = 15)
	public void memilihTanggalLahir(){
		int tanggal = 12;
		int bulan = 7;
		int tahun = 1990;
		Gudang.memilihTanggalLahir(tanggal, bulan, tahun);
	}

	@Test(priority = 16)
	public void mengisiIsiNomorTelepon(){
		String nomor_telepon = "089699063839";
		Gudang.isiFieldNomorTelepon(nomor_telepon);
	}
	
	@Test(priority = 17)
	public void memilihProvinsi(){
		Gudang.PilihProvinsiJawaBarat();
	}
	
	@Test(priority = 18)
	public void memilihKotaKabupaten(){
		Gudang.pilihKabupatenBogor();
	}
	
	@Test(priority = 19)
	public void memilihKecamatan(){
		Gudang.pilihKecamatanBogor();
	}
	
	@Test(priority = 20)
	public void verifyEditAkunResult(){
		
		String namaPemilikAkun = "Raga Pinilih";
		String jenisKelamin = "Laki-laki";
		String tanggalLahir = "12 Juli 1990";
		String nomorTelepon = "089699063839";
		String provinsi = "Jawa Barat";
		String kotaKabupaten = "Kab. Bogor";
		String kecamatan = "Dramaga";
		
		EditAkunPage editAkun = PageFactory.initElements(driver, EditAkunPage.class);
		editAkun.clickSimpan();
		Gudang.tungguBeberapaDetik(2);
		editAkun.verifikasiAvatarBanner();
		
		editAkun.verifikasiEditedAkun(namaPemilikAkun);
		editAkun.verifikasiEditedAkun(jenisKelamin);
		editAkun.verifikasiEditedAkun(tanggalLahir);
		editAkun.verifikasiEditedAkun(nomorTelepon);
		editAkun.verifikasiEditedAkun(provinsi);
		editAkun.verifikasiEditedAkun(kotaKabupaten);
		editAkun.verifikasiEditedAkun(kecamatan);
	}
}
