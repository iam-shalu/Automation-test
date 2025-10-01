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

public class CityBlackList {
	WebDriver driver;
	WebDriverWait wait;

	public CityBlackList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='City BlackList']")
	WebElement cityBlackList;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	WebElement SubMastermerchant1;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	WebElement searchMasterMerchant1;
	
	//X Path For UAT 
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
	WebElement Testacs1;

	@FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
	WebElement addcityBlackList;

	@FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]")
	WebElement selectSubMerchant2;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
	WebElement searchSubTestacs2;
	
	//x Path For UAT
	@FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
	WebElement selectsubTestacs2;

	@FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[2]")
	WebElement SubMastermerchant3;

	@FindBy(xpath = "//li[@class='multiselect-item multiselect-all active']//label[@class='checkbox'][normalize-space()='Select all']")
	WebElement selectAllbtn3;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	WebElement searchSubMastermerchant3;
	
	// X Path For UAT 
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
	WebElement Testacs3;

	@FindBy(xpath = "//h3[normalize-space()='City BlackList Details']")
	WebElement cityBlackListDetails;

	@FindBy(xpath = "//span[@class=\"fa fa-trash-o fa-lg\"]")
	WebElement deleteRecord;

	public void interactWithfraudControlblackList() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(cityBlackList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant1)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant1)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs1)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));
		WebElement blackListUpload = driver.findElement(By.xpath("//input[@id='fileInput']"));
	//	String filePath = "D:\\Automation\\Excel file\\CityBlackList Customer\\CityBlackList.xlsx";
		String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\CityBlackList Customer\\CityBlackList.xlsx";
		
		blackListUpload.sendKeys(filePath);
		System.out.println("File uploaded successfully.");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnimport"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addcityBlackList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectSubMerchant2)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchSubTestacs2)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(selectsubTestacs2)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("City"))).sendKeys("Surat");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Save"))).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant3)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAllbtn3)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchSubMastermerchant3)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs3)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
		String screenshotName = "CityBlackListDetails_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-City BlackList", screenshotName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Surat");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnExport"))).click();
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
		
		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("CityBlackList", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant3)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAllbtn3)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchSubMastermerchant3)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs3)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Bangalore");
		wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
	}
	
}

