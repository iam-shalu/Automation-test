package PG10PageObject;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
    JavascriptExecutor js;

    public Settings_DepositAssignProcessor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "(//span[@class=\"nav-item\"])[7]")
    WebElement settings;

    @FindBy(xpath = "//a[contains(text(),'Deposit - Assign Processor & Set Merchant Wise Pro')]")
    WebElement depositAssignProcessor;

    @FindBy(xpath = "//div[@class=\"col-xl-3 col-lg-3 col-md-6 col-sm-6 col-12 ddlMultiselect\"]")
    WebElement depositProcessorSelectAny;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchDepositProcessor;

//    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01 - (287)']")
//    WebElement testacs01;
    
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

    // -------------------- Headless Safe Click --------------------
    private void safeClick(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            System.out.println("[CLICKED] " + elementName);
        } catch (Exception e) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
            System.out.println("[JS CLICK FALLBACK] " + elementName);
        }
    }

    public void interactWithsettingsDepositProcessor() throws IOException {

        try {
            // Handle sidebar if hidden
            List<WebElement> sidebarList = driver.findElements(By.id("sidebar-wrapper"));
            if (!sidebarList.isEmpty() && !settings.isDisplayed()) {
                WebElement toggleBtn = driver.findElement(By.id("sidebarToggle"));
                js.executeScript("arguments[0].click();", toggleBtn);
                wait.until(ExpectedConditions.visibilityOf(settings));
            }

            // Navigate to Deposit Assign Processor
            safeClick(settings, "Settings");
            safeClick(depositAssignProcessor, "Deposit Assign Processor");

            // Select deposit processor
            safeClick(depositProcessorSelectAny, "Deposit Processor Dropdown");
            wait.until(ExpectedConditions.visibilityOf(searchDepositProcessor)).sendKeys("Test-acs-01");
            safeClick(testacs01, "Test-Acs-01");

            safeClick(driver.findElement(By.id("btnGetProcessor")), "Get Processor");

            // Scroll bottom
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Fill processor details
            safeClick(firSTPAY, "First Pay Active");
            safeClick(processorIsActive, "Processor Is Active");

            wait.until(ExpectedConditions.visibilityOf(limit)).clear();
            limit.sendKeys("1000");

            wait.until(ExpectedConditions.visibilityOf(order)).clear();
            order.sendKeys("1");

            safeClick(acs, "ACS Checkbox");
            safeClick(processorisactive, "Processor Is Active 2");

            limit2.clear();
            limit2.sendKeys("1000");

            order2.clear();
            order2.sendKeys("1");

            // Screenshot
            CommonUtilis.captureFullPageScreenshot(driver, "Setting-DepositTx", "SettingsDepositTx_Page_Screenshot");

            // Final save
            safeClick(processorisactive, "Processor Is Active 2 again");
            safeClick(updateLimit, "Update Limit");

            wait.until(ExpectedConditions.alertIsPresent()).accept();

            // Scroll top
            js.executeScript("window.scrollTo(0, 0);");

        } catch (Exception e) {
            System.out.println("Error in Settings_DepositAssignProcessor: " + e.getMessage());
            CommonUtilis.captureFullPageScreenshot(driver, "Setting-DepositTx", "Error_Screenshot");
        }
    }
}

