package PG10PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== Locators =====
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='FTD White List User']")
    WebElement ftdWhiteList;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[1]")
    WebElement selectMasterMerchant;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
    WebElement searchMasterMerchant;
    
    // For UAT
//  @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//  WebElement TestAcs01;

    // For Production
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcs01;

    @FindBy(xpath = "//a[normalize-space()='Add FTDWhiteList User']")
    WebElement addWhiteListUser;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]")
    WebElement addWhiteListUserMasterMerchant;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement searchAddWhiteListUserMasterMerchant;
    
    // For UAT
//  @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//  WebElement TestAcs013;
    
    //For Production 
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcs013;
    
    @FindBy(xpath = "(//button[@class='btn btn-success'])[1]")
    WebElement close;

    @FindBy(xpath = "//div[@class='gutters row']//button[@title='Select Master Merchant']")
    WebElement selectMasterMerchant2;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[2]")
    WebElement searchMasterMerchant2;
    
    // For UAT
//  @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
//  WebElement TestAcs0135;
  
    //For Production 
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
    WebElement TestAcs0135;
   
    By duplicateDataError = By.xpath("//*[contains(text(),'Data Already Exist for this Merchant')]");

    // ===== Main Test Flow =====
    public void interactWithfraudControl_FTDwhiteListUser() throws IOException, InterruptedException {
        log.info("==== Starting FraudControl FTD White List User Test ====");

        safeClick(fraudControlMenu);
        safeClick(ftdWhiteList);

        // Select Master Merchant
        safeClick(selectMasterMerchant);
        typeAndSelect(searchMasterMerchant, "Test-acs-01", TestAcs01);

        // Upload Excel
        try {
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fileInput")));
            // String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\FTDWhiteList User\\FTDWhiteListUser.xlsx";
            // String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/FTDWhiteList User/FTDWhiteListUser.xlsx";
            String filePath = System.getProperty("user.dir") + "Upload Excel File/FTDWhiteList User/FTDWhiteListUser.xlsx";
            fileInput.sendKeys(filePath);
            log.info("File uploaded successfully.");
        } catch (Exception e) {
            log.error("File upload failed", e);
        }

        safeClick(By.id("btnimport"));
        waitForPageStable();

        // Add new WhiteList User
        safeClick(addWhiteListUser);
        safeClick(addWhiteListUserMasterMerchant);
        typeAndSelect(searchAddWhiteListUserMasterMerchant, "Test-acs-01", TestAcs013);

        typeText(By.id("firstName"), "Akash");
        typeText(By.id("lastName"), "Patel");
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
            log.info("No duplicate error, continuing execution.");
        }

        // Apply filters
        applyFilters();
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-FTDWhiteListUser", "FTDWhiteListUser_Page_Screenshot");

        // Wait for stability before delete
    //    waitForTableToLoad();
        
        Thread.sleep(2000);

        // Delete records
        deleteAllRecordsFromTable();
        
        Thread.sleep(2000);

        applyFilters();
        
        Thread.sleep(2000);
        
    //    waitForTableToLoad();

        deleteAllRecordsFromTable();
        
        Thread.sleep(2000);

        log.info("==== Completed FraudControl FTD White List User Test ====");
    }

    // ===== Helper Methods =====
    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'}); window.scrollBy(0,-150);", element);
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            log.warn("Performed JS click due to standard click failure on element: {}", element);
        }
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    // ===== Filter Logic =====
    private void applyFilters() {
        safeClick(selectMasterMerchant2);
        typeAndSelect(searchMasterMerchant2, "Test-acs-01", TestAcs0135);
        safeClick(By.id("btnFilter"));

        typeText(By.id("txtSearch"), "Akash");
        safeClick(By.id("btnFilter"));

        // Wait for results or no data message
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//table")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'No data available')]"))
        ));

        waitForTableToLoad();
        log.info("Filters applied and table data loaded successfully.");
    }

    // ===== Delete Logic =====
  
    // ===== Wait Helpers =====
    private void waitForTableToLoad() {
        try {
            wait.until(driver -> {
                List<WebElement> loaders = driver.findElements(By.xpath("//*[contains(@class,'spinner') or contains(text(),'Loading')]"));
                return loaders.isEmpty();
            });
        } catch (TimeoutException e) {
            log.warn("Timeout waiting for table load stabilization.");
        }
    }
    
    
    private void deleteAllRecordsFromTable() throws InterruptedException {
    
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
         
        Thread.sleep(2000);
  }

    private void waitForPageStable() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            wait.until(webDriver -> {
                try {
                    boolean ready = js.executeScript("return document.readyState").equals("complete");
                    boolean ajaxDone = (Boolean) js.executeScript("return (typeof jQuery === 'undefined') || jQuery.active === 0");
                    return ready && ajaxDone;
                } catch (Exception e) {
                    return true; // Fallback if jQuery not used
                }
            });
        } catch (TimeoutException e) {
            log.warn("Timeout waiting for page stability.");
        }
    }
}
