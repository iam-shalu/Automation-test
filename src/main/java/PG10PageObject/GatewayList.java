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

import PG10utils.CommonUtilis;

public class GatewayList {
	WebDriver driver;
	WebDriverWait wait;

	public GatewayList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(xpath = "(//span[@class=\"nav-item\"])[5]")
	WebElement listControl;
	
	@FindBy(xpath = "//a[normalize-space()='Gateway List']")
	WebElement gatewayList;
	
	@FindBy(xpath = "//input[@id='dt-search-0']")
	WebElement searchGatewayList;
	
	@FindBy(xpath = "(//i[@class=\"fa fa-arrow-circle-o-right fa-lg\"])[1]")
	WebElement Descriptor;
	
	@FindBy(xpath = "(//a[@class=\"btn btn-info btn-sm\"])[2]")
	WebElement createDescriptor;
	
	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	WebElement currencyList;
	
	@FindBy(xpath = "(//input[@type=\"checkbox\"])[1]")
	WebElement selectAll;
	
	@FindBy(xpath =  "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[3]")
	WebElement countryList;
	
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@value='multiselect-all']")
	WebElement selectAllCountryList;
	
	@FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[4]")
	WebElement paymentType;
	
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Select all']")
	WebElement selectAllpaymentType;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div[7]/div[4]/div/div/button")
	WebElement descriptorType;
	
	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@value='multiselect-all']")
	WebElement descriptorTypeselectAll;
	
	@FindBy(xpath = "//input[@name='remarks']")
	WebElement notes;
	
	@FindBy(xpath = "//tbody/tr[1]/td[5]/a[2]/i[1]")
	WebElement deleteTest3;
	
	
	public void interactWithgatewayList() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(listControl)).click();
		wait.until(ExpectedConditions.elementToBeClickable(gatewayList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchGatewayList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchGatewayList)).sendKeys("Test");
		wait.until(ExpectedConditions.elementToBeClickable(Descriptor)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createDescriptor)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("terminalid"))).sendKeys("0");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("authentication"))).sendKeys("1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication2"))).sendKeys("2");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication3"))).sendKeys("3");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication4"))).sendKeys("4");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication5"))).sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication6"))).sendKeys("6");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication7"))).sendKeys("7");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication8"))).sendKeys("8");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication9"))).sendKeys("9");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication10"))).sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Authentication11"))).sendKeys("11");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("descriptor"))).sendKeys("Test3");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("displaytitle"))).sendKeys("Test3");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phonenumber"))).sendKeys("9632629033");
		wait.until(ExpectedConditions.elementToBeClickable(currencyList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAll)).click();
		wait.until(ExpectedConditions.elementToBeClickable(countryList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAllCountryList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(paymentType)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectAllpaymentType)).click();
		wait.until(ExpectedConditions.elementToBeClickable(descriptorType)).click();
		wait.until(ExpectedConditions.elementToBeClickable(descriptorTypeselectAll)).click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(notes)).sendKeys("Na");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsNoRefunds"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Status"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("IsSupplierStatus"))).click();
		String screenshotName = "GatewayList_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-GateWayList", screenshotName);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click();
		Thread.sleep(3000);
		String screenshotName1 = "GatewayList_DescriptorListPage_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "ListControl-GateWayList-DescriptorList", screenshotName1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteTest3)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept(); 
	
		
	}

}
