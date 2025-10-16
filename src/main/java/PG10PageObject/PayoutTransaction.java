package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import PG10utils.CommonUtilis;

public class PayoutTransaction {
    WebDriver driver;
    WebDriverWait wait;

    public PayoutTransaction(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ---------- Elements ----------
    @FindBy(xpath = "(//span[@class='nav-item'])[3]")
    WebElement transactionsMenu;

    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
    WebElement bnibMenu;
    
//	// For UAT 
//	@FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
//	WebElement payoutTxdateLast7Days;

    @FindBy(xpath = "//a[@href='/BNIBPayout/Transactions']")
    WebElement payoutTxs;
    
    
//	//UAT 
//	@FindBy(xpath = "//li[@class='active']")
//	WebElement dateLast7days;
//	
//	//Production
//	@FindBy(xpath = "//Li[@data-range-key=\"Yesterday\"]")
//	WebElement Yesterday;

    @FindBy(xpath = "//input[@id='txtDateRange']")
    WebElement dateRange;

    @FindBy(xpath = "//Li[@data-range-key='Yesterday']")
    WebElement yesterdayOption;

    @FindBy(xpath = "//button[@id='btnExport']")
    WebElement exportButton;
    // For Production
    @FindBy(xpath = "(//td[@class='dt-type-numeric sorting_1 dtr-control'])[1]")
    WebElement payoutTxId;
    
//	//For UAT
//	@FindBy(xpath = "(//td[@class=\"dt-type-numeric sorting_1 dtr-control\"])[1]")
//	WebElement PayoutId;
//	
//	//For Live 
//	@FindBy(xpath = "(//td[@class=\"dt-type-numeric sorting_1 dtr-control\"])[1]")
//	WebElement PayoutTxId;

    @FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
    WebElement showDetails;

    @FindBy(xpath = "(//button[@class='btn btn-default waves-effect'])[1]")
    WebElement closeButton;

    // ---------- Main Interaction ----------
    public void interactWithtransactionPayoutTxs() throws IOException {
        expandSidebarIfCollapsed();

        try {
            Thread.sleep(1000);

            // Navigate through menu
            clickElementWithFallback(By.xpath("(//span[@class='nav-item'])[3]"));
            clickElementWithFallback(By.xpath("//a[@id='submenuTxDropdown']"));
            clickElementWithFallback(By.xpath("//a[@href='/BNIBPayout/Transactions']"));

            // Select date range
            clickElementWithFallback(By.xpath("//input[@id='txtDateRange']"));
            clickElementWithFallback(By.xpath("//Li[@data-range-key='Yesterday']"));

            // Select Cutoff Time
            WebElement cutoffTimeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlCutOffTime")));
            new Select(cutoffTimeDropdown).selectByVisibleText("12 AM to 12 AM");

            // Filter + Export
            clickElementWithFallback(By.id("frmsearch"));
            clickElementWithFallback(By.id("btnExport"));

            // Wait for Excel and move
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

            if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                CommonUtilis.moveDownloadedFileToDatedFolder("PayoutTransaction", dateFolder);
            } else {
                System.err.println(" No downloaded Excel file found to move.");
            }

            // View Tx details
            clickElementWithFallback(By.id("dt-length-1"));
            clickElementWithFallback(By.xpath("(//td[@class='dt-type-numeric sorting_1 dtr-control'])[1]"));

            CommonUtilis.takeScreenshot(driver, "Payout Transactions", "Payout_Tx_List");
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

            clickElementWithFallback(By.xpath("//span[@class='dtr-data']//i[@class='fa fa-server']"));
            clickElementWithFallback(By.xpath("(//button[@class='btn btn-default waves-effect'])[1]"));

            System.out.println(" Payout Transactions Test Completed Successfully.");

        } catch (Exception e) {
            CommonUtilis.captureFullPageScreenshot(driver, "debug", "PayoutTransaction_Failure");
            System.err.println(" Unexpected error in Payout Transaction flow: " + e.getMessage());
        }
    }

    // ---------- Utility Methods ----------

    /**
     * Expands sidebar if it’s collapsed (for headless mode)
     */
    private void expandSidebarIfCollapsed() {
        try {
            List<WebElement> toggles = driver.findElements(By.xpath("//button[contains(@class,'menu-toggle')]"));
            if (!toggles.isEmpty() && toggles.get(0).isDisplayed()) {
                toggles.get(0).click();
                System.out.println("Sidebar expanded via toggle button.");
            }
        } catch (Exception e) {
            System.out.println(" No sidebar toggle found or already expanded.");
        }
    }

    /**
     * Robust click method with scroll & JS fallback
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
}
