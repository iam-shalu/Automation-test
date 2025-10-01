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
    
    public void interactWithfraudControlblackList() throws InterruptedException {
        try {
            // Navigate
           wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
       
           wait.until(ExpectedConditions.elementToBeClickable(blackListCustomer)).click();
           driver.manage().window().maximize();

            // Upload file
            wait.until(ExpectedConditions.elementToBeClickable(SampleFile)).click();
            String filePath = "D:\\Automation\\pg10-automation\\Upload Excel File\\Blacklist Customer\\BlackList.xlsx";
            chooseFileInput.sendKeys(filePath);
            System.out.println("File uploaded successfully.");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

            // Export file
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

            if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                CommonUtilis.moveDownloadedFileToDatedFolder("BlackListCustomer", dateFolder);
            } else {
                System.err.println("No downloaded Excel file found to move.");
            }
            
            // Add record
            wait.until(ExpectedConditions.elementToBeClickable(ManualAddBlackListCust)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash13@gmail.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
            
            
            // Handle possible error toast
            try {
                WebElement errorToast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Blacklist Customer creation failed')]")));
                if (errorToast.isDisplayed()) {
                    System.out.println("Blacklist Data is Already Exist . Clicking Close.");
                    WebElement closeBtn = wait.until(
                            ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
                    closeBtn.click();
                    return;
                }
            } catch (Exception e) {
                System.out.println("Blacklist Customer created successfully, continuing test flow...");
            }
          
//            String expectedEmail = "akash13@gmail.com";

         // Define target email
            String expectedEmail = "akash13@gmail.com";

            // Step 1: Search once and take screenshot
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
            searchBox.clear();
            searchBox.sendKeys(expectedEmail);

            // Wait until at least one row with email appears
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[normalize-space(text())='" + expectedEmail + "']")));

            // Screenshot before deletion
            String screenshotName = "BlackListCustomerText_Page_Screenshot";
            System.out.println("Capturing full page screenshot...");
            CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-BlackListCustomer", screenshotName);

            // Step 2: Loop until all records deleted

            while (true) {
                try {
                    // Re-apply search (because table refresh clears it after deletion)
                    WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
                    searchBox2.clear();
                    searchBox2.sendKeys(expectedEmail);

                    // Small wait for table to refresh after typing search
                    Thread.sleep(1000); // replace with WebDriverWait on a loader/spinner if available

                    // Find all rows with the target email (no exception if none found)
                    List<WebElement> rows = driver.findElements(
                        By.xpath("//td[normalize-space(text())='" + expectedEmail + "']")
                    );

                    if (rows.isEmpty()) {
                        System.out.println("All records deleted for: " + expectedEmail);
                        break;
                    }

                    // Take the first row
                    WebElement row = rows.get(0);

                    // Wait for its delete button to be clickable
                    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        row.findElement(By.xpath(
                            "./following-sibling::td//a[contains(@title,'Delete Blacklist customer')]//span[contains(@class,'fa-trash-o')]"
                        ))
                    ));

                    // Click delete
                    deleteBtn.click();

                    // Accept the alert
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                    // Wait until that row is removed (table refresh completes)
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
             wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        } catch (Exception e) {
            System.err.println("Test data in BlackListCustomer is already Exist : " + e.getMessage());
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        
        Thread.sleep(3000);
       
    }
}
