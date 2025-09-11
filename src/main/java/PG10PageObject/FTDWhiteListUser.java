package PG10PageObject;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class FTDWhiteListUser {

	WebDriver driver;
	WebDriverWait wait;

	public FTDWhiteListUser(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Page Elements

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "//a[normalize-space()='FTD White List User']")
	WebElement ftdWhiteList;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	WebElement selectMasterMerchant;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	WebElement searchMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	WebElement Testacs01;
	
	@FindBy(xpath = "//a[normalize-space()='Add FTDWhiteList User']")
	WebElement addWhiteListUser;

	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[3]")
	WebElement addWhiteListUserMasterMerchant;

	@FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
	WebElement searchaddWhiteListUserMasterMerchant;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	WebElement Testacs013;

	@FindBy(xpath = "(//button[@class=\"btn btn-success\"])[1]")
	WebElement close;

	@FindBy(xpath = "//div[@class='gutters row']//button[@title='Select Master Merchant']")
	WebElement selectMasterMerchant2;

	@FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	WebElement searchMasterMerchant2;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	WebElement Testacs0135;

	@FindBy(xpath = "//h3[normalize-space()='FTD White list User Details']")
	WebElement FTDWhiteListUser;

	@FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
	WebElement deleteRecord;

	@FindBy(xpath = "/span[@class=\"fa fa-trash-o fa-lg\"]")
	WebElement deleteRecord2;

	public void interactWithfraudControl_FTDwhiteListUser() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(ftdWhiteList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectMasterMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"fileInput\"]")));
		WebElement blackListUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
		String filePath = "D:\\Automation\\Excel file\\FTDWhiteList User\\FTDWhiteListUser.xlsx";
		blackListUpload.sendKeys(filePath);
		System.out.println("File uploaded successfully.");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWhiteListUser)).click(); 
		wait.until(ExpectedConditions.elementToBeClickable(addWhiteListUserMasterMerchant)).click(); 
		wait.until(ExpectedConditions.elementToBeClickable(searchaddWhiteListUserMasterMerchant)) .sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs013)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys("Akash");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys("Lade");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys("akash@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Phone"))).sendKeys("9632629063");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_Save"))).click();
		Thread.sleep(3000);
	    wait.until(ExpectedConditions.elementToBeClickable(selectMasterMerchant2)).click(); 
	    wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant2)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs0135)).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();  
		String screenshotName="FTDWhiteListUser_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver,"FraudControl-FTDWhiteListUser", screenshotName); // Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("9632629063");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click(); 
	    Thread.sleep(3000);
	   wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click(); 
	    wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept(); 
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click(); 
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("9632629033"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click(); 
		  wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  driver.switchTo().alert().accept();
		  
		 
	}
}
