package bukalapak;

import java.util.Properties;
import java.util.Random;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bukalapak.core.Settings;

public class Gudang extends Settings{

	public static String urlBukalapak = "https://www.bukalapak.com/";
	public static String username;
	public static Random rand = new Random();
	public static int random;

	public static void konfirmasiEmail(String emailBase, WebDriver driver) {
		String urlKonfirmasi, currentURL;
		Properties props = new Properties();
		Session session;
		Store store = null;
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(10000);
			switch (emailBase) {
			case "gmail.com":
				session = Session.getInstance(props, null);
				store = session.getStore();
				store.connect("imap.gmail.com", "testingbukalapak@gmail.com",
						"lieajalah");
				break;
			case "yahoo.com":
				session = Session.getInstance(props, null);
				store = session.getStore();
				store.connect("imap.mail.yahoo.com",
						"testingbukalapak@yahoo.com", "lieajalah");
				break;
			case "yahoo.co.id":
				session = Session.getInstance(props, null);
				store = session.getStore();
				store.connect("imap.mail.yahoo.com",
						"testingbukalapakcoid@yahoo.co.id", "lieajalah");
				break;
			default:
				break;
			}
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			Multipart mp = (Multipart) msg.getContent();
			BodyPart bp = mp.getBodyPart(0);
			String url = bp.getContent().toString();
			urlKonfirmasi = url.substring(url.indexOf("Konfirmasi Email") + 20,
					url.length() - (url.indexOf("Konfirmasi Email"))
							+ (146 - 13));
			driver.get(urlKonfirmasi);
			Thread.sleep(5000);
			currentURL = driver.getCurrentUrl().toString();
			currentURL = currentURL.substring(8, currentURL.length());
			driver.get(currentURL);
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	public static void tungguBeberapaDetik(int detik) {
		detik = detik * 1000;
		try {
			Thread.sleep(detik);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void hapusBarangDariKeranjang(){
		
		// hover menu-history__details
	}

	public static void pilihBarangRandomDariHalamanPencarian() {
		tungguBeberapaDetik(10);
		int Filter_halaman = Integer.parseInt(driver.findElement(
				By.xpath(".//*[@id='search_per_page_chzn']/a")).getText());
			random = rand.nextInt(Filter_halaman-1)+1;
		driver.findElement(
				By.xpath("//div[@id='layout']/div[4]/div/div/div/ul/li["+random+"]/article/div/a/img")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains(
				"https://www.bukalapak.com/p/"));
		tungguBeberapaDetik(3);
	}

	public static void tekanTombolKeranjang() {
		WebElement tombolKeranjang = driver.findElement(By
				.xpath("//a[contains(text(),'Keranjang Belanja')]"));
		tombolKeranjang.click();
		tungguBeberapaDetik(3);
	}

	public static void klikLihatKeranjang() {
		tungguBeberapaDetik(3);
		WebElement linkLihatKeranjang = driver.findElement(By
				.linkText("Lihat keranjang"));
		Assert.assertTrue(linkLihatKeranjang.isDisplayed());
		linkLihatKeranjang.click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.bukalapak.com/cart/carts");
	}

	public static void cekKeranjangKosong1() {
		tekanTombolKeranjang();
		Gudang.tungguBeberapaDetik(4);
		WebElement notifKeranjangKosong = driver.findElement(By
				.xpath("//div[contains(@class,'menu-history__empty')]"));
		Assert.assertEquals(notifKeranjangKosong.getText(),
				"Belum ada barang ditambahkan");
	}
	
	public static void cekKeranjangKosong() {
		tekanTombolKeranjang();
		Gudang.tungguBeberapaDetik(4);
		WebElement notifKeranjangKosong = driver.findElement(By
				.xpath("//div[contains(@class,'dropdown__item__empty')]"));
		Assert.assertEquals(notifKeranjangKosong.getText(),
				"Belum ada barang ditambahkan");
	}

	public static void cekHalamanKeranjangKosong() {
		klikLihatKeranjang();
		WebElement notifKeranjangKosong = driver.findElement(By
				.xpath("//div[contains(@class,'cart-content-empty')]//p//strong"));
		Assert.assertTrue(notifKeranjangKosong.getText().contains(
				"Belum ada barang yang masuk di keranjang belanja."));
	}

	public static void cariBarangdiSearchBarDenganFieldKosong() {
		WebElement fieldCariBarang = driver.findElement(By
				.id("search_keywords"));
		Assert.assertTrue(fieldCariBarang.isDisplayed());
		fieldCariBarang.clear();
		fieldCariBarang.sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.getCurrentUrl().contains(
				"https://www.bukalapak.com/products?"));
		tungguBeberapaDetik(5);
	}

	public static void tekanTombolMyLapak() {
		WebElement tombolMyLapak = driver.findElement(By.xpath("//a[contains(@href,'my_lapak')]"));
		Assert.assertTrue(tombolMyLapak.isDisplayed());
		tombolMyLapak.click();
		tungguBeberapaDetik(3);
	}

	public static void tekanTombolPengaturan() {
		WebElement tombolPengaturan = driver.findElement(By
				.xpath("//a[contains(text(),'Pengaturan')]"));
		//Assert.assertTrue(tombolPengaturan.isDisplayed());
		tombolPengaturan.click();
		tungguBeberapaDetik(2);
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.bukalapak.com/account_settings?from=dropdown");
	}
	
	public static void tekanTombolLogout() {
		WebElement tombolPengaturan = driver.findElement(By
				.xpath("//a[contains(text(),'Logout')]"));
		//Assert.assertTrue(tombolPengaturan.isDisplayed());
		tombolPengaturan.click();
		tungguBeberapaDetik(2);
	}

	public static void tekanTombolEdit() {
		WebElement tombolEdit = driver.findElement(By.linkText("edit"));
		Assert.assertTrue(tombolEdit.isDisplayed());
		tombolEdit.click();
		tungguBeberapaDetik(2);
		Assert.assertTrue(driver.getCurrentUrl().toString()
				.contains("https://www.bukalapak.com/users/"));
	}

	public static void isiDeskripsi() {
		WebElement fieldDeskripsi = driver.findElement(By
				.id("user_description"));
		String Deskripsi = ambilDeskripsiRandom();
		Assert.assertTrue(fieldDeskripsi.isDisplayed());
		fieldDeskripsi.clear();
		fieldDeskripsi.sendKeys(Deskripsi);
	}

	private static String ambilDeskripsiRandom() {
		String[] Deskripsi = { "Toko Paling Jaya Sedunia",
				"Toko Paling Laris Sejagat Raya", "Toko Serba Ada Online",
				"Toko Serba Jaya", "Toko Abadi Makmur",
				"Prosperous Victorious", "Victorious Eternal" };
		int random = rand.nextInt(Deskripsi.length);
		return Deskripsi[random];
	}

	public static void isiNama() {
		WebElement fieldName = driver.findElement(By.id("user_name"));
		String Nama = "Raga Pinilih";
		Assert.assertTrue(fieldName.isDisplayed());
		fieldName.clear();
		fieldName.sendKeys(Nama);
	}

	public static void memilihJenisKelaminLakiLaki() {
		WebElement radioButtonLakiLaki = driver.findElement(By.xpath("//input[contains(@id,'user_gender_laki-laki')]"));
		radioButtonLakiLaki.click();
	}

	public static void memilihTanggalLahir(int tanggal, int bulan, int tahun) {
		tanggal -= 1;
		bulan -= 1;
		tahun -= 1925;

		WebElement dropDownTanggal = driver.findElement(By
				.xpath(".//*[@id='user_birthday_3i_chzn']/a/span"));
		Assert.assertTrue(dropDownTanggal.isDisplayed());
		dropDownTanggal.click();
		tungguBeberapaDetik(3);
		driver.findElement(
				By.xpath(".//*[@id='user_birthday_3i_chzn_o_" + tanggal + "']"))
				.click();

		WebElement dropDownBulan = driver.findElement(By
				.xpath(".//*[@id='user_birthday_2i_chzn']/a/span"));
		Assert.assertTrue(dropDownBulan.isDisplayed());
		dropDownBulan.click();
		tungguBeberapaDetik(3);
		driver.findElement(
				By.xpath(".//*[@id='user_birthday_2i_chzn_o_" + bulan + "']"))
				.click();

		WebElement dropDownTahun = driver.findElement(By
				.xpath(".//*[@id='user_birthday_1i_chzn']/a/span"));
		Assert.assertTrue(dropDownTahun.isDisplayed());
		dropDownTahun.click();
		tungguBeberapaDetik(3);
		driver.findElement(
				By.xpath(".//*[@id='user_birthday_1i_chzn_o_" + tahun + "']"))
				.click();
	}

	public static void isiFieldNomorTelepon(String no_telepon) {
		WebElement fieldNomorTelepon = driver.findElement(By.id("user_phone"));
		Assert.assertTrue(fieldNomorTelepon.isDisplayed());
		fieldNomorTelepon.clear();
		fieldNomorTelepon.sendKeys(no_telepon);
	}

	public static void PilihProvinsiJawaBarat() {
		WebElement dropDownProvinsi = driver.findElement(By.xpath(".//*[@id='user_address_attributes_province_chzn']/a/span"));
		Assert.assertTrue(dropDownProvinsi.isDisplayed());
		dropDownProvinsi.click();
		tungguBeberapaDetik(3);
		driver.findElement(By.xpath(".//*[@id='user_address_attributes_province_chzn_o_8']")).click();
	}

	public static void pilihKabupatenBogor() {
		WebElement dropDownKotaKabupaten = driver.findElement(By.xpath(".//*[@id='user_address_attributes_city_chzn']/a/span"));
		Assert.assertTrue(dropDownKotaKabupaten.isDisplayed());
		dropDownKotaKabupaten.click();
		tungguBeberapaDetik(3);
		driver.findElement(By.xpath(".//*[@id='user_address_attributes_city_chzn_o_8']")).click();
	}

	public static void pilihKecamatanBogor() {
		WebElement dropDownKecamatan = driver.findElement(By.xpath(".//*[@id='user_address_attributes_area_chzn']/a/span"));
		Assert.assertTrue(dropDownKecamatan.isDisplayed());
		dropDownKecamatan.click();
		tungguBeberapaDetik(3);
		driver.findElement(By.xpath(".//*[@id='user_address_attributes_area_chzn_o_17']")).click();		
	}
}
