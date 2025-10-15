package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import PG10utils.CommonUtilis;

public class DepositTransaction {
    WebDriver driver;
    WebDriverWait wait;

    public DepositTransaction(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//span[normalize-space()='Transactions']")
    WebElement transactionsMenu;

    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
    WebElement bnibMenu;

    @FindBy(xpath = "//a[normalize-space()='Deposit Txs']")
    WebElement depositTxsOption;

    @FindBy(xpath = "//input[@id='txtDateRange']")
    WebElement dateRange;

    @FindBy(xpath = "//button[@id='btnfiltersearch']")
    WebElement filter;

    @FindBy(xpath = "//button[@id='btnDownloadExcel']")
    WebElement export;

    @FindBy(xpath = "(//td[@class='dt-type-numeric sorting_1 dtr-control'])[1]")
    WebElement txId;

    @FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
    WebElement txAction;

    @FindBy(xpath = "//h3[normalize-space()='Deposit Txs']")
    WebElement depositTxText;

    @FindBy(xpath = "/html/body/div[2]/div/header/div/div/div/div/h3")
    WebElement viewTxButton;

    public void interactWithDepositTransactions() throws IOException {
        try {
            System.out.println("==== Starting Deposit Transactions Test ====");

            // Navigate to Deposit Transactions
            clickElementWithFallback(transactionsMenu);
            clickElementWithFallback(bnibMenu);
            clickElementWithFallback(depositTxsOption);

            // Select Date Range: Yesterday
            clickElementWithFallback(dateRange);
            
          //For Production
            clickElementWithFallback(By.xpath("//Li[@data-range-key='Yesterday']"));
            
           // For UAT
          //li[@data-range-key="Last 7 Days"]
            
          //For UAT
      //      clickElementWithFallback(By.xpath("//li[@data-range-key=\"Last 7 Days\"]"));

            // Filter & Export
            clickElementWithFallback(filter);
            clickElementWithFallback(export);

            // Wait for Excel download and move
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
            if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                CommonUtilis.moveDownloadedFileToDatedFolder("DepositTransactions", dateFolder);
            } else {
                System.err.println("No downloaded Excel file found to move.");
            }

            // Click transaction to view details
            clickElementWithFallback(txId);
            wait.until(ExpectedConditions.visibilityOf(depositTxText));
            CommonUtilis.takeScreenshot(driver, "Deposit Transactions", "DepositTxText");

            // Click action icon
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
            clickElementWithFallback(txAction);

            // Switch to new window and capture
            String originalWindow = driver.getWindowHandle();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) driver.switchTo().window(handle);
            }
            wait.until(ExpectedConditions.visibilityOf(viewTxButton));
            CommonUtilis.takeScreenshot(driver, "Deposit Transactions", "View_Tx_Details");
            driver.close();
            driver.switchTo().window(originalWindow);

            // Search within Deposit Transactions (hardcoded value)
            Select searchDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fieldname1"))));
            searchDropdown.selectByValue("iPayinfo");

            Select selectFilterType = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filtertype1"))));
            selectFilterType.selectByValue("Equals");

            WebElement enterValueBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchval1")));
            enterValueBox.clear();
            enterValueBox.sendKeys("gomzi001@axl");

            System.out.println("==== Deposit Transactions Test Completed ====");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    // Fallback click: wait, scroll, JS click if normal fails
    private void clickElementWithFallback(WebElement element) throws IOException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            System.out.println("Normal click failed, trying JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void clickElementWithFallback(By locator) throws IOException {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        } catch (Exception e) {
            System.out.println("Normal click failed for locator, trying JS click.");
            WebElement el = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            
        }
    }
}
