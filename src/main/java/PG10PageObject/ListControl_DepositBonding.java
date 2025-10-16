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

public class ListControl_DepositBonding {
	
	WebDriver driver;
	WebDriverWait wait;

	public ListControl_DepositBonding(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Page Elements
	@FindBy(xpath = "(//span[@class=\"nav-item\"])[5]")
	WebElement listControl;
	
	@FindBy(xpath = "(//a[@id=\"submenuDropdown\"])[2]")
	WebElement merchantList;
	
	@FindBy(xpath = "//a[@href='/DepositBoarding']")
	WebElement depositBoarding;
	
	@FindBy(id = "MerchantId")
	WebElement merchantDropdown;

	@FindBy(id = "RetailerId")
	WebElement retailerDropdown;

	@FindBy(id = "SiteId")
	WebElement siteDropdown;
	
	@FindBy(xpath = "//select[@name=\"CBFeeCurrency\"]")
	WebElement chargebackFee;
	
	@FindBy(xpath = "//select[@name=\"RefundCurrency\"]")
	WebElement refundfeeCurrency;
	
	@FindBy(xpath = "//select[@name=\"RefundCurrency\"]")
	WebElement refundFee;
	
	@FindBy(xpath = "//button[@class=\"btn btn-success\"]")
	WebElement save;

	public void interactWithListControl_DepositBonding() throws InterruptedException, IOException {

		    wait.until(ExpectedConditions.elementToBeClickable(listControl)).click();
		    wait.until(ExpectedConditions.elementToBeClickable(merchantList)).click();
		    wait.until(ExpectedConditions.elementToBeClickable(depositBoarding)).click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("btncreate"))).click();

		    // Select Merchant
		    wait.until(ExpectedConditions.elementToBeClickable(merchantDropdown));
		    Select selectMerchant = new Select(merchantDropdown);
		    selectMerchant.selectByVisibleText("RP_MM");
		    
		    // Select Retailer
		    wait.until(ExpectedConditions.elementToBeClickable(retailerDropdown));
		    Select selectRetailer = new Select(retailerDropdown);
		    selectRetailer.selectByVisibleText("RP_M");

		    // Select Site
		    wait.until(ExpectedConditions.elementToBeClickable(siteDropdown));
		    Select selectSite = new Select(siteDropdown);
		    selectSite.selectByVisibleText("RP_SM");
		    
		    WebElement ftdDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("FTDNonFTD")));
		    Select selectFTD = new Select(ftdDropdown);
		    selectFTD.selectByVisibleText("FTD");
		    
		    WebElement paymentMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("PaymentMethod")));
		    Select selectPaymentMethod = new Select(paymentMethodDropdown);
		    selectPaymentMethod.selectByVisibleText("UPI");
		     
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("BankchargesPer"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("BankchargesPer"))).sendKeys("11");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("GSTBankchargePer"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("GSTBankchargePer"))).sendKeys("2");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("DepositFixedFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("DepositFixedFee"))).sendKeys("3");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RollingReserveHoldDay"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RollingReserveHoldDay"))).sendKeys("9");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("FruadFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("FruadFee"))).sendKeys("8");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RollingReserve"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RollingReserve"))).sendKeys("7");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("FruadCostFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("FruadCostFee"))).sendKeys("6");
		       
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("ReversalFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("ReversalFee"))).sendKeys("5");
		    
		    
		    wait.until(ExpectedConditions.elementToBeClickable(refundfeeCurrency));
		    Select selectRefundFeeCurrency = new Select(refundfeeCurrency);
		    selectRefundFeeCurrency.selectByVisibleText("Indian Rupee (INR)");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(chargebackFee));
		    Select selectChargebackFee = new Select(chargebackFee);
		    selectChargebackFee.selectByVisibleText("Indian Rupee (INR)");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("GSTonReversalFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("GSTonReversalFee"))).sendKeys("13");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("CBFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("CBFee"))).sendKeys("15");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RefundFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RefundFee"))).sendKeys("8");
		    
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RRReversalFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RRReversalFee"))).sendKeys("16");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("CBCostFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("CBCostFee"))).sendKeys("18");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RefundCostFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("RefundCostFee"))).sendKeys("12");
		    
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("ReversalCostFee"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("ReversalCostFee"))).sendKeys("2");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("Remark"))).clear();
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("Remark"))).sendKeys("NA");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(save)).click();
		    
		    Thread.sleep(1000);
		    
		    
		
		
	}
}
