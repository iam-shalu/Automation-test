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

public class DepositTransaction {

	WebDriver driver;
	WebDriverWait wait;

	public DepositTransaction(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@class='modal-dialog']//button[@type='button'][normalize-space()='Close']")
	WebElement CloseLimit;

	@FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[3]/a/span")
	WebElement transactionsMenu;

	@FindBy(xpath = "//a[@id='submenuTxDropdown']")
	WebElement bnibMenu;

	@FindBy(xpath = "//a[normalize-space()='Deposit Txs']")
	WebElement depositTxsOption;

	@FindBy(xpath = "//select[@id='fieldname1']")
	WebElement searchField;

	@FindBy(xpath = "//input[@id='txtDateRange']")
	WebElement dateRange;

	@FindBy(xpath = "//li[@class='active']")
	WebElement dateLast7days;

	@FindBy(xpath = "//button[@id='btnfiltersearch']")
	WebElement filter;

	@FindBy(xpath = "//button[@id='btnDownloadExcel']")
	WebElement export;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[1]")
	WebElement txId;

	@FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
	WebElement tx_Action;

	@FindBy(xpath = "//h3[normalize-space()='Deposit Txs']")
	WebElement depositTxText;

	@FindBy(xpath = "/html/body/div[2]/div/header/div/div/div/div/h3")
	WebElement viewTxButton;

	@FindBy(xpath = "//input[@id='searchval1']")
	WebElement enterValue;

	@FindBy(xpath = "//a[@href='/BNIBPayout/Transactions']")
	WebElement payoutTxs;

	@FindBy(xpath = "//li[@data-range-key=\"Yesterday\"]")
	WebElement payoutTxdateLast7Days;

	@FindBy(xpath = "//h3[normalize-space()='Payout Tx List']")
	WebElement payoutTxList;
	

	public void interactWithtransactionsDepositTxs() throws IOException {
		try {
			System.out.println("==== Starting Deposit Transactions Test ====");

			// === Handle Merchant Limit Modal if present ===
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("merchantLimitModal")));
			if (CloseLimit.isDisplayed()) {
				CloseLimit.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("merchantLimitModal")));
				
				System.out.println("Merchant limit modal closed.");
			}

			// === Navigate to Deposit Transactions Menu ===
			wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(depositTxsOption)).click();

			// === Date Range Selection ===
			wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
			WebElement Today = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-range-key=\"Yesterday\"]")));
			Today.click();
		
			//Li[@data-range-key="Today"]
			// //li[@data-range-key="Last 7 Days"]
			// /Yesterday - //li[@data-range-key='Yesterday']
			
			

			// === Filter and Export ===
			wait.until(ExpectedConditions.elementToBeClickable(filter)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(export)).click();

			// === Wait for Excel file download ===
			String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

			if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
				CommonUtilis.moveDownloadedFileToDatedFolder("DepositTransactions", dateFolder);
			} else {
				System.err.println(" No downloaded Excel file found to move.");
			}

			// === Wait for loader to disappear ===
			WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(50));
			longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));

			// === Click txId and capture screenshot ===
			wait.until(ExpectedConditions.elementToBeClickable(txId)).click();
			wait.until(ExpectedConditions.visibilityOf(depositTxText));
			CommonUtilis.takeScreenshot(driver, "Deposit Transactions", "DepositTxText");

			// === Scroll to top and click tx action ===
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
			wait.until(ExpectedConditions.elementToBeClickable(tx_Action)).click();

			// === Switch to new window and take screenshot ===
			String originalWindow = driver.getWindowHandle();
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalWindow)) {
					driver.switchTo().window(handle);
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(viewTxButton));
			CommonUtilis.takeScreenshot(driver, "Deposit Transactions", "View_Tx_Details");
			driver.close();
			
			driver.switchTo().window(originalWindow);

			// === Now select Search Field, Filter Type and Enter Value ===
			Select searchDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fieldname1"))));
			searchDropdown.selectByValue("iPayinfo"); // Select Guid

			Select selectFilterType = new Select(
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filtertype1"))));
			selectFilterType.selectByValue("Equals"); // Select Contains

			WebElement enterValueBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchval1")));
			enterValueBox.clear();
			enterValueBox.sendKeys("gomzi001@axl");

			System.out.println("==== Deposit Transactions Test Completed ====");
		} catch (Exception e) {
			System.err.println("Unexpected error occurred: " + e.getMessage());
		}
	}
}
