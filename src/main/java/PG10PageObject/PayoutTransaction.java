package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import PG10utils.CommonUtilis;

public class PayoutTransaction {
	WebDriver driver;
	WebDriverWait wait;

	public PayoutTransaction(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@class='modal-dialog']//button[@type='button'][normalize-space()='Close']")
	WebElement CloseLimit;

	@FindBy(xpath = "(//span[@class=\"nav-item\"])[3]")
	WebElement transactionsMenu;

	@FindBy(xpath = "//a[@id='submenuTxDropdown']")
	WebElement bnibMenu;

	@FindBy(xpath = "//a[normalize-space()='Deposit Txs']")
	WebElement depositTxsOption;

	@FindBy(xpath = "//select[@id='fieldname1']")
	WebElement searchField;

	@FindBy(xpath = "//input[@id='txtDateRange']")
	WebElement dateRange;
	
	//UAT 
	@FindBy(xpath = "//li[@class='active']")
	WebElement dateLast7days;
	
	//Production
	@FindBy(xpath = "//Li[@data-range-key=\"Yesterday\"]")
	WebElement Yesterday;	
	
	// For Production
	@FindBy(xpath = "(//td[@class=\"dt-type-numeric sorting_1 dtr-control\"])[1]")
	WebElement PayoutTxYesterday;
	
	@FindBy(xpath = "//button[@id='btnfiltersearch']")
	WebElement filter;

	@FindBy(xpath = "//button[@id='btnDownloadExcel']")
	WebElement export;
	
	//For UAT
	@FindBy(xpath = "(//td[@class=\"dt-type-numeric sorting_1 dtr-control\"])[1]")
	WebElement PayoutId;
	
	//For Live 
	@FindBy(xpath = "(//td[@class=\"dt-type-numeric sorting_1 dtr-control\"])[1]")
	WebElement PayoutTxId;

	@FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
	WebElement tx_Action;

	@FindBy(xpath = "/html/body/div[2]/div/header/div/div/div/div/h3")
	WebElement viewTxButton;

	@FindBy(xpath = "//input[@id='searchval1']")
	WebElement enterValue;

	@FindBy(xpath = "//a[@href='/BNIBPayout/Transactions']")
	WebElement payoutTxs;
	
	// For UAT 
	@FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
	WebElement payoutTxdateLast7Days;
	
	@FindBy(xpath = "//h3[normalize-space()='Payout Tx List']")
	WebElement payoutTxList;
	
	@FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
	WebElement showDetails;
	
	@FindBy(xpath = "(//h4[@class=\"modal-title\"])[1]")
	WebElement PayoutCollection;
	
	@FindBy(xpath = "(//button[@class=\"btn btn-default waves-effect\"])[1]")
	WebElement close;
	
	public void interactWithtransactionPayoutTxs() throws IOException {
		try {
		//	System.out.println("==== Starting Payout Transactions Test ====");
			Thread.sleep(3000);
//			wait.until(ExpectedConditions.or(ExpectedConditions.invisibilityOfElementLocated(By.id("merchantLimitModal")),
//			ExpectedConditions.not(ExpectedConditions.attributeContains(By.id("merchantLimitModal"),"style", "display: block"))));
			
			
			
			wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(payoutTxs)).click();
			wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
			//For UAT
			
	//		wait.until(ExpectedConditions.elementToBeClickable(payoutTxdateLast7Days)).click();
			
			//For Production
			wait.until(ExpectedConditions.elementToBeClickable(Yesterday)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ddlCutOffTime"))).click();
			WebElement cutoffTimeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlCutOffTime")));
			
			Select timeSelect = new Select(cutoffTimeDropdown);
			// Select the option by visible text "12 AM to 12 AM"
			timeSelect.selectByVisibleText("12 AM to 12 AM");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("frmsearch"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
			
			// Wait and move Excel
			String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

			if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
				CommonUtilis.moveDownloadedFileToDatedFolder("PayoutTransaction", dateFolder);
			} else {
				System.err.println(" No downloaded Excel file found to move.");
			}
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("dt-length-1"))).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(PayoutTxId)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(PayoutTxId)).click();
			
			Thread.sleep(3000);
			CommonUtilis.takeScreenshot(driver, "Payout Transactions", "Payout_Tx_List");
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
			
			wait.until(ExpectedConditions.elementToBeClickable(showDetails)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(close)).click();
			
			System.out.println("==== Payout Transactions Test Completed ====");

		} catch (Exception e) {
			System.err.println(" Unexpected error in Payout Transaction flow: " + e.getMessage());
		}
	}
}