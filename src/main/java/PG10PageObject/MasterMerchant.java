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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

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
	
	@FindBy(xpath = "//input[@id=\"IsProductionLive\"]")
	WebElement isProductionLive;
	
	@FindBy(xpath = "//input[@id=\"Description\"]")
	WebElement masterMerchantnotes;
	
	@FindBy(xpath = "//input[@id=\"IsCheckRealNameonRandom\"]")
	WebElement masterMerchantcheckRealName;
	
	@FindBy(xpath = "//input[@id=\"IsPayoutCheckCurrentBalance\"]")
	WebElement masterMerchantbalanceCheck;
	
	@FindBy(xpath = "//input[@id=\"IsReportCreated\"]")
	WebElement masterMerchantSettlementReport;
	
	@FindBy(xpath = "//button[@id=\"btn-submit\"]")
	WebElement masterMerchantSubmit;
	
	@FindBy(xpath = "//input[@id=\"dt-search-0\"]")
	WebElement masterMerchantSearch;
	
	@FindBy(xpath = "//a[normalize-space()='Create Merchant']")
	WebElement createMerchant;
	
	@FindBy(xpath = "//i[@class='fa fa-arrow-circle-right fa-lg']")
	WebElement submerchant;
	
	@FindBy(xpath = "//a[normalize-space()='Create Sub Merchant']")
	WebElement createSubMerchant;
	
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
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantnotes)).sendKeys("Test");
		wait.until(ExpectedConditions.elementToBeClickable(isProductionLive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantcheckRealName)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantbalanceCheck)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantSettlementReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsPayoutEnabled"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsRandomDetails"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("AllowChargebackOriginalTx"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("AllowRefundOriginalTx"))).click();
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	    Thread.sleep(3000);
	    String screenshotName = "MasterMerchant_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-MasterMerchant", screenshotName);
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantSubmit)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantSearch)).sendKeys("AL_MM");
		WebElement almmIcon = driver.findElement(By.xpath("//tr[td[normalize-space(text())='AL_MM']]//i[contains(@class,'fa-arrow-circle-right')]"));
		almmIcon.click();
		wait.until(ExpectedConditions.elementToBeClickable(createMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("AL_M");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("AliasName"))).sendKeys("AL_M");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("HubSpotId"))).sendKeys("1234");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Description"))).sendKeys("Test");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Margin"))).sendKeys("13");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys("pg10@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Status"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_Enablebalance_report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableBatchPayout_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableInstantPayout_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableKYCVerifycheck_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableKYCVerifyratio_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click();
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-Merchant", screenshotName);
		wait.until(ExpectedConditions.elementToBeClickable(submerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createSubMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("AL_SM");
		
		
		
		
		
		
		

	
	}
	
}
