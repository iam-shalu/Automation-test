package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class WhiteListCustomer {
    WebDriver driver;
    WebDriverWait wait;

    public WhiteListCustomer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlMenu;

    @FindBy(xpath = "//a[normalize-space()='White List Customer']")
    WebElement whiteListCustomer;

    @FindBy(xpath = "//button[@title='Select Master Merchant']")
    WebElement smasterMerchantDropdown;

    @FindBy(xpath = "//input[@class='form-control multiselect-search']")
    WebElement searchSelectMasterMerchant;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement selectWhiteListMasterMerchant;

    @FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
    WebElement addWhiteListCust;

    @FindBy(xpath = "//button[@class='multiselect dropdown-toggle btn btn-default']")
    WebElement selectAny;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchWhiteList;

    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01']")
    WebElement Testacs;

    @FindBy(xpath = "(//button[@title='Select Master Merchant'])[2]")
    WebElement smasterMerchantDropdown2;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[2]")
    WebElement searchWhiteList2;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement Testacs2;

    // First delete button
    By deleteLocator1 = By.xpath("(//span[@class='fa fa-trash-o  fa-lg'])[1]");
    
    // Generic delete locator for fallback
    By deleteLocator2 = By.xpath("//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]");

    public void interactWithfraudControl_whiteListCustomer() throws IOException, InterruptedException {
        expandSidebarIfCollapsed();

        // Fraud Control → White List Customer
        clickElementWithFallback(By.xpath("//span[normalize-space()='Fraud Control']"));
        clickElementWithFallback(By.xpath("//a[normalize-space()='White List Customer']"));

        // Master Merchant selection
        clickElementWithFallback(By.xpath("//button[@title='Select Master Merchant']"));
        searchSelectMasterMerchant.sendKeys("Test-Acs-01");
        
        //For UAT
    //    clickElementWithFallback(By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']"));
        
        //For Production
        clickElementWithFallback(By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']"));

        driver.manage().window().maximize();

        // Upload Excel
        try {
            WebElement upload = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fileInput")));
            // String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\Whitelist Customer\\WhiteList.xlsx";
			// String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/Whitelist Customer/WhiteList.xlsx";
			 String filePath = System.getProperty("user.dir") + "Upload Excel File/Whitelist Customer/WhiteList.xlsx";
            upload.sendKeys(filePath);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
            System.out.println(" File uploaded successfully.");
        } catch (Exception e) {
            CommonUtilis.captureFullPageScreenshot(driver, "debug", "uploadFail");
            throw new RuntimeException(" File upload failed", e);
        }

        clickElementWithFallback(By.id("btnimport"));
        clickElementWithFallback(By.xpath("//a[@class='btn btn-info btn-sm']")); // Add WhiteList
        clickElementWithFallback(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"));
        searchWhiteList.sendKeys("Test-acs-01");
        
        //For UAT
    //    clickElementWithFallback(By.xpath("//label[normalize-space()='Test-Acs-01']"));
        
        //For Production
        clickElementWithFallback(By.xpath("//label[normalize-space()='Test-Acs-01-MM']"));
        // Fill details
        typeText(By.id("FirstName"), "Akash");
        typeText(By.id("LastName"), "Lade");
        typeText(By.id("Email"), "akash@gmail.com");
        typeText(By.id("Phone"), "9632629036");
        typeText(By.id("IP"), "1.1.1.3");
        clickElementWithFallback(By.id("IsActive"));
        clickElementWithFallback(By.id("btn-submit"));

        // Export Excel
        clickElementWithFallback(By.xpath("(//button[@title='Select Master Merchant'])[2]"));
        searchWhiteList2.sendKeys("Test-acs-01");
        
        //For UAT
     //   clickElementWithFallback(By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']"));
        
        //For Production
        clickElementWithFallback(By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']"));
        
        typeText(By.id("txtSearch"), "Akash");
        clickElementWithFallback(By.id("btnFilter"));
        clickElementWithFallback(By.id("btnExport"));

        // Handle File Move
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("WhiteListCustomer", dateFolder);
        } else {
            System.err.println(" No downloaded Excel file found to move.");
        }

        // Screenshot
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-WhiteListCustomer", "WhiteListCustomer_Page_Screenshot");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        
        Thread.sleep(2000);

        // Delete records (with retry)
        deleteWithRetry();
        
        waitForTableToLoad();
        
    }

    private void waitForTableToLoad() {
    	 try {
             wait.until(driver -> {
                 List<WebElement> loaders = driver.findElements(By.xpath("//*[contains(@class,'spinner') or contains(text(),'Loading')]"));
                 return loaders.isEmpty();
             });
         } catch (TimeoutException e) {
            // log.warn("Timeout waiting for table load stabilization.");
         }
	
	}

	/**
     * Expand sidebar if collapsed (for headless mode)
     */
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

    /**
     * Robust click with scroll + JS fallback
     */
    private void clickElementWithFallback(By locator) throws IOException {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(el));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        } catch (Exception e) {
            System.out.println("⚠️ Normal click failed for " + locator + " - trying JS click.");
            try {
                WebElement el = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ex) {
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "clickFail_" + locator.toString());
                throw new RuntimeException("Could not click element: " + locator, ex);
            }
        }
    }

    private void typeText(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Delete with retry & JS fallback
     */
    private void deleteWithRetry() {
        try {
            clickElementWithFallback(deleteLocator1);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            clickElementWithFallback(deleteLocator2);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            System.out.println("✅ Records deleted successfully.");
        } catch (Exception e) {
            System.out.println("⚠️ Primary delete failed, trying fallback...");
            try {
                WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='dt-type-numeric sorting_1 dtr-control']")));
                row.click();
                clickElementWithFallback(By.xpath("(//span[@class='fa fa-trash-o  fa-lg'])[2]"));
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
                System.out.println(" Fallback delete worked.");
            } catch (Exception inner) {
                throw new RuntimeException("Delete failed completely", inner);
            }
        }
    }
    
    
}

