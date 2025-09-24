
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

public class Settings_PayoutProcessorAss {
    WebDriver driver;
    WebDriverWait wait;

    public Settings_PayoutProcessorAss(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ Fixed locator
    @FindBy(xpath = "(//span[@class=\"nav-item\"])[7]")
    WebElement settings;

    // Sidebar toggle button (adjust xpath if different in PG10 UI)
    @FindBy(xpath = "//i[contains(@class,'nav-icon') and contains(@class,'icon-layers3')]")
    WebElement sidebarToggle;

    @FindBy(xpath = "//a[contains(text(),'Payout - Assign Processor & Set Merchant Wise Proc')]")
    WebElement payoutAssign;

    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
    WebElement MMSelectany;

    @FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
    WebElement SearchMM;

    // X Path For UAT 
    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01 - (287)']")
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
            Thread.sleep(2000);

            // ✅ Ensure sidebar is open before clicking Settings
            try {
                if (!settings.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(sidebarToggle)).click();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("Sidebar toggle not needed or already open.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(settings)).click();
            wait.until(ExpectedConditions.elementToBeClickable(payoutAssign)).click();
            wait.until(ExpectedConditions.elementToBeClickable(MMSelectany)).click();
            wait.until(ExpectedConditions.elementToBeClickable(SearchMM)).sendKeys("Test-acs-01");
            wait.until(ExpectedConditions.elementToBeClickable(testacs01)).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGetProcessor"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMinAmount"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMinAmount"))).sendKeys("1");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMaxAmount"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSingleTxMaxAmount"))).sendKeys("10");

            wait.until(ExpectedConditions.elementToBeClickable(processor)).click();
            wait.until(ExpectedConditions.elementToBeClickable(limit)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(limit)).sendKeys("1000");
            wait.until(ExpectedConditions.elementToBeClickable(order)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(order)).sendKeys("1");

            wait.until(ExpectedConditions.elementToBeClickable(processor2)).click();
            wait.until(ExpectedConditions.elementToBeClickable(limit2)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(limit2)).sendKeys("1000");
            wait.until(ExpectedConditions.elementToBeClickable(order2)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(order2)).sendKeys("1");

            String screenshotName = "SettingsPayout_Page_Screenshot";
            CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", screenshotName);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSaveLoadBalance"))).click();
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        } catch (Exception e) {
            System.err.println("Error in Settings_PayoutProcessorAss: " + e.getMessage());
            CommonUtilis.captureFullPageScreenshot(driver, "Settings-PayoutProcessorAssign", "Error_Screenshot");
            throw e;
        }
    }
}
