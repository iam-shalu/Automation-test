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

public class SearchTxHistory {
    WebDriver driver;
    WebDriverWait wait;

    public SearchTxHistory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//span[normalize-space()='Transactions']")
    WebElement transactionsMenu;

    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
    WebElement bnibMenu;

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[3]/ul/li[1]/ul/li[5]/a")
    WebElement searchTxHist;
    
    @FindBy(xpath = "//td[@class='sorting_1 dtr-control']")
    WebElement ChargebackId;

    @FindBy(xpath = "//h3[normalize-space()='Search Tx History List']")
    WebElement SearchTxHistoryPage;

    public void interactWithtransactionsSearchTxHist() throws IOException {

        // Try expanding sidebar if collapsed
        expandSidebarIfCollapsed();

        // Transactions Menu (with fallback + JS click)
        clickElementWithFallback(By.xpath("//span[normalize-space()='Transactions']"));

        // BNIB Menu
        clickElementWithFallback(By.id("submenuTxDropdown"));

        // Search Tx History
        clickElementWithFallback(By.xpath("//a[normalize-space()='Search Tx History']"));

        // Dropdown (Search Param)
        WebElement searchFieldDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ddlSearchParam")));
        Select dropdown = new Select(searchFieldDropdown);
        dropdown.selectByVisibleText("ChargeBack Tx Id");

        // Enter Search Value
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearchValue")));
        searchBox.clear();
        searchBox.sendKeys("123");

        // Click Filter
        clickElementWithFallback(By.id("btnFilter"));

        // Select Chargeback ID Row
        wait.until(ExpectedConditions.visibilityOf(ChargebackId));
        clickElementWithFallback(By.xpath("//td[@class='sorting_1 dtr-control']"));

        // Download Excel
        clickElementWithFallback(By.id("btnDownloadExcel"));

        // Handle File Move
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

        if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
            CommonUtilis.moveDownloadedFileToDatedFolder("SearchTx History", dateFolder);
        } else {
            System.err.println(" No downloaded Excel file found to move.");
        }

        // Screenshot
        String screenshotName = "SearchTxHistory_Page_Screenshot";
        System.out.println(" Capturing full page screenshot...");
        CommonUtilis.captureFullPageScreenshot(driver, "Transaction-SearchTxHistory", screenshotName);

        // Scroll to top after screenshot
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Expand sidebar if menu is collapsed (for headless mode)
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
     * Robust element click: wait, scroll, fallback to JS click
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
                // Capture screenshot before failing
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "clickFail_" + locator.toString());
                throw new RuntimeException(" Could not click element: " + locator, ex);
            }
        }
    }
}
