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

public class WhiteListCustomer {
	WebDriver driver;
	WebDriverWait wait;

	public WhiteListCustomer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='White List Customer']")
	WebElement whiteListCustomer;

	@FindBy(xpath = "//button[@title='Select Master Merchant']")
	WebElement smasterMerchantDropdown;
	
	//x path For Production
	@FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
	WebElement smasterMerchantDropdown_Live;

	@FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
	WebElement searchSelectMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement selectWhiteListMasterMerchant;
	
	@FindBy(xpath = "//a[@class=\"btn btn-info btn-sm\"]")
	WebElement addWhiteListCust;

	@FindBy(xpath = "//h3[normalize-space()='White list Customer Details']")
	WebElement whiteListCustomerText;

	@FindBy(xpath = "//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")
	WebElement selectAny;

	@FindBy(xpath = "//input[@placeholder=\"Search\"]")
	WebElement searchWhiteList;

	@FindBy(xpath = "//label[normalize-space()='Test-Acs-01']")
	WebElement Testacs;

	@FindBy(xpath = "(//button[@title=\"Select Master Merchant\"])[2]")
	WebElement smasterMerchantDropdown2;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	WebElement searchWhiteList2;
	
	//X Path For UAT
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	WebElement Testacs2;
	
	//X Path For Production 
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	WebElement Testacs2_Live;
	
	@FindBy(xpath = "//td[normalize-space()='6847']")
	WebElement Id;

	@FindBy(xpath = "//span[contains(@class,'dtr-data')]//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]")
	WebElement delete;

	@FindBy(xpath = "(//span[@class=\"fa fa-trash-o  fa-lg\"])[1]")
	WebElement delete1;

	@FindBy(xpath = "//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]")
	WebElement delete2;

	public void interactWithfraudControl_whiteListCustomer() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(whiteListCustomer)).click();
		wait.until(ExpectedConditions.elementToBeClickable(smasterMerchantDropdown)).click();
		wait.until(ExpectedConditions.visibilityOf(searchSelectMasterMerchant)).sendKeys("Test-Acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(selectWhiteListMasterMerchant)).click();
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileInput")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"fileInput\"]")));

		try {
			WebElement whiteListCustomerUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
		//	String filePath = "D:\\Automation\\Excel file\\Whitelist Customer\\WhiteList.xlsx";
			String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\Whitelist Customer\\WhiteList.xlsx";
			whiteListCustomerUpload.sendKeys(filePath);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader"))); // Optional loader
			System.out.println("File uploaded successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWhiteListCust)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAny)).click();
		wait.until(ExpectedConditions.visibilityOf(searchWhiteList)).sendKeys("Test-acs-01");
		
		
		  wait.until(ExpectedConditions.elementToBeClickable(Testacs)).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName"))).sendKeys("Akash");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("LastName"))).sendKeys("Lade");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys("akash@gmail.com");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("Phone"))).sendKeys("9632629036");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("IP"))).sendKeys("1.1.1.3");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActive"))).click(); 
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click(); 
		  Thread.sleep(3000);
		  wait.until(ExpectedConditions.elementToBeClickable(smasterMerchantDropdown2)).click();
		  wait.until(ExpectedConditions.visibilityOf(searchWhiteList2)).sendKeys("Test-acs-01");
		  wait.until(ExpectedConditions.elementToBeClickable(Testacs2)).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Akash");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click
		  ();
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click(); 
		  Thread.sleep(3000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click(); 
		  String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new
		  Date()); 
		  String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
		  
		  if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
		  CommonUtilis.moveDownloadedFileToDatedFolder("WhiteListCustomer",
		  dateFolder); } else {
		  System.err.println(" No downloaded Excel file found to move."); }
		  Thread.sleep(3000); 
		  String screenshotName ="WhiteListCustomer_Page_Screenshot";
		  System.out.println("Capturing full page screenshot...");
		  CommonUtilis.captureFullPageScreenshot(driver,"FraudControl-WhiteListCustomer", screenshotName); 
	
		  try { 
				
			// First Delete
			  wait.until(ExpectedConditions.elementToBeClickable(delete1)).click();
			  wait.until(ExpectedConditions.alertIsPresent());
			  driver.switchTo().alert().accept();

			  // Re-locate the delete button again after table refresh
			  By deleteLocator = By.xpath("//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]");
			  WebElement deleteButton2 = wait.until(ExpectedConditions.elementToBeClickable(deleteLocator));
			  deleteButton2.click();
			  wait.until(ExpectedConditions.alertIsPresent());
			  driver.switchTo().alert().accept();
			  
			  
		  Thread.sleep(3000); // Scroll To Top
		  ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		 
		  } 
		  catch (Exception e) { 
			  
		  try { WebElement fallbackElement= wait.until(ExpectedConditions .elementToBeClickable(By.xpath("//td[@class='dt-type-numeric sorting_1 dtr-control']")));
		  fallbackElement.click(); 
		  WebElement fallbackElementdelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"fa fa-trash-o  fa-lg\"])[2]"))); 
		  fallbackElementdelete.click();
		  
		  wait.until(ExpectedConditions.alertIsPresent());
		  driver.switchTo().alert().accept();
		  
		  WebElement fallbackElement2 = wait.until(ExpectedConditions
		  .elementToBeClickable(By.xpath("//td[@class='dt-type-numeric sorting_1 dtr-control']")));
		  fallbackElement2.click();
		  
		  WebElement fallbackElementdelete2 =
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		  "//span[contains(@class,'dtr-data')]//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]"))); 
		  
		  fallbackElementdelete2.click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  driver.switchTo().alert().accept(); 
		  Thread.sleep(3000); 
		  // Scroll To Top
		  ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		  Thread.sleep(3000); 
		  } 
		  catch (Exception innerException) {
		  System.out.println("Both primary and fallback delete actions failed: " +
		  innerException.getMessage()); 
		  } }
		 
		  
		  
		  
	}
}
