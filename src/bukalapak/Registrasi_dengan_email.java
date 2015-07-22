package bukalapak;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bukalapak.core.Settings;
import bukalapak.core.Utility;

public class Registrasi_dengan_email extends Settings {

	@Test(priority = 1)
	public void menekanTombolDaftar() {
		WebElement tombolDaftar = driver.findElement(By
				.cssSelector("a[class*='btn-auth btn-auth--default']"));
		tombolDaftar.click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.bukalapak.com/register?from=header");
	}

	@Test(priority = 2)
	public void mengisiUsername() throws InterruptedException {
		WebElement fieldUsername = driver.findElement(By.id("user_username"));
		Assert.assertTrue(fieldUsername.isDisplayed());
		fieldUsername.clear();

		Utility.username = Utility.ambilUsernameAcak();
		fieldUsername.sendKeys(Utility.username + Keys.TAB);

		Gudang.tungguBeberapaDetik(10);

		WebElement pesanErrorUsername = driver.findElement(By
				.xpath(".//*[@id='new_user']/div[5]/fieldset[1]/div/span"));

		while (pesanErrorUsername.getText() == "sudah digunakan"
				|| pesanErrorUsername.getText().isEmpty() == true) {
			Utility.username = Utility.ambilUsernameAcak();
			fieldUsername.sendKeys(Utility.username);
		Gudang.tungguBeberapaDetik(10);
		}
	}

	@Test(priority = 3)
	public void mengisiPassword() {
		WebElement fieldPassword = driver.findElement(By.id("user_password"));
		Assert.assertTrue(fieldPassword.isDisplayed());
		fieldPassword.clear();
		fieldPassword.sendKeys("lieajalah");
	}

	@Test(priority = 4)
	public void mengisiUlangiPassword() {
		WebElement fieldUlangiPassword = driver.findElement(By
				.id("user_password_confirmation"));
		Assert.assertTrue(fieldUlangiPassword.isDisplayed());
		fieldUlangiPassword.clear();
		fieldUlangiPassword.sendKeys("lieajalah");
	}

	@Test(priority = 5)
	public void mengisiNamaLengkap() throws InterruptedException {
		WebElement fieldNamaLengkap = driver.findElement(By.id("user_name"));
		Assert.assertTrue(fieldNamaLengkap.isDisplayed());
		fieldNamaLengkap.clear();
		fieldNamaLengkap.sendKeys(Utility.ambilNamaAcak());
	}

	@Test(priority = 6)
	public void mengisiEmail() throws InterruptedException {
		WebElement fieldEmail = driver.findElement(By.id("user_email"));
		Assert.assertTrue(fieldEmail.isDisplayed());
		String email = Utility.ambilEmail();
		String alamatEmail = "testingbukalapak+" + Utility.username + "@"
				+ email;
		fieldEmail.clear();
		fieldEmail.sendKeys(alamatEmail);
		Thread.sleep(300);
	}

	@Test(priority = 7)
	public void pilihJenisKelamin() throws InterruptedException {
		WebElement linkAturanPenggunaan = driver.findElement(By
				.linkText("aturan penggunaan"));
		Assert.assertTrue(linkAturanPenggunaan.isDisplayed());
		linkAturanPenggunaan.click();
		Thread.sleep(5000);
		WebElement popUp = driver.findElement(By.id("cboxLoadedContent"));
		Assert.assertTrue(popUp.isDisplayed());
		WebElement popUpTombolTutup = driver.findElement(By.id("cboxClose"));
		Assert.assertTrue(popUpTombolTutup.isDisplayed());
		popUpTombolTutup.click();

		WebElement jenisKelaminLakiLaki = driver.findElement(By
				.id("user_gender_laki-laki"));
		jenisKelaminLakiLaki.click();

		Thread.sleep(300);
	}

	@Test(priority = 8)
	public void menekanTombolDaftarDiHalamanDaftar() {
		WebElement tombolDaftar = driver.findElement(By.name("commit"));
		Assert.assertTrue(tombolDaftar.isDisplayed());
		tombolDaftar.click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.bukalapak.com/users/registered");
	}

	@Test(priority = 9)
	public void menekanTombolSelesai() {
		WebElement tombolSelesai = driver.findElement(By
				.linkText("selesai, dan saya akan cek e-mail saya"));
		Assert.assertTrue(tombolSelesai.isDisplayed());
		tombolSelesai.click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.bukalapak.com/");
	}
}