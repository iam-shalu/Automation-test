package PG10PageObject;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MasterMerchant {
	WebDriver driver;
	WebDriverWait wait;
	
	public MasterMerchant(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(xpath = "(//span[@class=\"nav-item\"])[5]")
	WebElement listControl;
	
	@FindBy(xpath = "(//a[@id=\"submenuDropdown\"])[2]")
	WebElement merchantList;
	
	@FindBy(xpath = "//a[normalize-space()='Master Merchant List']")
	WebElement masterMerchantList;
	
	@FindBy(xpath =  "//a[@class=\"btn btn-info btn-sm\"]")
	WebElement createMasterMerchant;
	
	@FindBy(xpath = "//input[@id=\"Title\"]")
	WebElement masterMerchantTitle;
	
	@FindBy(xpath = "//select[@id=\"SettlementCurrency\"]" )
	WebElement currency;
	
	@FindBy(xpath = "//input[@id=\"Status\"]")
	WebElement active;
	
	@FindBy(xpath = "//input[@id=\"Description\"]")
	WebElement masterMerchantnotes;
	
	
	public void interactWithlistControl_masterMerchant() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(listControl)).click();
		wait.until(ExpectedConditions.elementToBeClickable(merchantList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createMasterMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Name"))).sendKeys("AL_MM");
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantTitle)).sendKeys("AL_MM");
		wait.until(ExpectedConditions.elementToBeClickable(active)).click();
		Select selectCurrency = new Select(currency);
		selectCurrency.selectByVisibleText("Indian Rupee");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("HubSpotId"))).sendKeys("123");
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantnotes)).click();
		
		
	}
	
}
