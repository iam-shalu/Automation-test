package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
import PG10Base.PG10Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhiteList_MechantIP {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(WhiteList_MechantIP.class);

    public WhiteList_MechantIP(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== Page Elements =====
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='White List Merchant IP']")
    WebElement whiteListMerchantIp;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[1]")
    WebElement sMasterMerchant;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
    WebElement searchWhiteListMasterMerchant;
    
    // For UAT
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement TestAcs01;
    
    // For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcs01;

    @FindBy(xpath = "//a[normalize-space()='Add Whitelist Merchant IP']")
    WebElement addWhiteListMerchantIp;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]")
    WebElement addWhiteListSelectAny;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement addWhiteListTestACS;
    
    // For UAT
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement addWhiteListSelectTestAcs;
    
    //For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement addWhiteListSelectTestAcs;
    

    @FindBy(xpath = "//div[@class='gutters row']//button[@title='Select Master Merchant']")
    WebElement sMasterMerchant2;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@placeholder='Search']")
    WebElement searchWhiteListMasterMerchant2;
    
      // For UAT
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement TestAcs03;
    
    // For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
     WebElement TestAcs03;
    
    @FindBy(xpath = "//h3[text()='White list Master Merchant IP Details']")
    WebElement whitelistMasterMerchantIPDetails;

    // ===== Main Flow =====
    public void interactWithfraudControlwhiteListMerchIP() throws IOException {
        log.info("==== Starting WhiteList Merchant IP Test ====");

        // Expand sidebar if collapsed
        PG10Base.expandSidebarIfCollapsed();

        // Navigate to WhiteList Merchant IP
        safeClick(fraudControlMenu);
        safeClick(whiteListMerchantIp);

        // Select Master Merchant
        safeClick(sMasterMerchant);
        typeAndSelect(searchWhiteListMasterMerchant, "Test-acs-01", TestAcs01);

        driver.manage().window().maximize();
        waitForPageLoad();

        // Upload File
        WebElement fileInput = driver.findElement(By.id("fileInput"));
        String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\WhiteList Merchant Ip\\WhiteList_MerchantIP.xlsx";
        fileInput.sendKeys(filePath);
        waitForUploadComplete();

        safeClick(By.id("btnimport"));

        // Add Whitelist Merchant IP
        safeClick(addWhiteListMerchantIp);
        safeClick(addWhiteListSelectAny);
        typeAndSelect(addWhiteListTestACS, "Test-acs-01", addWhiteListSelectTestAcs);

        // Fill IP Form
        typeText(By.id("IP"), "1.5.7.8");
        safeClick(By.id("IsActive"));
        safeClick(By.id("btnSave"));

        // Filter Records
        safeClick(sMasterMerchant2);
        typeAndSelect(searchWhiteListMasterMerchant2, "Test-acs-01", TestAcs03);
        typeText(By.id("txtSearch"), "1.5.7.8");
        safeClick(By.id("btnFilter"));

        // Capture Screenshot
        String screenshotName = "WhitelistMasterMerchantIP_Page_Screenshot";
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-WhitelistMasterMerchantIP", screenshotName);

        // Export Excel
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        safeClick(By.id("btnExport"));

        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("WhiteListMerchantIP", dateFolder);
        } else {
            log.warn("No downloaded Excel file found to move.");
        }

        // Delete all records safely
        deleteAllRecordsFromTable();

        log.info("==== WhiteList Merchant IP Test Completed ====");
    }

    // ===== Delete All Records =====
    private void deleteAllRecordsFromTable() {
        while (true) {
            List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[@class='fa fa-trash-o fa-lg']"));
            if (deleteButtons.isEmpty()) {
                log.info("No more records left to delete.");
                break;
            }
            WebElement deleteBtn = deleteButtons.get(0);
            safeClick(deleteBtn);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            wait.until(ExpectedConditions.stalenessOf(deleteBtn));
        }
    }

    // ===== Wait Helpers =====
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    private void waitForUploadComplete() {
        wait.until(driver -> driver.findElement(By.id("btnimport")).isEnabled());
    }

    // ===== Safe Click Helpers =====
    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); window.scrollBy(0,-150);", element);

            element.click();
            log.info("Clicked normally: " + element.toString());
        } catch (Exception e) {
            log.warn("Normal click failed, retrying with JS: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            log.info("Clicked with JS fallback: " + element.toString());
        }
    }

    private void safeClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // ===== Utility Wrappers =====
    private void typeAndSelect(WebElement searchBox, String text, WebElement optionToSelect) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(optionToSelect)).click();
    }

    private void typeText(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }
}

