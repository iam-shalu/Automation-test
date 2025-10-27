
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Payout_BlackListCustomer {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(Payout_BlackListCustomer.class);

    public Payout_BlackListCustomer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ==== Locators ====
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='BlackList Payout Customer']")
    WebElement payoutBlackListCustomer;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
    WebElement sMasterMerchant;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
    WebElement sSearchMasterMerchant;
    // For UAT 
//   @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//   WebElement TestAcs01;
    
    // For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcs01;

    @FindBy(xpath = "(//button[@class=\"btn btn-info btn-sm\"])[1]")
    WebElement addPayoutBlackList;

    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[3]")
    WebElement addsMasterMerchant;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
    WebElement sSearchMasterMerchant3;
    // For UAT	
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement selectTestAcs013;
    
    // For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement selectTestAcs013;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
    WebElement sMasterMerchant2;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
    WebElement searchMasterMerchant2;
    // For UAT
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement TestAcsMasterMerch03;
    
    //For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcsMasterMerch03;
    


    // ==== Main Flow ====
    public void interactWithfraudControlPayoutblackListCust() throws IOException {
        log.info("==== Starting FraudControl Blacklist Payout Customer Test ====");

        // Navigate to menu
        safeClick(fraudControlMenu);
        safeClick(payoutBlackListCustomer);

        // Select Master Merchant
        safeClick(sMasterMerchant);
        typeAndSelect(sSearchMasterMerchant, "Test-acs-01", TestAcs01);

        // Upload Excel
        
        try {
            WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fileInput")));
            // String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\PayoutBlackList Customer\\BlacklistPayoutCustomer.xlsx";
            // String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/PayoutBlackList Customer/BlacklistPayoutCustomer.xlsx";
            String filePath = System.getProperty("user.dir") + "Upload Excel File/PayoutBlackList Customer/BlacklistPayoutCustomer.xlsx";
            blackListUpload.sendKeys(filePath);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
            log.info("File uploaded successfully.");
        } catch (Exception e) {
            log.error("Error during file upload", e);
        }

        safeClick(By.id("btnimport"));

        // Add Payout BlackList
        safeClick(addPayoutBlackList);
        safeClick(addsMasterMerchant);
        typeAndSelect(sSearchMasterMerchant3, "Test-acs-01", selectTestAcs013);

        typeText(By.id("AccountNo"), "1234234346789");
        typeText(By.id("BankIFSC"), "ICICI008");
        safeClick(By.id("btnSave"));

        // Filter and export
        safeClick(sMasterMerchant2);
        typeAndSelect(searchMasterMerchant2, "Test-acs-01", TestAcsMasterMerch03);
        safeClick(By.id("btnFilter"));
        safeClick(By.id("btnExport"));

        // Move downloaded file
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("PayoutBlackListTransaction", dateFolder);
        } else {
            log.warn("No downloaded Excel file found to move.");
        }

        // Screenshot
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-PayoutBlackList", "PayoutblackList_Page_Screenshot");

        // Search & Delete records safely
   //     deleteRecordByAccountNo("1234234346789");
   //     deleteRecordByAccountNo("1234234346796");
        
        deleteAllRecordsFromTable();
        
        log.info("==== Completed FraudControl Blacklist Payout Customer Test ====");
    }

    private void deleteAllRecordsFromTable() {
        while (true) {
            List<WebElement> deleteButtons = driver.findElements(By.xpath("//i[@class=\"fa fa-trash-o fa-lg\"]"));
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

    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'}); window.scrollBy(0,-150);", element);
            element.click();
            log.info("Clicked normally: " + element.toString());
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            log.info("Clicked with JS fallback: " + element.toString());
        }
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        safeClick(element);
    }

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
