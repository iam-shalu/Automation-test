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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class ChargebackTxReport {
	WebDriver driver;
	WebDriverWait wait;

	public ChargebackTxReport(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@class='modal-dialog']//button[@type='button'][normalize-space()='Close']")
	WebElement CloseLimit;

	@FindBy(xpath = "//span[normalize-space()='Transactions']")
	WebElement transactionsMenu;

	@FindBy(xpath = "//a[@id='submenuTxDropdown']")
	WebElement bnibMenu;

	@FindBy(xpath = "//a[normalize-space()='ChargeBack Txs Report']")
	WebElement chargebackTxReport;
	
	@FindBy(xpath = "(//li[@data-range-key=\"Yesterday\"])[1]")
	WebElement createdDateRangeyesterday;
	
	@FindBy(xpath = "(//li[@data-range-key=\"Yesterday\"])[2]")
	WebElement cbOpenDateRange;
	
	@FindBy(xpath = "(//li[@data-range-key=\"Yesterday\"])[3]")
	WebElement cbcloseDateRangeYesterday;
	
	@FindBy(xpath = "(//button[@title=\"Select Any\"])[1]")
	WebElement masterMerchantSelectAny;
	
	@FindBy(xpath = "(//input[@placeholder=\"Search\"])[1]")
	WebElement SearchMasterMerchant;
	
	@FindBy(xpath = "//label[contains(normalize-space(.), 'Test-Acs-01')]")
	WebElement selectTestacs01;
	
	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	WebElement merchantSelectAny2;
	
	@FindBy(xpath = "(//input[@placeholder=\"Search\"])[2]")
	WebElement searchMasterMerchant2;
	
	@FindBy(xpath = "(//label[contains(normalize-space(.), 'Test-Acs-01')])[3]")
	WebElement masterselectTestacs01;
	
	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[3]")
	WebElement subMerchantSelectAny;
	
	@FindBy(xpath = "(//input[@placeholder=\"Search\"])[3]")
	WebElement searchSubMerchant;
	
	@FindBy(xpath = "(//label[contains(normalize-space(.), 'Test-Acs-01')])[4]")
	WebElement submerchantTestacs01;
	
	@FindBy(id = "ddlCompleted")
	WebElement isClosedDropdown;
	
	public void interactWithtransactionchargebackTX() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(chargebackTxReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCreatedDateRange"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(createdDateRangeyesterday)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCBOpenDateRange"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(cbOpenDateRange)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCBCloseDateRange"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(cbcloseDateRangeYesterday)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownloadExcel"))).click();
		
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("ChargebackTxReport", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantSelectAny)).click();
		wait.until(ExpectedConditions.elementToBeClickable(SearchMasterMerchant)).sendKeys("Test-acs-01");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(selectTestacs01)).click();
		wait.until(ExpectedConditions.elementToBeClickable(merchantSelectAny2)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant2)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(masterselectTestacs01)).click();
		wait.until(ExpectedConditions.elementToBeClickable(subMerchantSelectAny)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchSubMerchant)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(submerchantTestacs01)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ddlCompleted"))).click();
		Select isClosed = new Select(isClosedDropdown);
		isClosed.selectByVisibleText("No");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch"))).click();
		
		String screenshotName = "ChargebackTxReport_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "Transaction-ChargebackTxReport", screenshotName);
		
		Thread.sleep(3000);
		
			
	}
}
