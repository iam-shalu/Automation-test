//package PG10PageObject;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.Date;
//import java.util.List;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import PG10utils.CommonUtilis;
//
//public class VPABlackList {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public VPABlackList(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//    }
//    // ====== Locators ======
//    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
//    WebElement fraudControlMenu;
//
//    @FindBy(xpath = "//a[normalize-space()='VPA BlackList']")
//    WebElement vpaBlackList;
//
//    @FindBy(xpath = "//a[normalize-space()='Add VPABlackList User']")
//    WebElement addVpaBlackListUser;
//
//    @FindBy(xpath = "//button[@class='multiselect dropdown-toggle btn btn-default']")
//    WebElement selectAny;
//
//    @FindBy(xpath = "//input[@class='form-control multiselect-search']")
//    WebElement searchSelectAny;
//
//    @FindBy(xpath = "//label[normalize-space()='FULL']")
//    WebElement fullOption;
//
//    @FindBy(xpath = "//span[@class=\"fa fa-trash-o fa-lg\"]")
//    WebElement deleteRecord;
//
//    // ====== Actions ======
//    public void interactWithfraudControlVPABlackList() throws IOException, InterruptedException {
//
//        // Try expanding sidebar (headless mode safe)
//        expandSidebarIfCollapsed();
//
//        // Navigate to Fraud Control → VPA BlackList
//        clickElementWithFallback(By.xpath("//span[normalize-space()='Fraud Control']"));
//        clickElementWithFallback(By.xpath("//a[normalize-space()='VPA BlackList']"));
//
//        // Upload Excel
//        WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("(//input[@class='form-control form-control-sm'])[1]")));
//        String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\VPA BlackList\\VPABlackList.xlsx";
//        blackListUpload.sendKeys(filePath);
//        System.out.println(" File uploaded successfully.");
//
//        clickElementWithFallback(By.id("btnimport"));
//
//        // Add new VPA Blacklist user
//        clickElementWithFallback(By.xpath("//a[normalize-space()='Add VPABlackList User']"));
//        clickElementWithFallback(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"));
//        clickElementWithFallback(By.xpath("//input[@class='form-control multiselect-search']"));
//        clickElementWithFallback(By.xpath("//label[normalize-space()='FULL']"));
//
//        WebElement vpaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VPA")));
//        vpaField.clear();
//        vpaField.sendKeys("4123499@paytm");
//
//        clickElementWithFallback(By.id("btn_Save"));
//
//        // Search for a record
//        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
//        searchBox.clear();
//        searchBox.sendKeys("4123466@paytm");
//        clickElementWithFallback(By.id("btnFilter"));
//
//        // Screenshot
//        String screenshotName = "VpaBlackList_Page_Screenshot";
//        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-VPA BlackList", screenshotName);
//
//        // Export
//        clickElementWithFallback(By.id("btnExport"));
//
//        // Handle File Move
//        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
//
//        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
//            CommonUtilis.moveDownloadedFileToDatedFolder("VPABlackList", dateFolder);
//        } else {
//            System.err.println(" No downloaded Excel file found to move.");
//        }
//
//        // Delete records (twice for confirmation)
//        deleteWithAlert(deleteRecord);
//         
//        WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
//        searchBox2.clear();
//        searchBox2.sendKeys("4123466@paytm");
//        clickElementWithFallback(By.id("btnFilter"));
//        
//        deleteWithAlert(deleteRecord);
//        
//    }
//
//    // ====== Utility Methods ======
//
//    /**
//     * Expand sidebar if menu is collapsed (headless mode fix)
//     */
//    private void expandSidebarIfCollapsed() {
//        try {
//            List<WebElement> toggles = driver.findElements(By.xpath("//button[contains(@class,'menu-toggle')]"));
//            if (!toggles.isEmpty() && toggles.get(0).isDisplayed()) {
//                toggles.get(0).click();
//                System.out.println("Sidebar expanded via toggle button.");
//            }
//        } catch (Exception e) {
//            System.out.println(" No sidebar toggle found, continuing...");
//        }
//    }
//
//    /**
//     * Robust element click: wait, scroll, fallback to JS click
//     */
//    private void clickElementWithFallback(By locator) throws IOException {
//        try {
//            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//            wait.until(ExpectedConditions.elementToBeClickable(el));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
//            el.click();
//        } catch (Exception e) {
//            System.out.println(" Normal click failed for " + locator + " - trying JS click.");
//            try {
//                WebElement el = driver.findElement(locator);
//                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//            } catch (Exception ex) {
//                CommonUtilis.captureFullPageScreenshot(driver, "debug", "clickFail_" + locator.toString());
//                throw new RuntimeException(" Could not click element: " + locator, ex);
//            }
//        }
//    }
//
//    /**
//     * Delete record with alert handling
//     */
//    private void deleteWithAlert(WebElement deleteBtn) {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
//            wait.until(ExpectedConditions.alertIsPresent());
//            driver.switchTo().alert().accept();
//            System.out.println("✅ Record deleted successfully.");
//        } catch (Exception e) {
//            System.out.println(" Delete action failed: " + e.getMessage());
//        }
//    }
//}

