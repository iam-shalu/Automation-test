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
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class CityBlackList {
    WebDriver driver;
    WebDriverWait wait;

    public CityBlackList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='City BlackList']")
    WebElement cityBlackListLink;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[1]")
    WebElement subMasterMerchantDropdown1;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
    WebElement searchMasterMerchant1;
    
    //FOR uat & Production Env.
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
    WebElement testAcs1;

    @FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
    WebElement addCityBlackListBtn;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]")
    WebElement subMasterMerchantDropdown2;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement searchSubTestAcs2;

    @FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
    WebElement selectSubTestAcs2;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[2]")
    WebElement subMasterMerchantDropdown3;

    @FindBy(xpath = "//li[@class='multiselect-item multiselect-all active']//label[@class='checkbox'][normalize-space()='Select all']")
    WebElement selectAllBtn3;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[2]")
    WebElement searchSubMasterMerchant3;
    
    //UAT & Production Env. same 
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
    WebElement testAcs3;

    @FindBy(xpath = "//h3[normalize-space()='City BlackList Details']")
    WebElement cityBlackListDetailsPage;

    @FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
    WebElement deleteRecordBtn;

    /**
     * Main method to interact with City BlackList page
     */
    public void interactWithFraudControlBlackList() throws IOException {
        try {
            // Expand sidebar if collapsed
            expandSidebarIfCollapsed();

            // Navigate menus
            clickElementWithFallback(fraudControlMenu, By.xpath("//span[normalize-space()='Fraud Control']"));
            clickElementWithFallback(cityBlackListLink, By.xpath("//a[normalize-space()='City BlackList']"));

            // Select Sub-Master Merchant (first dropdown)
            clickElementWithFallback(subMasterMerchantDropdown1, By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[1]"));
            searchMasterMerchant1.sendKeys("Test-acs-01");
            clickElementWithFallback(testAcs1, By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']"));

            // Upload Excel
            WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));
            String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\CityBlackList Customer\\CityBlackList.xlsx";
            blackListUpload.sendKeys(filePath);
            System.out.println("✅ File uploaded successfully.");

            clickElementWithFallback(By.id("btnimport"));

            // Add City BlackList entry
            clickElementWithFallback(addCityBlackListBtn, By.xpath("//a[@class='btn btn-info btn-sm']"));

            // Select sub-merchant (second dropdown)
            clickElementWithFallback(subMasterMerchantDropdown2, By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]"));
            searchSubTestAcs2.sendKeys("Test-acs-01");
            clickElementWithFallback(selectSubTestAcs2, By.xpath("//label[@class='radio'][normalize-space()='Test-Acs-01-SM']"));

            // Enter city name & save
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("City"))).sendKeys("Surat");
            clickElementWithFallback(By.id("btn_Save"));

            // Third dropdown filter
            clickElementWithFallback(subMasterMerchantDropdown3, By.xpath("(//span[@class='multiselect-selected-text'])[2]"));
            clickElementWithFallback(selectAllBtn3, By.xpath("//li[@class='multiselect-item multiselect-all active']//label[@class='checkbox'][normalize-space()='Select all']"));
            searchSubMasterMerchant3.sendKeys("Test-acs-01");
            clickElementWithFallback(testAcs3, By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']"));
            clickElementWithFallback(By.id("btnFilter"));

            // Export Excel
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Surat");
            clickElementWithFallback(By.id("btnExport"));

            // Handle download file
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
            if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                CommonUtilis.moveDownloadedFileToDatedFolder("CityBlackList", dateFolder);
            } else {
                System.err.println(" No downloaded Excel file found to move.");
            }

            // Screenshot
            CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-City BlackList", "CityBlackListDetails_Page_Screenshot");

            // Delete record
            clickElementWithFallback(deleteRecordBtn, By.xpath("//span[@class='fa fa-trash-o fa-lg']"));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).clear();           
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Bangalore");
            
            clickElementWithFallback(By.id("btnFilter"));
            
            // Delete record
            clickElementWithFallback(deleteRecordBtn, By.xpath("//span[@class='fa fa-trash-o fa-lg']"));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            

        } catch (Exception e) {
            CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-City BlackList", "Error_Capture_" + System.currentTimeMillis());
            throw new RuntimeException(" Error in CityBlackList flow", e);
        }
    }

    /**
     * Expand sidebar if collapsed
     */
    private void expandSidebarIfCollapsed() {
        try {
            List<WebElement> toggles = driver.findElements(By.xpath("//button[contains(@class,'menu-toggle')]"));
            if (!toggles.isEmpty() && toggles.get(0).isDisplayed()) {
                toggles.get(0).click();
                System.out.println("✅ Sidebar expanded via toggle button.");
            }
        } catch (Exception e) {
            System.out.println("ℹ️ No sidebar toggle found, continuing...");
        }
    }

    /**
     * Robust click: element + JS fallback
     */
    private void clickElementWithFallback(WebElement element, By locator) throws IOException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            System.out.println("  Normal click failed for " + locator + " - trying JS click.");
            try {
                WebElement el = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ex) {
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "clickFail_" + locator.toString());
                throw new RuntimeException(" Could not click element: " + locator, ex);
            }
        }
    }

    private void clickElementWithFallback(By locator) throws IOException {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        
        } catch (Exception e) {
            System.out.println(" Normal click failed for " + locator + " - trying JS click.");
            
            try {
                WebElement el = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ex) {
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "clickFail_" + locator.toString());
                throw new RuntimeException(" Could not click element: " + locator, ex);
           
            }
        }
    }
}
