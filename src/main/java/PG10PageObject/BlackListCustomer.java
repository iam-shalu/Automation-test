package PG10PageObject;
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
import org.openqa.selenium.support.ui.WebDriverWait;

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

            // Screenshot
            String screenshotName = "BlackListCustomerText_Page_Screenshot";
            System.out.println("Capturing full page screenshot...");
            CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-BlackListCustomer", screenshotName);

            // Step 2: Delete all matching records
            while (true) {
                try {
                    WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
                    searchBox2.clear();
                    searchBox2.sendKeys(expectedEmail);

                    Thread.sleep(1000); // wait for table refresh

                    List<WebElement> rows = driver.findElements(
                            By.xpath("//td[normalize-space(text())='" + expectedEmail + "']"));

                    if (rows.isEmpty()) {
                        System.out.println("All records deleted for: " + expectedEmail);
                        break;
                    }

                    WebElement row = rows.get(0);
                    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            row.findElement(By.xpath(
                                    "./following-sibling::td//a[contains(@title,'Delete Blacklist customer')]//span[contains(@class,'fa-trash-o')]"
                            ))
                    ));

                    deleteBtn.click();
                    wait.until(ExpectedConditions.alertIsPresent()).accept();
                    wait.until(ExpectedConditions.stalenessOf(row));

                    System.out.println("Deleted one record for: " + expectedEmail);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

            // Scroll page
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