package PG10PageObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class VPABlackList {
    WebDriver driver;
    WebDriverWait wait;

    public VPABlackList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ====== Locators ======
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='VPA BlackList']")
    WebElement vpaBlackList;

    @FindBy(xpath = "//a[normalize-space()='Add VPABlackList User']")
    WebElement addVpaBlackListUser;

    @FindBy(xpath = "//button[@class='multiselect dropdown-toggle btn btn-default']")
    WebElement selectAny;

    @FindBy(xpath = "//input[@class='form-control multiselect-search']")
    WebElement searchSelectAny;

    @FindBy(xpath = "//label[normalize-space()='FULL']")
    WebElement fullOption;

    @FindBy(xpath = "//span[@class=\"fa fa-trash-o fa-lg\"]")
    WebElement deleteRecord;

    // ====== Actions ======
    public void interactWithfraudControlVPABlackList() throws IOException, InterruptedException {

        expandSidebarIfCollapsed();

        clickElementWithFallback(By.xpath("//span[normalize-space()='Fraud Control']"));
        clickElementWithFallback(By.xpath("//a[normalize-space()='VPA BlackList']"));

        // Upload Excel
        WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//input[@class='form-control form-control-sm'])[1]")));
        String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\VPA BlackList\\VPABlackList.xlsx";
        blackListUpload.sendKeys(filePath);
        System.out.println(" File uploaded successfully.");

        clickElementWithFallback(By.id("btnimport"));

        // Add new VPA Blacklist user
        clickElementWithFallback(By.xpath("//a[normalize-space()='Add VPABlackList User']"));
        clickElementWithFallback(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"));
        clickElementWithFallback(By.xpath("//input[@class='form-control multiselect-search']"));
        clickElementWithFallback(By.xpath("//label[normalize-space()='FULL']"));

        WebElement vpaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VPA")));
        vpaField.clear();
        vpaField.sendKeys("4123499@paytm");

        clickElementWithFallback(By.id("btn_Save"));

        // Search for a record
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
        searchBox.clear();
        searchBox.sendKeys("4123466@paytm");
        clickElementWithFallback(By.id("btnFilter"));

        // Screenshot
        String screenshotName = "VpaBlackList_Page_Screenshot";
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-VPA BlackList", screenshotName);

        // Export
        clickElementWithFallback(By.id("btnExport"));

        // Handle File Move
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("VPABlackList", dateFolder);
        } else {
            System.err.println(" No downloaded Excel file found to move.");
        }
        
   //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("4123466@paytm");
      
        // Delete records (twice for confirmation)
        deleteWithAlert(deleteRecord);
    //    deleteAllRecords();

        WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
        searchBox2.clear();
        searchBox2.sendKeys("4123466@paytm");
        clickElementWithFallback(By.id("btnFilter"));

        deleteWithAlert(deleteRecord);
        
     //   deleteAllRecords();
    }

    // ====== Utility Methods ======

    private void expandSidebarIfCollapsed() {
        try {
            List<WebElement> toggles = driver.findElements(By.xpath("//button[contains(@class,'menu-toggle')]"));
            if (!toggles.isEmpty() && toggles.get(0).isDisplayed()) {
                toggles.get(0).click();
                System.out.println("Sidebar expanded via toggle button.");
            }
        } catch (Exception e) {
            System.out.println(" No sidebar toggle found, continuing...");
        }
    }

    private void clickElementWithFallback(By locator) throws IOException {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(el));
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

    /**
     * ✅ Headless-compatible Delete action with JS click + alert wait + confirmation
     */
    private void deleteWithAlert(WebElement deleteBtn) {
        try {
            wait.until(ExpectedConditions.visibilityOf(deleteBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
            System.out.println(" Delete button clicked via JS.");

            // Wait for alert (headless-safe)
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println(" Alert text: " + alert.getText());
            alert.accept();

            // Wait for page to refresh / record removal
            wait.until(ExpectedConditions.invisibilityOf(deleteBtn));
            System.out.println("✅ Record deleted successfully (headless mode safe).");

        } catch (Exception e) {
            System.out.println(" Delete action failed: " + e.getMessage());
            try {
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "deleteFail");
            } catch (IOException io) {
                System.out.println(" Failed to capture screenshot on delete fail: " + io.getMessage());
            }
        }
    }
    
    /**
     * ✅ Reliable delete action (dynamic table + headless-safe)
     */
    /**
     * ✅ Delete all visible records from VPA BlackList (headless-safe)
     */

   
}



