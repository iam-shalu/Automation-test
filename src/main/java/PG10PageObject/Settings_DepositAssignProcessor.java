package PG10PageObject;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class Settings_DepositAssignProcessor {
	WebDriver driver;
	WebDriverWait wait;

	public Settings_DepositAssignProcessor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(xpath = "(//span[@class=\"nav-item\"])[7]")
	WebElement settings;
	
	@FindBy(xpath = "//a[contains(text(),'Deposit - Assign Processor & Set Merchant Wise Pro')]")
	WebElement depositAssignProcessor;
	
	@FindBy(xpath = "//div[@class=\"col-xl-3 col-lg-3 col-md-6 col-sm-6 col-12 ddlMultiselect\"]")
	WebElement depositProcessorSelectAny;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchDepositProcessor;
	
	@FindBy(xpath = "//label[normalize-space()='Test-Acs-01-MM - (287)']")
	WebElement testacs01;
	
	@FindBy(xpath = "(//input[@class=\"chkIsActive\"])[1]")
	WebElement firSTPAY;
	
	@FindBy(xpath = "(//input[@class=\"chkProcessorIsActive\"])[1]")
	WebElement processorIsActive;
	
	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtLoadAmount\"])[1]")
	WebElement limit;
	
	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtOrderNo\"])[1]")
	WebElement order;
	
	@FindBy(xpath = "//tbody/tr[14]/td[1]/input[1]")
	WebElement acs;
	
	@FindBy(xpath = "//tbody/tr[14]/td[5]/input[1]")
	WebElement processorisactive;
	
	@FindBy(xpath = "//tbody/tr[14]/td[6]/input[1]")
	WebElement limit2;
	
	@FindBy(xpath = "//tbody/tr[14]/td[7]/input[1]")
	WebElement order2;
	
	@FindBy(xpath = "//button[@id='btnSaveLoadBalance']")
	WebElement updateLimit;
	
	
	public void interactWithsettingsDepositProcessor() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(settings)).click();
		wait.until(ExpectedConditions.elementToBeClickable(depositAssignProcessor)).click();
		wait.until(ExpectedConditions.elementToBeClickable(depositProcessorSelectAny)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchDepositProcessor)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(testacs01)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id( "btnGetProcessor"))).click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(firstPay)).click();
		wait.until(ExpectedConditions.elementToBeClickable(processorIsActive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(limit)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(limit)).sendKeys("1000");
		wait.until(ExpectedConditions.elementToBeClickable(order)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(order)).sendKeys("1");
		wait.until(ExpectedConditions.elementToBeClickable(acs)).click();
		wait.until(ExpectedConditions.elementToBeClickable(processorisactive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(limit2)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(limit2)).sendKeys("1000");
		wait.until(ExpectedConditions.elementToBeClickable(order2)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(order2)).sendKeys("1");
		String screenshotName = "SettingsDepositTx_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "Setting-DepositTx", screenshotName);
		wait.until(ExpectedConditions.elementToBeClickable(firstPay)).click();
		wait.until(ExpectedConditions.elementToBeClickable(processorIsActive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(acs)).click();
		wait.until(ExpectedConditions.elementToBeClickable(processorisactive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSaveLoadBalance"))).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept(); 
		
		
		

	    
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 wait.until(ExpectedConditions.elementToBeClickable(firSTPAY)).click();
		 wait.until(ExpectedConditions.elementToBeClickable(processorIsActive)).click(); 
		  wait.until(ExpectedConditions.elementToBeClickable(limit)).clear();
		  wait.until(ExpectedConditions.elementToBeClickable(limit)).sendKeys("1000");
		  wait.until(ExpectedConditions.elementToBeClickable(order)).clear();
		  wait.until(ExpectedConditions.elementToBeClickable(order)).sendKeys("1");
		  String screenshotName = "Settings_Page_Screenshot";
		  System.out.println("Capturing full page screenshot...");
		  CommonUtilis.captureFullPageScreenshot(driver, "Settings-Deposit Processor Assign", screenshotName);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id( "btnSaveLoadBalance"))).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();
		  System.out.println("Alert Text: " + alert.getText());  
		  alert.accept(); 
		  
		  ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	
	}

}
