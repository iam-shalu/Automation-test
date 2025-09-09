package PG10PageObject;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@FindBy(xpath = "//label[normalize-space()='Test-Acs-01-007 - (589)']")
	WebElement testacs01;
	
	@FindBy(xpath = "//tbody/tr[12]/td[1]/input[1]")
	WebElement firstPay;
	
	@FindBy(xpath = "//tbody/tr[12]/td[5]/input[1]")
	WebElement processorIsActive;
	
	@FindBy(xpath = "//tbody/tr[12]/td[6]/input[1]")
	WebElement limit;
	
	@FindBy(xpath = "//tbody/tr[12]/td[7]/input[1]")
	WebElement order;
	
	
	public void interactWithsettingsDepositProcessor() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(settings)).click();
		wait.until(ExpectedConditions.elementToBeClickable(depositAssignProcessor)).click();
		wait.until(ExpectedConditions.elementToBeClickable(depositProcessorSelectAny)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchDepositProcessor)).sendKeys("Test-acs-01-007");
		wait.until(ExpectedConditions.elementToBeClickable(testacs01)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id( "btnGetProcessor"))).click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(firstPay)).click();
		wait.until(ExpectedConditions.elementToBeClickable(processorIsActive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(limit)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(limit)).sendKeys("1000");
		wait.until(ExpectedConditions.elementToBeClickable(order)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(order)).sendKeys("1");
		
		
	
	}

}
