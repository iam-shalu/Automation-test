package PG10PageObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class VPABlackList {
	WebDriver driver;
	WebDriverWait wait;

	public VPABlackList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='VPA BlackList']")
	WebElement VpaBlackList;

	@FindBy(xpath = "//a[normalize-space()='Add VPABlackList User']")
	WebElement AddVPABlackListUser;

	@FindBy(xpath = "//h3[normalize-space()='VPA BlackList Details']")
	WebElement VpaBlackListDetailsText;

	@FindBy(xpath = "//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")
	WebElement selectAny;

	@FindBy(xpath = "//input[@class='form-control multiselect-search']")
	WebElement searchselectAny;

	@FindBy(xpath = "//label[normalize-space()='FULL']")
	WebElement Full;

	@FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
	WebElement deleteRecord;

	public void interactWithfraudControlVPABlackList() throws IOException, InterruptedException {

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(VpaBlackList)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//input[@class='form-control form-control-sm'])[1]")));
		WebElement blackListUpload = driver
				.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]"));
	//	String filePath = "D:\\Automation\\Excel file\\VPA BlackList\\VPABlackList.xlsx";
		String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\VPA BlackList\\VPABlackList.xlsx";
		blackListUpload.sendKeys(filePath);
		System.out.println("File uploaded successfully.");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnimport"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(AddVPABlackListUser)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(selectAny)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(searchselectAny)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Full)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VPA"))).sendKeys("4123499@paytm");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Save"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("4123466@paytm");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		String screenshotName = "VpaBlackList_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-VPA BlackList", screenshotName);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnExport"))).click();
		Thread.sleep(3000);
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("VPABlackList", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
	}
}
