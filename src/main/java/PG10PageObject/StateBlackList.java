package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class StateBlackList {
    WebDriver driver;
    WebDriverWait wait;

    public StateBlackList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    // ==== Locators ====
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlManu;

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[4]/ul/li[9]/a")
    WebElement stateBlackList;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[1]")
    WebElement SubMarchant1;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
    WebElement searchSubMerchant1;

    // X Path For UAT Environment & Production Same
    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
    WebElement Testacs1;
    
    //For Production
    //ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']
   
    @FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
    WebElement addStateBlackList;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[3]")
    WebElement selectSubElement;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement searchselectSubElement1;

    // X Path For UAT Environment
    @FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
    WebElement Testacs2;
    
    @FindBy(xpath = "//h3[normalize-space()='State BlackList Details']")
    WebElement StateBlackListText;

    @FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
    WebElement deleteChg;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/a[2]/span[1]")
    WebElement deleteka;

    // ==== Helper: safeClick ====
    private void safeClick(WebElement element, String name) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            System.out.println("Clicked normally: " + name);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            System.out.println("Clicked with JS fallback: " + name);
        }
    }

    // ==== Helper: retry click (for dynamic elements) ====
    public boolean retryingFindClick(By locator, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return true;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
            } catch (Exception e) {
                System.err.println("Non-stale error during clicking: " + e.getMessage());
                break;
            }
        }
        return false;
    }

    // ==== Main Flow ====
    public void interactWithfraudControlStateblackList() throws InterruptedException, IOException {
        // Click Fraud Control menu
        safeClick(fraudControlManu, "Fraud Control");

        // Click State BlackList submenu
        safeClick(stateBlackList, "State BlackList");

        // Select SubMerchant
        safeClick(SubMarchant1, "SubMerchant dropdown");
        wait.until(ExpectedConditions.visibilityOf(searchSubMerchant1)).sendKeys("Test-acs-01");
        safeClick(Testacs1, "Test-acs-01 SubMerchant");

        // Upload Excel
        WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));
        String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\State BlackList\\StateBlackList.xlsx";
        blackListUpload.sendKeys(filePath);
        System.out.println("File uploaded successfully.");

        safeClick(driver.findElement(By.id("btnimport")), "Import Button");
        safeClick(addStateBlackList, "Add State BlackList");

        // Select Sub element
        safeClick(selectSubElement, "SubElement dropdown");
        wait.until(ExpectedConditions.visibilityOf(searchselectSubElement1)).sendKeys("Test-acs-01");
        safeClick(Testacs2, "Test-acs-01 SubElement");

        // Enter State & Save
        wait.until(ExpectedConditions.elementToBeClickable(By.id("State"))).sendKeys("Chattisgarh");
        safeClick(driver.findElement(By.id("btn_Save")), "Save Button");

        // Retry dropdown clicks
        try {
            boolean dropdownClicked = retryingFindClick(
                By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"), 3);
            if (!dropdownClicked) throw new RuntimeException("Failed to click SubMerchant dropdown");

            boolean selectAllClicked = retryingFindClick(
                By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]"), 3);
            if (!selectAllClicked) throw new RuntimeException("Failed to click Select All checkbox");

            WebElement freshSearchBox = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//input[@class='form-control multiselect-search'])[2]")));
            freshSearchBox.clear();
            freshSearchBox.sendKeys("Test-acs-01", Keys.ENTER);

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")));
            checkbox.click();
            System.out.println("Checkbox for Test-Acs-01 clicked successfully.");

        } catch (Exception e) {
            System.err.println("Error in SubMerchant dropdown interaction: " + e.getMessage());
        }

        // Filter + Screenshot
        safeClick(driver.findElement(By.id("btnFilter")), "Filter Button");
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-State BlackList", "StateBlackList_Page_Screenshot");

        // Scroll top
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        // Search and Export
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Chattisgarh");
        safeClick(driver.findElement(By.id("btnFilter")), "Filter Button Again");
        safeClick(driver.findElement(By.id("btnExport")), "Export Button");

        // Move Exported File
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("StateBlackList", dateFolder);
        } else {
            System.err.println("No downloaded Excel file found to move.");
        }

        // Delete steps
        safeClick(deleteChg, "Delete (chg)");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Karnataka");
        safeClick(driver.findElement(By.id("btnFilter")), "Filter Button Again");
        safeClick(driver.findElement(By.id("btnExport")), "Export Button");
       
        safeClick(deleteka, "Delete (ka)");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        System.out.println("State BlackList flow completed successfully ");
    }
}
