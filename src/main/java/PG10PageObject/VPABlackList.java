package PG10PageObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
        vpaField.sendKeys("4123466@paytm");

        clickElementWithFallback(By.id("btn_Save"));

        // Search for a record
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
        searchBox.clear();
        searchBox.sendKeys("4123466@paytm");
        clickElementWithFallback(By.id("btnFilter"));

        // Screenshot
        String screenshotName = "VpaBlackList_Page_Screenshot";
        CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-VPA BlackList", screenshotName);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

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

        // Delete record (dynamic and headless-safe)
        deleteVpaRecord("4123466@paytm");
        Thread.sleep(2000);

        // Double-check deletion (optional)
        deleteVpaRecord("4123466@paytm");
        Thread.sleep(2000);
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
     * Robust headless-safe delete logic:
     *  - enlarge viewport (helps DataTables responsive behavior in headless)
     *  - re-search for VPA
     *  - extract record id from first <td>
     *  - call DeleteRow(recordId) via JS (preferred), with fallbacks
     */
    private void deleteVpaRecord(String vpaValue) throws InterruptedException, IOException {
        try {
            // Attempt to set a large viewport (helpful for headless runs)
            try {
                driver.manage().window().setSize(new Dimension(1920, 1080));
            } catch (Exception e) {
                // some remote setups may not allow resize — ignore if it fails
            }

            // Re-search the VPA to make sure row is loaded
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
            searchBox.clear();
            searchBox.sendKeys(vpaValue);
            clickElementWithFallback(By.id("btnFilter"));

            // Wait until row for that VPA is visible
            By rowLocator = By.xpath("//td[normalize-space()='" + vpaValue + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));

            // Extract the record id from the first cell of the same row:
            // assuming structure: <td>id</td><td>vpa</td>...action td
            By idLocator = By.xpath("//td[normalize-space()='" + vpaValue + "']/preceding-sibling::td[1]");
            WebElement idTd = wait.until(ExpectedConditions.visibilityOfElementLocated(idLocator));
            String recordId = idTd.getText().trim();
            System.out.println(" Found record id for VPA " + vpaValue + " => " + recordId);

            // Preferred: call the page's DeleteRow JS function directly (most reliable)
            try {
                String script = "if(typeof DeleteRow === 'function'){ DeleteRow(arguments[0]); return true;} return false;";
                Object result = ((JavascriptExecutor) driver).executeScript(script, recordId);
                if (result instanceof Boolean && ((Boolean) result)) {
                    System.out.println(" Invoked DeleteRow('" + recordId + "') via JS.");
                } else {
                    System.out.println(" DeleteRow function not found / not invoked — will try anchor click fallback.");
                    throw new RuntimeException("DeleteRow not executed");
                }
            } catch (Exception jsEx) {
                // Fallback: find anchor with onclick and JS-click it
                try {
                    By anchorLocator = By.xpath("//td[normalize-space()='" + vpaValue + "']/following-sibling::td[last()]//a[contains(@onclick,'DeleteRow')]");
                    WebElement anchor = wait.until(ExpectedConditions.elementToBeClickable(anchorLocator));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", anchor);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", anchor);
                    System.out.println(" JS-clicked anchor Delete for recordId: " + recordId);
                } catch (Exception anchorEx) {
                    // Final fallback: click the span element
                    By spanLocator = By.xpath("//td[normalize-space()='" + vpaValue + "']/following-sibling::td[last()]//span[contains(@class,'fa-trash-o')]");
                    WebElement span = wait.until(ExpectedConditions.presenceOfElementLocated(spanLocator));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", span);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", span);
                    System.out.println(" JS-clicked span Delete for recordId: " + recordId);
                }
            }

            // Wait for alert and accept (if present)
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                System.out.println(" Alert text: " + alert.getText());
                alert.accept();
            } catch (Exception noAlert) {
                // Some pages may use custom modal instead of browser alert - handle if needed
                System.out.println(" No browser alert present after delete action (or handled by custom modal).");
            }

            // Wait for the record row to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(rowLocator));
            System.out.println("✅ Record deleted successfully: " + vpaValue);

        } catch (Exception e) {
            System.out.println("❌ Delete action failed for " + vpaValue + ": " + e.getMessage());
            try {
                CommonUtilis.captureFullPageScreenshot(driver, "debug", "deleteFail_" + vpaValue);
            } catch (IOException io) {
                System.out.println(" Failed to capture screenshot on delete fail: " + io.getMessage());
            }
        }
    }
}
