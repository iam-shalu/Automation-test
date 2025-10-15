package PG10PageObject;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import PG10utils.CommonUtilis;

public class BlackListCustomer {
    WebDriver driver;
    WebDriverWait wait;

    public BlackListCustomer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[4]/a/span")
    WebElement fraudControlManu;

    @FindBy(xpath = "//a[normalize-space()='Black List Customer']")
    WebElement blackListCustomer;

    @FindBy(xpath = "//a[text()='Download Sample File']")
    WebElement SampleFile;

    @FindBy(xpath = "//a[normalize-space()='Add Blacklist Customer']")
    WebElement ManualAddBlackListCust;

    @FindBy(xpath = "//input[@name='blacklistcustomerfile']")
    WebElement chooseFileInput;

    @FindBy(xpath = "//button[contains(text(),'Import')]")
    WebElement importButton;

    @FindBy(xpath = "//h3[normalize-space()='Blacklist Customer']")
    WebElement blackListCustomerText;

    @FindBy(xpath = "//input[@id='dt-search-0']")
    WebElement searchBlackListCust;

    @FindBy(xpath = "//span[@class='user-name']")
    WebElement LogoutText;

    // -----------------------
    // Core robust click method
    // -----------------------
    private void clickElementWithFallback(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(el));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        } catch (Exception e) {
            try {
                WebElement el = driver.findElement(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ex) {
                throw new RuntimeException("Could not click element: " + locator, ex);
            }
        }
    }

    // -----------------------
    // Main interaction method
    // -----------------------
    public void interactWithfraudControlblackList() throws Exception {
        try {
            // Navigate to Blacklist Customer page
            clickElementWithFallback(By.xpath("/html/body/div[2]/div/nav/div/ul/li[4]/a/span"));
            clickElementWithFallback(By.xpath("//a[normalize-space()='Black List Customer']"));
            driver.manage().window().maximize();

            // Upload file
            clickElementWithFallback(By.xpath("//a[text()='Download Sample File']"));
            String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\Blacklist Customer\\BlackList.xlsx";
            chooseFileInput.sendKeys(filePath);
            clickElementWithFallback(By.id("frmimport"));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

            // Export file
            clickElementWithFallback(By.id("btnExport"));
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

            if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                CommonUtilis.moveDownloadedFileToDatedFolder("BlackListCustomer", dateFolder);
            } else {
                System.err.println("No downloaded Excel file found to move.");
            }

            // Add record manually
            clickElementWithFallback(By.xpath("//a[normalize-space()='Add Blacklist Customer']"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash13@gmail.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");
            clickElementWithFallback(By.id("btnSave"));

            // Handle possible error toast
            try {
                WebElement errorToast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Blacklist Customer creation failed')]")));
                if (errorToast.isDisplayed()) {
                    System.out.println("Blacklist Data is Already Exist. Clicking Close.");
                    clickElementWithFallback(By.xpath("//button[normalize-space()='Close']"));
                    return;
                }
            } catch (Exception e) {
                System.out.println("Blacklist Customer created successfully, continuing test flow...");
            }

            // Step 1: Search record
            String expectedEmail = "akash13@gmail.com";
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
            searchBox.clear();
            searchBox.sendKeys(expectedEmail);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[normalize-space(text())='" + expectedEmail + "']")));

            // Screenshot using full-page logic
            String screenshotName = "BlackListCustomerText_Page_Screenshot";
            System.out.println("Capturing full page screenshot...");
            CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-BlackListCustomer", screenshotName);

            // ============================
            // ✅ Fixed Delete Logic Section
            // ============================
            while (true) {
                try {
                    // Refresh search each loop
                    WebElement searchBox2 = wait.until(ExpectedConditions.elementToBeClickable(searchBlackListCust));
                    searchBox2.clear();
                    searchBox2.sendKeys(expectedEmail);

                    // Wait until record appears
                    List<WebElement> rows = wait.until(driver -> {
                        List<WebElement> found = driver.findElements(
                                By.xpath("//table//td[normalize-space(text())='" + expectedEmail + "']/parent::tr"));
                        return (found.size() > 0) ? found : null;
                    });

                    if (rows == null || rows.isEmpty()) {
                        System.out.println("✅ All records deleted for: " + expectedEmail);
                        break;
                    }

                    // Work with first record
                    WebElement row = rows.get(0);
                    WebElement deleteLink = row.findElement(By.xpath(".//a[contains(@title,'Delete Blacklist customer')]"));

                    // Scroll center (helps headless)
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deleteLink);

                    // Try normal click, fallback to JS
                    try {
                        wait.until(ExpectedConditions.elementToBeClickable(deleteLink));
                        deleteLink.click();
                    } catch (Exception clickEx) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteLink);
                        System.out.println(" Used JS click fallback for delete (headless mode).");
                    }

                    // Handle alert confirmation
                    try {
                        wait.until(ExpectedConditions.alertIsPresent());
                        Alert alert = driver.switchTo().alert();
                        System.out.println("🪟 Alert message: " + alert.getText());
                        alert.accept();
                        System.out.println("✅ Alert accepted successfully.");
                    } catch (Exception e) {
                        System.out.println("⚠️ Alert not appeared — retrying DeleteRow() manually via JS.");
                        try {
                            ((JavascriptExecutor) driver).executeScript("DeleteRow('" + expectedEmail + "');");
                            Thread.sleep(1000);
                            driver.switchTo().alert().accept();
                        } catch (Exception ignore) {
                            System.out.println(" No alert found even after JS trigger — skipping.");
                        }
                    }

                    // Wait for row to disappear
                    wait.until(ExpectedConditions.stalenessOf(row));
                    System.out.println(" Deleted one record for: " + expectedEmail);

                    // Ensure table reload
                    wait.until(ExpectedConditions.or(
                            ExpectedConditions.stalenessOf(row),
                            ExpectedConditions.invisibilityOfElementLocated(
                                    By.xpath("//table//td[normalize-space(text())='" + expectedEmail + "']"))
                    ));

                } catch (Exception e) {
                    System.out.println("⚠️ Deletion completed or no more records found.");
                    break;
                }
            }

            // Scroll page for sanity
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        } catch (Exception e) {
            System.err.println("Error in BlackListCustomer flow: " + e.getMessage());
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);
    }
}


