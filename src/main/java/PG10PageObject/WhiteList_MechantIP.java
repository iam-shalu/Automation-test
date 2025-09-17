package PG10PageObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class WhiteList_MechantIP {
	WebDriver driver;
	WebDriverWait wait;

	public WhiteList_MechantIP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Page Elements
	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='White List Merchant IP']")
	WebElement whiteListMerchantIp;

	@FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
	WebElement sMasterMerchant;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	WebElement searchWhiteListMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement Testacs01;

	@FindBy(xpath = "//a[normalize-space()='Add Whitelist Merchant IP']")
	WebElement AddWhiteListMerchantIp;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[3]")
	WebElement AddWhiteListMSelectAny;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
	WebElement AddWhiteListTestACS;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement AddWhiteListSelectTestAcs;

	@FindBy(xpath = "//button[@title='Select Any']")
	WebElement addWhiteLiStselectMasterMerchant;

	@FindBy(xpath = "//*[@id=\"frmWhiteListCustomer\"]/div[1]/div/div[1]/div/div/div/ul/li[1]/div/input")
	WebElement searchTestAcs;

	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div[2]/form/div[1]/div/div[1]/div/div/div/ul/li[898]/a/label")
	WebElement selectTestAcs01;

	@FindBy(xpath = "//div[@class='gutters row']//button[@title='Select Master Merchant']")
	WebElement sMasterMerchant2;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@placeholder='Search']")
	WebElement searchWhiteListMasterMerchant2;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement Testacs03;

	@FindBy(xpath = "//h3[text()='White list Master Merchant IP Details']")
	WebElement WhitelistMasterMerchantIPDetails;

	public void interactWithfraudControlwhiteListMerchIP() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(whiteListMerchantIp)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant)).sendKeys("Test-acs-01");
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();
		Thread.sleep(2000);

		driver.manage().window().maximize();
		waitForPageLoad();

		WebElement WhiteListMerchantIPUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
		String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\WhiteList Merchant Ip\\WhiteList_MerchantIP.xlsx";
		WhiteListMerchantIPUpload.sendKeys(filePath);
		waitForUploadComplete();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListMerchantIp)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListMSelectAny)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListTestACS)).sendKeys("Test-acs-01");
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListSelectTestAcs)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("IP"))).sendKeys("1.5.7.8");
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActive"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant2)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant2)).sendKeys("Test-acs-01");
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(Testacs03)).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(2000);

		String screenshotName = "WhitelistMasterMerchantIP_Page_Screenshot";
		CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-WhitelistMasterMerchantIP", screenshotName);
		Thread.sleep(2000);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
		Thread.sleep(2000);

		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("WhiteListMerchantIP", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		Thread.sleep(2000);

		// 🔥 Delete all records with IP "1.5.7.8"
		deleteAllMatchingIPs("1.5.7.8");
	}

	// Helper method: delete all rows matching an IP
	private void deleteAllMatchingIPs(String ip) {
		while (true) {
			// Search by IP
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys(ip);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();

			// Find delete icons
			List<WebElement> deleteIcons = driver.findElements(
					By.xpath("//table[@id='datatable']//tbody//tr//td[last()]//span[contains(@class,'fa-trash-o')]"));

			if (deleteIcons.isEmpty()) {
				System.out.println("✅ No more records found for IP: " + ip);
				break;
			}

			// Delete first record
			WebElement deleteIcon = deleteIcons.get(0);
			wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

			// Accept alert
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();

			// Wait until row disappears
			wait.until(ExpectedConditions.stalenessOf(deleteIcon));
		}
	}

	private void waitForPageLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	private void waitForUploadComplete() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
