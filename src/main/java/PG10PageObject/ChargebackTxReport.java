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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class ChargebackTxReport {
    WebDriver driver;
    WebDriverWait wait;

    public ChargebackTxReport(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//div[@class='modal-dialog']//button[@type='button'][normalize-space()='Close']")
    WebElement CloseLimit;

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[3]/a/span")
    WebElement transactionsMenu;

    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
    WebElement bnibMenu;

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[3]/ul/li[1]/ul/li[6]/a")
    WebElement chargebackTxReport;

    @FindBy(xpath = "(//li[@data-range-key='Yesterday'])[1]")
    WebElement createdDateRangeyesterday;

    @FindBy(xpath = "(//li[@data-range-key='Yesterday'])[2]")
    WebElement cbOpenDateRange;

    @FindBy(xpath = "(//li[@data-range-key='Yesterday'])[3]")
    WebElement cbcloseDateRangeYesterday;

    @FindBy(xpath = "(//button[@title='Select Any'])[1]")
    WebElement masterMerchantSelectAny;

    @FindBy(xpath = "(//input[@placeholder='Search'])[1]")
    WebElement SearchMasterMerchant;
    
    // X Path For UAT 
    @FindBy(xpath = "//label[normalize-space(text())='Test-Acs-01']")
    WebElement SelectmasterMasterTestacs01;
    
    //x Path For Production 
    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01-MM']")
    WebElement SelectmasterMasterTestacs01L;

    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]")
    WebElement merchantSelectAny2;

    @FindBy(xpath = "(//input[@placeholder='Search'])[2]")
    WebElement searchMasterMerchant2;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[3]")
    WebElement subMerchantSelectAny3;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement SearchSubMerchant03;

    @FindBy(xpath = "//label[normalize-space(text())='Test-Acs-01-SM']")
    WebElement SelectSubMerchant03;

    @FindBy(id = "ddlCompleted")
    WebElement isClosedDropdown;

    public void interactWithtransactionchargebackTX() throws IOException {

        // Expand sidebar if collapsed
        expandSidebarIfCollapsed();

        // Transactions Menu
        clickElementWithFallback(By.xpath("//li[a/span[text()='Transactions']]/a/span"));

        // BNIB Menu
        clickElementWithFallback(By.id("submenuTxDropdown"));

        // Chargeback Tx Report
        clickElementWithFallback(By.xpath("/html/body/div[2]/div/nav/div/ul/li[3]/ul/li[1]/ul/li[6]/a"));

        // Date ranges
        clickElementWithFallback(By.id("txtCreatedDateRange"));
        clickElementWithFallback(By.xpath("(//li[@data-range-key='Yesterday'])[1]"));

        clickElementWithFallback(By.id("txtCBOpenDateRange"));
        clickElementWithFallback(By.xpath("(//li[@data-range-key='Yesterday'])[2]"));

        clickElementWithFallback(By.id("txtCBCloseDateRange"));
        clickElementWithFallback(By.xpath("(//li[@data-range-key='Yesterday'])[3]"));

        // Download Excel
        clickElementWithFallback(By.id("btnDownloadExcel"));

        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("ChargebackTxReport", dateFolder);
        } else {
            System.err.println(" No downloaded Excel file found to move.");
        }

        // Master Merchant
        clickElementWithFallback(By.xpath("(//button[@title='Select Any'])[1]"));
        WebElement masterSearch = wait.until(ExpectedConditions.visibilityOf(SearchMasterMerchant));
        masterSearch.sendKeys("Test-Acs-01");
        
        //X Path For UAT 
        // clickElementWithFallback(By.xpath("//label[normalize-space(text())='Test-Acs-01']"));
        
       //X Path For Production
        clickElementWithFallback(By.xpath("//label[normalize-space()='Test-Acs-01-MM']"));

        // Merchant 2
        clickElementWithFallback(By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"));
        searchMasterMerchant2.sendKeys("Test-Acs-01");
        clickElementWithFallback(By.xpath("//label[normalize-space(text())='Test-Acs-01-M']"));

        // Sub Merchant
        clickElementWithFallback(By.xpath("(//span[@class='multiselect-selected-text'])[3]"));
        SearchSubMerchant03.click();
        // X Path For UAT
        clickElementWithFallback(By.xpath("//label[normalize-space(text())='Test-Acs-01-SM']"));

        // Is Closed dropdown
        Select isClosed = new Select(isClosedDropdown);
        isClosed.selectByVisibleText("No");

        // Search
        clickElementWithFallback(By.id("btnSearch"));

        // Screenshot
        String screenshotName = "ChargebackTxReport_Page_Screenshot";
        System.out.println("Capturing full page screenshot...");
        CommonUtilis.captureFullPageScreenshot(driver, "Transaction-ChargebackTxReport", screenshotName);

        // Scroll to top
        ((JavascriptExecutor) driver).executeScript("window.scroll({ top: 0, behavior: 'smooth' });");
    }
    
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
}
