//package PG10PageObject;
//import java.io.IOException;
//import java.time.Duration;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import PG10Base.PG10Base;
//import PG10utils.CommonUtilis;
//
//public class Settings_PayoutrAssignProcessor {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public Settings_PayoutrAssignProcessor(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    }
//
//    // ✅ Use stable locator if possible instead of index
//    @FindBy(xpath = "(//span[@class=\"nav-item\"])[7]")
//    WebElement settings;
//
//    @FindBy(xpath = "//i[contains(@class,'nav-icon') and contains(@class,'icon-layers3')]")
//    WebElement sidebarToggle;
//
//    @FindBy(xpath = "//a[contains(text(),'Payout - Assign Processor & Set Merchant Wise Proc')]")
//    WebElement payoutAssign;
//
//    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
//    WebElement MMSelectany;
//
//    @FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
//    WebElement SearchMM;
//
//    // UAT specific
////    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01 - (287)']")
////    WebElement testacs01;
//    
//    //For Production Env.
//    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01-MM - (287)']")
//    WebElement testacs01;
//
//    @FindBy(xpath = "(//input[@type=\"checkbox\"])[1]")
//    WebElement processor;
//
//    @FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtLoadAmount\"])[1]")
//    WebElement limit;
//
//    @FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtOrderNo\"])[1]")
//    WebElement order;
//
//    @FindBy(xpath = "(//input[@class=\"chkIsActive\"])[2]")
//    WebElement processor2;
//
//    @FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtLoadAmount\"])[2]")
//    WebElement limit2;
//
//    @FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtOrderNo\"])[2]")
//    WebElement order2;
//
//    public void interactWithsettingsPayoutProcessorAss() throws IOException, InterruptedException {
//        try {
//            // ✅ Ensure sidebar open
//        	
//            try {
//                if (!settings.isDisplayed()) {
//                    wait.until(ExpectedConditions.elementToBeClickable(sidebarToggle)).click();
//                }
//            } catch (Exception e) {
//                System.out.println("Sidebar toggle not needed or already open.");
//            }
//
//            // ✅ Use safeClick from PG10Base (scroll + JS fallback if needed)
//            PG10Base.safeClick(By.xpath("(//span[@class='nav-item'])[7]"));
//            PG10Base.safeClick(By.xpath("//a[contains(text(),'Payout - Assign Processor & Set Merchant Wise Proc')]"));
//            PG10Base.safeClick(By.xpath("(//span[@class='multiselect-selected-text'])[1]"));
//
//            wait.until(ExpectedConditions.elementToBeClickable(SearchMM)).sendKeys("Test-acs-01");
//            wait.until(ExpectedConditions.elementToBeClickable(testacs01)).click();
//
//            PG10Base.safeClick(By.id("btnGetProcessor"));
//
//            WebElement minAmount = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMinAmount")));
//            minAmount.clear();
//            minAmount.sendKeys("1");
//
//            WebElement maxAmount = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMaxAmount")));
//            maxAmount.clear();
//            maxAmount.sendKeys("10");
//            
//            // (//input[@type="checkbox"])[3]
//
//            PG10Base.safeClick(By.xpath("(//input[@type='checkbox'])[3]"));
//            limit.clear();
//            limit.sendKeys("1000");
//            order.clear();
//            order.sendKeys("1");
//
//            PG10Base.safeClick(By.xpath("(//input[@type=\"checkbox\"])[28]"));
//            limit2.clear();
//            limit2.sendKeys("1000");
//            order2.clear();
//            order2.sendKeys("1");
//
//            String screenshotName = "SettingsPayout_Page_Screenshot";
//            CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", screenshotName);
//            
//            PG10Base.safeClick(By.id("btnSaveLoadBalance"));
//            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
//
//        } catch (Exception e) {
//            System.err.println("Error in Settings_PayoutProcessorAss: " + e.getMessage());
//            CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", "Error_Screenshot");
//            throw e;
//        }
//        
//        
//    }
//}

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
import PG10Base.PG10Base;
import PG10utils.CommonUtilis;

public class Settings_PayoutrAssignProcessor {
	WebDriver driver;
	WebDriverWait wait;

	public Settings_PayoutrAssignProcessor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// ✅ Use stable locator if possible instead of index
	@FindBy(xpath = "(//span[@class=\"nav-item\"])[7]")
	WebElement settings;

	@FindBy(xpath = "//i[contains(@class,'nav-icon') and contains(@class,'icon-layers3')]")
	WebElement sidebarToggle;

	@FindBy(xpath = "//a[contains(text(),'Payout - Assign Processor & Set Merchant Wise Proc')]")
	WebElement payoutAssign;

	@FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
	WebElement MMSelectany;

	@FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
	WebElement SearchMM;

	// UAT specific
//    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01 - (287)']")
//    WebElement testacs01;

	// For Production Env.
	@FindBy(xpath = "//label[normalize-space()='Test-Acs-01-MM - (287)']")
	WebElement testacs01;

	@FindBy(xpath = "(//input[@type=\"checkbox\"])[1]")
	WebElement processor;

	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtLoadAmount\"])[1]")
	WebElement limit;

	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtOrderNo\"])[1]")
	WebElement order;

	@FindBy(xpath = "(//input[@class=\"chkIsActive\"])[2]")
	WebElement processor2;

	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtLoadAmount\"])[2]")
	WebElement limit2;

	@FindBy(xpath = "(//input[@class=\"form-control form-control-sm txtOrderNo\"])[2]")
	WebElement order2;

	public void interactWithsettingsPayoutProcessorAss() throws IOException, InterruptedException {
		try {
			// ✅ Ensure sidebar open

			try {
				if (!settings.isDisplayed()) {
					wait.until(ExpectedConditions.elementToBeClickable(sidebarToggle)).click();
				}
			} catch (Exception e) {
				System.out.println("Sidebar toggle not needed or already open.");
			}

			// ✅ Use safeClick from PG10Base (scroll + JS fallback if needed)
			PG10Base.safeClick(By.xpath("(//span[@class='nav-item'])[7]"));
			PG10Base.safeClick(By.xpath("//a[contains(text(),'Payout - Assign Processor & Set Merchant Wise Proc')]"));
			PG10Base.safeClick(By.xpath("(//span[@class='multiselect-selected-text'])[1]"));

			wait.until(ExpectedConditions.elementToBeClickable(SearchMM)).sendKeys("Test-acs-01");
			wait.until(ExpectedConditions.elementToBeClickable(testacs01)).click();

			PG10Base.safeClick(By.id("btnGetProcessor"));

			WebElement minAmount = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMinAmount")));
			minAmount.clear();
			minAmount.sendKeys("1");

			WebElement maxAmount = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMaxAmount")));

			maxAmount.clear();
			maxAmount.sendKeys("10");

			// (//input[@type="checkbox"])[3]

			PG10Base.safeClick(By.xpath("(//input[@type='checkbox'])[3]"));
			limit.clear();
			limit.sendKeys("1000");
			order.clear();
			order.sendKeys("1");

			Thread.sleep(3000);
			PG10Base.safeClick(
					By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[1]/input"));
			limit2.clear();
			limit2.sendKeys("1000");
			order2.clear();
			order2.sendKeys("1");

			String screenshotName = "SettingsPayout_Page_Screenshot";
			CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", screenshotName);

			PG10Base.safeClick(By.id("btnSaveLoadBalance"));
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		} catch (Exception e) {
			System.err.println("Error in Settings_PayoutProcessorAss: " + e.getMessage());
			CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", "Error_Screenshot");
			throw e;
		}

	}
}
