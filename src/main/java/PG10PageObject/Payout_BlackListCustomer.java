
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
import PG10utils.CommonUtilis;

public class Payout_BlackListCustomer {
	WebDriver driver;
	WebDriverWait wait;

	public Payout_BlackListCustomer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='BlackList Payout Customer']")
	WebElement payoutblackListCustomer;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	WebElement sMasterMerchant;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	WebElement sSearchMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement Testacs01;

	@FindBy(xpath = "(//button[@class=\"btn btn-info btn-sm\"])[1]")
	WebElement addPayoutBlackList;

	@FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[3]")
	WebElement addsMasterMerchant;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
	WebElement sSearchMasterMerchant3;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement selectTestacs013;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	WebElement smasterMerchant3;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	WebElement sMasterMerchant2;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	WebElement searchMasterMerchant2;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement TestacsMasterMerch03;

	@FindBy(xpath = "//h3[normalize-space()='Payout BlackList Customers']")
	WebElement PayoutblackListCustomerText;

	@FindBy(xpath = "(//input[@class='form-control form-control-sm'])[2]")
	WebElement searchByIP;

	@FindBy(xpath = "//i[@class='fa fa-trash-o fa-lg']")
	WebElement deleteRecord;


	  public void interactWithfraudControlPayoutblackListCust() throws
	  InterruptedException, IOException {
	  
	  Thread.sleep(3000);
	  
	  wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click()
	  ;
	  wait.until(ExpectedConditions.elementToBeClickable(payoutblackListCustomer)).
	  click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant)).click();
	  wait.until(ExpectedConditions.elementToBeClickable(sSearchMasterMerchant)).
	  sendKeys("Test-acs-01");
	  wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();
	  
	  
	  Thread.sleep(3000);
	  
	  try { driver.manage().window().maximize();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));
	  WebElement blackListUpload = driver.findElement(By.xpath("//input[@id='fileInput']")); 
	   String filePath= "D:\\Automation\\pg10-automation\\Upload Excel File\\PayoutBlackList Customer\\BlacklistPayoutCustomer.xlsx"; 
	  
	   blackListUpload.sendKeys(filePath);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
	  "//div[@class='loader']"))); // hypothetical // loader // wait
	  System.out.println("File uploaded successfully."); 
	  } 
	  catch (Exception e) {
		  
	  e.printStackTrace(); }
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(addPayoutBlackList)).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(addsMasterMerchant)).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(sSearchMasterMerchant3)).sendKeys("Test-acs-01");
	  
	  wait.until(ExpectedConditions.elementToBeClickable(selectTestacs013)).click(); 
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("AccountNo"))).sendKeys("1234234346789");
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("BankIFSC"))).sendKeys("ICICI008");
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant2)).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant2)).sendKeys("Test-acs-01");
	  
	  wait.until(ExpectedConditions.elementToBeClickable(TestacsMasterMerch03)).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click
	  ();
	  
	  String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //
	  String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
	  
	  if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
	  CommonUtilis.moveDownloadedFileToDatedFolder("PayoutBlackListTransaction",
	  dateFolder); } else {
	  System.err.println(" No downloaded Excel file found to move."); }
	  
	  String screenshotName = "PayoutblackList_Page_Screenshot";
	  System.out.println("Capturing full page screenshot...");
	  CommonUtilis.captureFullPageScreenshot(driver,
	  "FraudControl-PayoutBlackList", screenshotName);
	  
	  ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1234234346789");
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	  
	  Thread.sleep(1000);
	  
	  
	  wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	  wait.until(ExpectedConditions.alertIsPresent());
	  driver.switchTo().alert().accept();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1234234346796");
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	  
	  Thread.sleep(1000);
	 
	 wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	 wait.until(ExpectedConditions.alertIsPresent());
	 driver.switchTo().alert().accept();
		 
	 ((JavascriptExecutor) driver).executeScript("window.scroll({ top: 0, behavior: 'smooth' });");

	  
	  }
	  
}
	 
	
	
		 
	

		  
		  
		  
		  
		  




