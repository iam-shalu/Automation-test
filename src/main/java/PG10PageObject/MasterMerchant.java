
package PG10PageObject;
import java.io.IOException;
import java.time.Duration;
import org.apache.log4j.Logger;
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
    Logger log = Logger.getLogger(MasterMerchant.class);

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

    @FindBy(xpath = "//a[@class=\"btn btn-info btn-sm\"]")
    WebElement createMasterMerchant;

    @FindBy(xpath = "//input[@id=\"Title\"]")
    WebElement masterMerchantTitle;

    @FindBy(xpath = "//select[@id=\"SettlementCurrency\"]")
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

    @FindBy(xpath = "(//a[@class=\"btn btn-info btn-sm\"])[1]")
    WebElement backToMerchant;

    @FindBy(xpath = "(//a[@class=\"btn btn-info btn-sm\"])[1]")
    WebElement backToMasterMerchant;

    @FindBy(xpath = "//tbody/tr[1]/td[6]/a[2]/span[1]")
    WebElement deleteMasterMerchant;

    // Utility: Safe click with scroll + JS fallback
    private void clickElementSafe(WebElement element, String elementName) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            log.info("Clicked: " + elementName);
        } catch (Exception e) {
            log.warn("Normal click failed on: " + elementName + ". Trying JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void interactWithlistControl_masterMerchant() throws IOException {
        try {
            log.info("Navigating to Master Merchant List...");

            clickElementSafe(listControl, "List Control");
            clickElementSafe(merchantList, "Merchant List");
            clickElementSafe(masterMerchantList, "Master Merchant List");

            // Create Master Merchant
            log.info("Creating Master Merchant...");
            clickElementSafe(createMasterMerchant, "Create Master Merchant");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("Name"))).sendKeys("RP_MM");
            masterMerchantTitle.sendKeys("RP_MM");
            clickElementSafe(active, "Active Checkbox");

            new Select(currency).selectByVisibleText("Indian Rupee");

            driver.findElement(By.id("HubSpotId")).sendKeys("123");
            masterMerchantnotes.sendKeys("Test");
            clickElementSafe(isProductionLive, "Is Production Live");
            driver.findElement(By.id("IsTestAccount")).click();
            clickElementSafe(masterMerchantcheckRealName, "Check Real Name");
            clickElementSafe(masterMerchantbalanceCheck, "Balance Check");
            clickElementSafe(masterMerchantSettlementReport, "Settlement Report");
            driver.findElement(By.id("IsPayoutEnabled")).click();
            driver.findElement(By.id("IsRandomDetails")).click();
            driver.findElement(By.id("AllowChargebackOriginalTx")).click();
            driver.findElement(By.id("AllowRefundOriginalTx")).click();

            CommonUtilis.captureFullPageScreenshot(driver, "ListControl-MasterMerchant", "MasterMerchant_Page_Screenshot");
            clickElementSafe(masterMerchantSubmit, "Master Merchant Submit");

            // Search & expand Master Merchant
            wait.until(ExpectedConditions.visibilityOf(masterMerchantSearch));
            masterMerchantSearch.sendKeys("RP_MM");

            WebElement almmIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tr[td[normalize-space(text())='RP_MM']]//i[contains(@class,'fa-arrow-circle-right')]")));
            clickElementSafe(almmIcon, "Expand Master Merchant");

            // Create Merchant under Master Merchant
            log.info("Creating Merchant under Master Merchant...");
            clickElementSafe(createMerchant, "Create Merchant");

            driver.findElement(By.id("name")).sendKeys("RP_MM");
            driver.findElement(By.id("AliasName")).sendKeys("RP_MM");
            driver.findElement(By.id("HubSpotId")).sendKeys("1234");
            driver.findElement(By.id("Description")).sendKeys("Test");
            driver.findElement(By.id("Margin")).sendKeys("13");
            driver.findElement(By.id("Email")).sendKeys("pg10@gmail.com");
            driver.findElement(By.id("Status")).click();
            driver.findElement(By.id("Is_Enablebalance_report")).click();
            driver.findElement(By.id("Is_EnableBatchPayout_Report")).click();
            driver.findElement(By.id("Is_EnableInstantPayout_Report")).click();
            driver.findElement(By.id("Is_EnableKYCVerifycheck_Report")).click();
            driver.findElement(By.id("Is_EnableKYCVerifyratio_Report")).click();

            CommonUtilis.captureFullPageScreenshot(driver, "ListControl-Merchant", "Merchant_Page_Screenshot");
            driver.findElement(By.id("btn-submit")).click();

            // Create Sub Merchant
            log.info("Creating Sub Merchant...");
            clickElementSafe(submerchant, "Sub Merchant");
            clickElementSafe(createSubMerchant, "Create Sub Merchant");

            driver.findElement(By.id("name")).sendKeys("RP_SM");
            driver.findElement(By.id("AliasName")).sendKeys("RP_SM");
            driver.findElement(By.id("supportemail")).sendKeys("subm@gmail.com");
            driver.findElement(By.id("HubSpotId")).sendKeys("1876");
            driver.findElement(By.id("description")).sendKeys("NA");
            driver.findElement(By.id("margin")).sendKeys("15");
            driver.findElement(By.id("IsRefund")).click();
            driver.findElement(By.id("EnablePayout")).click();
            driver.findElement(By.id("EnableDeposit")).click();

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            new Select(driver.findElement(By.id("FxRateType"))).selectByVisibleText("Live Rate");
            driver.findElement(By.id("FxMarkup")).sendKeys("13");

            CommonUtilis.captureFullPageScreenshot(driver, "ListControl-SubMerchant", "SubMerchant_Page_Screenshot");
            driver.findElement(By.id("btn-submit")).click();
            CommonUtilis.captureFullPageScreenshot(driver, "ListControl-SubMerchantCreatedPage", "SubMerchantCreated_Page_Screenshot");

            // Delete Master Merchant safely
            log.info("Deleting Master Merchant...");
            clickElementSafe(backToMerchant, "Back to Merchant");
            clickElementSafe(backToMasterMerchant, "Back to Master Merchant");

            // Wait for overlays to disappear
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay, .spinner")));
            } catch (Exception ignored) {}

            try {
                clickElementSafe(deleteMasterMerchant, "Delete Master Merchant");
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                log.info("Alert text: " + alert.getText());
                alert.accept();
            } catch (Exception e) {
                log.warn("Delete Master Merchant step skipped: " + e.getMessage());
            }

            log.info("Master Merchant flow completed successfully.");

        } catch (Exception e) {
            log.error("Error during Master Merchant interaction", e);
            CommonUtilis.captureFullPageScreenshot(driver, "ListControl-MasterMerchant", "Error_Screenshot");
        }
    }
}
