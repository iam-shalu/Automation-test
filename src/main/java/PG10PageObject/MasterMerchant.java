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
	
	@FindBy(xpath = "//input[@id=\"Status\"]")
	WebElement isActive;
	
	@FindBy(xpath = "(//a[@class=\"btn btn-info btn-sm\"])[1]")
	WebElement backToMerchant;
	
	@FindBy(xpath = "(//a[@class=\"btn btn-info btn-sm\"])[1]")
	WebElement backToMasterMerchant;
	
	@FindBy(xpath =  "//tbody/tr[1]/td[6]/a[2]/span[1]")
	WebElement deleteMasterMerchant;
	
	public void interactWithlistControl_masterMerchant() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(listControl)).click();
		wait.until(ExpectedConditions.elementToBeClickable(merchantList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createMasterMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Name"))).sendKeys("RP_MM");
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantTitle)).sendKeys("RP_MM");
		wait.until(ExpectedConditions.elementToBeClickable(active)).click();
		Select selectCurrency = new Select(currency);
		selectCurrency.selectByVisibleText("Indian Rupee");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("HubSpotId"))).sendKeys("123");
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantnotes)).sendKeys("Test");
		wait.until(ExpectedConditions.elementToBeClickable(isProductionLive)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsTestAccount"))).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(masterMerchantSearch)).sendKeys("RP_MM");
		WebElement almmIcon = driver.findElement(By.xpath("//tr[td[normalize-space(text())='RP_MM']]//i[contains(@class,'fa-arrow-circle-right')]"));
		almmIcon.click();
		wait.until(ExpectedConditions.elementToBeClickable(createMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("RP_M");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("AliasName"))).sendKeys("RP_MM");
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
		String screenshotName1 = "MasterMerchant_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-Merchant", screenshotName1);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(submerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createSubMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("RP_SM");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("AliasName"))).sendKeys("RP_SM");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("supportemail"))).sendKeys("subm@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("HubSpotId"))).sendKeys("1876");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("description"))).sendKeys("NA");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("margin"))).sendKeys("15");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsRefund"))).click();
		Thread.sleep(1000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.id("EnablePayout"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("EnableDeposit"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsWithoutProcess"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsGeoLocationcheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsMD5toAESMoved"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_HashModify"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkAllowFundTransfer"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkIsInstantPayout"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkIsInstantPayout"))).click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FxRateType")));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Live Rate");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("FxMarkup"))).sendKeys("13");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("CheckBalanceLevel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("BTSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("UPISeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("QRCodeSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("UPIQRCodeSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("WalletSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("BankDepositSeonDepositFraudCheck"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.id("CardDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("VoucherDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("CashDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PIXWithCryptoDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PIXDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SPEIDepositSeonDepositFraudCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsIPCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkIsmatchKYCName"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkIsValidateVPA"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkIsKycVpaValidate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsBlacklist_by_VPA"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsBlacklist_by_VPA_Prefix"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsBlacklist_by_VPA_Postfix"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsBlacklist_by_City"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsBlacklist_by_State"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsKYCEnabled"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_KYCReverificationInterval"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//select[@id='tblsite_Validation_Rules_KYCReverificationInterval']/option[text()='15 Days']")))
		    .click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_TrustScoreLevel"))).sendKeys("1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_TrustScoreCheck"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_TrustScoreByPassEnabled"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsFTDNonFTDMidRouting"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_IsFTDNonFTDMidRoutingForSubMerchant"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_FTD_Mid_AllowBeforeDays"))).sendKeys("1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("MidRoutingRuleAppliedOn"))).click();
		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("MidRoutingRuleAppliedOn")));
		Select select1 = new Select(dropdown1);
		select1.selectByVisibleText("Mobile");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_MidRoutingTxnCount"))).sendKeys("15");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("MidRoutingExpression"))).click();
		Thread.sleep(3000);
		WebElement dropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("MidRoutingExpression")));
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("AND");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_MidRoutingAmountSum"))).sendKeys("130");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_EnableUPIRecurring"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_Enablebalance_report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableBatchPayout_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableInstantPayout_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableKYCVerifycheck_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Is_EnableKYCVerifyratio_Report"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tblsite_Validation_Rules_Alert_Trigger"))).click();
		String screenshotName2 = "MasterMerchant_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-SubMerchant", screenshotName2);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click();
		String screenshotName5 = "MasterMerchant_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-SubMerchantCreatedPage", screenshotName5);
		wait.until(ExpectedConditions.elementToBeClickable(backToMerchant)).click();
		wait.until(ExpectedConditions.elementToBeClickable(backToMasterMerchant)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteMasterMerchant)).click();
		// Wait for the alert and switch
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text: " + alert.getText());
		alert.accept();
		
		
	}
	
}
