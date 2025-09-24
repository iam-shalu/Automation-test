
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTDWhiteListUser {

	
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(FTDWhiteListUser.class);

    public FTDWhiteListUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='FTD White List User']")
    WebElement ftdWhiteList;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
    WebElement selectMasterMerchant;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
    WebElement searchMasterMerchant;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement TestAcs01;

    @FindBy(xpath = "//a[normalize-space()='Add FTDWhiteList User']")
    WebElement addWhiteListUser;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[3]")
    WebElement addWhiteListUserMasterMerchant;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement searchAddWhiteListUserMasterMerchant;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement TestAcs013;

    @FindBy(xpath = "(//button[@class=\"btn btn-success\"])[1]")
    WebElement close;

    @FindBy(xpath = "//div[@class='gutters row']//button[@title='Select Master Merchant']")
    WebElement selectMasterMerchant2;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
    WebElement searchMasterMerchant2;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement TestAcs0135;

    @FindBy(xpath = "//span[@class=\"fa fa-trash-o fa-lg\"]")
    WebElement deleteRecord;

    By duplicateDataError = By.xpath("//*[contains(text(),'Data Already Exist for this Merchant')]");

    // ===== Main Method =====
    public void interactWithfraudControl_FTDwhiteListUser() throws IOException {

        log.info("==== Starting FraudControl FTD White List User Test ====");

        safeClick(fraudControlMenu);
        safeClick(ftdWhiteList);

        // Select Master Merchant
        safeClick(selectMasterMerchant);
        typeAndSelect(searchMasterMerchant, "Test-acs-01", TestAcs01);

        // Upload Excel
        try {
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fileInput")));
            String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\FTDWhiteList User\\FTDWhiteListUser.xlsx";
            fileInput.sendKeys(filePath);
            log.info("File uploaded successfully.");
        } catch (Exception e) {
            log.error("File upload failed", e);
        }

        safeClick(By.id("btnimport"));

        // Add new WhiteList User
        safeClick(addWhiteListUser);
        safeClick(addWhiteListUserMasterMerchant);
        typeAndSelect(searchAddWhiteListUserMasterMerchant, "Test-acs-01", TestAcs013);

        typeText(By.id("firstName"), "Akash");
        typeText(By.id("lastName"), "Lade");
        typeText(By.id("Email"), "akash@gmail.com");
        typeText(By.id("Phone"), "9632629063");
        safeClick(By.id("btn_Save"));

        // Handle duplicate data gracefully
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateDataError));
            if (errorMsg.isDisplayed()) {
                log.warn("Duplicate data detected → closing popup.");
                safeClick(close);
                return; // Stop execution
            }
        } catch (Exception e) {
            log.info("No duplicate error, continuing.");
        }

        // Filter and screenshot
        safeClick(selectMasterMerchant2);
        typeAndSelect(searchMasterMerchant2, "Test-acs-01", TestAcs0135);
        safeClick(By.id("btnFilter"));

        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-FTDWhiteListUser", "FTDWhiteListUser_Page_Screenshot");

        // Delete test data
        deleteRecordByPhone("9632629063");
        deleteRecordByPhone("9632629033");

        log.info("==== Completed FraudControl FTD White List User Test ====");
    }

    // ===== Helpers =====
    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'}); window.scrollBy(0,-150);", element);
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        safeClick(element);
    }

    private void typeAndSelect(WebElement searchBox, String text, WebElement optionToSelect) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(text);
        safeClick(optionToSelect);
    }

    private void typeText(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }
    
    private void deleteRecordByPhone(String phone) {
        try {
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch")));
            searchBox.clear();
            searchBox.sendKeys(phone);
            safeClick(By.id("btnFilter"));

            List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[@class=\"fa fa-trash-o fa-lg\"]"));
            if (!deleteButtons.isEmpty()) {
                safeClick(deleteButtons.get(0));
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
            }
        } catch (Exception e) {
            log.warn("No record found to delete for phone: " + phone);
        }
    }
}
