package PG10PageObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10Base.PG10Base;
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

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	WebElement sMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@placeholder='Search']")
	WebElement searchWhiteListMasterMerchant;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/form/div/div[1]/div/div/ul/li[898]/a/label")
	WebElement Testacs01;

	@FindBy(xpath = "//a[normalize-space()='Add Whitelist Merchant IP']")
	WebElement AddWhiteListMerchantIp;

	@FindBy(xpath = "//button[@title='Select Any']")
	WebElement addWhiteLiStselectMasterMerchant;

	@FindBy(xpath = "//*[@id=\"frmWhiteListCustomer\"]/div[1]/div/div[1]/div/div/div/ul/li[1]/div/input")
	WebElement searchTestAcs;

	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div[2]/form/div[1]/div/div[1]/div/div/div/ul/li[898]/a/label")
	WebElement selectTestAcs01;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	WebElement sMasterMerchant2;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@placeholder='Search']")
	WebElement searchWhiteListMasterMerchant2;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/ul/li[898]/a/label")
	WebElement Testacs03;

	@FindBy(xpath = "//h3[text()='White list Master Merchant IP Details']")
	WebElement WhitelistMasterMerchantIPDetails;

	@FindBy(xpath = "//tbody/tr[1]/td[9]/a[2]/span[1]")
	WebElement deleteIp;

	public void interactWithfraudControlwhiteListMerchIP() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(whiteListMerchantIp)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();
		Thread.sleep(3000);
		driver.manage().window().maximize();
		waitForPageLoad();
		WebElement WhiteListMerchantIPUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
		String filePath = "D:\\Automation\\Excel file\\WhiteList Merchant Ip\\WhiteList_MerchantIP.xlsx";
		WhiteListMerchantIPUpload.sendKeys(filePath);
		waitForUploadComplete();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListMerchantIp)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(addWhiteLiStselectMasterMerchant)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(searchTestAcs)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(selectTestAcs01)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IP"))).sendKeys("1.5.7.8");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActive"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant2)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant2)).sendKeys("Test-acs-01");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Testacs03)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		String screenshotName = "WhitelistMasterMerchantIP_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-WhitelistMasterMerchantIP", screenshotName);
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
		Thread.sleep(3000);
	
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("WhiteListMerchantIP", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1.5.7.8");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteIp)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1.5.7.8");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteIp)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
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
