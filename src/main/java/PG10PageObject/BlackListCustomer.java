package PG10PageObject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10Base.PG10Base;
import PG10utils.CommonUtilis;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BlackListCustomer {
    WebDriver driver;
    WebDriverWait wait;

    public BlackListCustomer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
  
    

    // Page Elements
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
    WebElement fraudControlManu;

    @FindBy(xpath = "//a[normalize-space()='Black List Customer']")
    WebElement blackListCustomer;
    
    @FindBy(xpath = "//a[normalize-space()='Add Blacklist Customer']")
    WebElement ManualAddBlackListCust;

    @FindBy(xpath = "//a[normalize-space()='Download Sample File']")
    WebElement downloadSampleFile;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/div/div/div/form/div/div[1]/input")
    WebElement chooseFileInput;

    @FindBy(xpath = "//button[contains(text(),'Import')]")
    WebElement importButton;

    @FindBy(xpath = "//h3[normalize-space()='Blacklist Customer']")
    WebElement blackListCustomerText;
    
    
    @FindBy(xpath = "//input[@id='dt-search-0']")
    WebElement searchBlackListCust;
       
    @FindBy(xpath = "//tbody/tr[30]/td[9]/a[2]/span[1]")
    WebElement deleteBlackListcust1;
    
 // //tr[td[contains(text(), 'akash13@gmail.com')]]//a[contains(@onclick, 'DeleteRow')]
  
    @FindBy(xpath = "//h3[normalize-space()='Blacklist Customer']")
    WebElement BlackListText;
    
    @FindBy(xpath = "//span[@class='user-name']")
    WebElement LogoutText;
    
//
//     public void interactWithfraudControlblackList() throws InterruptedException {
//    		Thread.sleep(3000);
//        try {
//        	
//        	
//            // ✅ Navigate to 'Fraud Control' > 'Black List Customer'
//            wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//span[normalize-space()='Fraud Control']"))).click();
//
//            wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//a[normalize-space()='Black List Customer']"))).click();
//
//            driver.manage().window().maximize();
//
//            // ✅ Upload Blacklist File
//            WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(
//                    By.xpath("//input[@name='blacklistcustomerfile']")));
//            String filePath = "D:\\Automation\\Excel file\\Blacklist Customer\\BlackList.xlsx";
//            blackListUpload.sendKeys(filePath);
//            System.out.println("File uploaded successfully.");
//
//            // ✅ Click 'Import' Button
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
//            Thread.sleep(3000);
//
//            // ✅ Export Excel
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
//
//            // ✅ Move the downloaded file
//            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String downloadDir = System.getProperty("user.home") + "\\Downloads";
//
//            PG10Base base = new PG10Base();
//            if (base.waitForFileDownload(downloadDir, ".xlsx", 20)) {
//                base.moveDownloadedFileToDatedFolder("BlackList_Transaction", dateFolder);
//            } else {
//                System.err.println("No downloaded Excel file found to move.");
//            }
//
//            // ✅ Click 'Add Blacklist Customer' (manual)
//            WebElement manualAddBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//a[normalize-space()='Add Blacklist Customer']")));
//            manualAddBtn.click();
//
//            // ✅ Fill form
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash13@gmail.com");
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");
//
//            // ✅ Save
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
//            
//            Thread.sleep(3000);
//
//            // ✅ Search for customer by expected email
//            String expectedEmail = "akash13@gmail.com";
//            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dt-search-0")));
//            searchBox.clear();
//            searchBox.sendKeys(expectedEmail);
//
//            // ✅ Wait for table to load search results
//            wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//td[contains(text(), '" + expectedEmail + "')]")));
//
//            Thread.sleep(3000);
//
//            // ✅ Screenshot
//            captureFullPageScreenshot(driver, "Fraud Control", "BlackListCustomer", "blackListCustomerText");
//
//            Thread.sleep(3000);
//
//            // ✅ Delete all rows that match the email
//            while (true) {
//                List<WebElement> deleteButtons = driver.findElements(By.xpath(
//                        "//tr[td[contains(text(), '" + expectedEmail + "')]]//a[contains(@onclick, 'DeleteRow')]"));
//                if (deleteButtons.isEmpty()) break;
//
//                WebElement deleteBtn = deleteButtons.get(0);
//                wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
//                wait.until(ExpectedConditions.alertIsPresent());
//                driver.switchTo().alert().accept();
//
//                Thread.sleep(2000);
//            }
//
//            // ✅ Confirm deletion
//            List<WebElement> remainingRows = driver.findElements(By.xpath(
//                    "//td[contains(text(), '" + expectedEmail + "')]"));
//            if (remainingRows.isEmpty()) {
//                System.out.println("✅ All blacklist customers with email '" + expectedEmail + "' deleted successfully.");
//            } else {
//                System.err.println("❌ Some records with email '" + expectedEmail + "' still exist.");
//            }
//
//            // ✅ Scroll to bottom
//            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
//            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            
//            Thread.sleep(3000);
//            
//            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
//
//
//        } catch (Exception e) {
//            System.err.println("❌ Test failed in BlackListCustomer interaction: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    
    public void interactWithfraudControlblackList() {
        try {
            // ✅ Navigate to 'Fraud Control' > 'Black List Customer'
            wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
            wait.until(ExpectedConditions.elementToBeClickable(blackListCustomer)).click();

            driver.manage().window().maximize();

            // ✅ Upload Blacklist File
            WebElement blackListUpload = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='blacklistcustomerfile']")));
            String filePath = "D:\\Automation\\Excel file\\Blacklist Customer\\BlackList.xlsx";
            blackListUpload.sendKeys(filePath);
            System.out.println("File uploaded successfully.");

            // ✅ Click 'Import' Button
            wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader"))); // Optional

            // ✅ Export Excel
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();

            // ✅ Move downloaded file
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String downloadDir = System.getProperty("user.home") + "\\Downloads";
            PG10Base base = new PG10Base();
            if (base.waitForFileDownload(downloadDir, ".xlsx", 20)) {
                base.moveDownloadedFileToDatedFolder("BlackList_Transaction", dateFolder);
            } else {
                System.err.println("No downloaded Excel file found to move.");
            }

            // ✅ Manual Add
            wait.until(ExpectedConditions.elementToBeClickable(ManualAddBlackListCust)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash13@gmail.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader"))); // optional

            // ✅ Search for customer by expected email
            String expectedEmail = "akash13@gmail.com";
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
            searchBox.clear();
            searchBox.sendKeys(expectedEmail);

            // ✅ Wait for table result
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[contains(text(), '" + expectedEmail + "')]")));

            // ✅ Screenshot
            captureFullPageScreenshot(driver, "Fraud Control", "BlackListCustomer", "blackListCustomerText");

            // ✅ Delete records
            while (true) {
                List<WebElement> deleteButtons = driver.findElements(By.xpath(
                        "//tr[td[contains(text(), '" + expectedEmail + "')]]//a[contains(@onclick, 'DeleteRow')]"));
                if (deleteButtons.isEmpty()) break;

                WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0)));
                deleteBtn.click();
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
                
                Thread.sleep(3000);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//tr[td[contains(text(), '" + expectedEmail + "')]]//a[contains(@onclick, 'DeleteRow')]")));
                
                Thread.sleep(3000);
            }

            // ✅ Confirm deletion
            List<WebElement> remainingRows = driver.findElements(By.xpath(
                    "//td[contains(text(), '" + expectedEmail + "')]"));
            if (remainingRows.isEmpty()) {
                System.out.println("✅ All blacklist customers with email '" + expectedEmail + "' deleted successfully.");
                Thread.sleep(3000);
            } else {
                System.err.println("❌ Some records with email '" + expectedEmail + "' still exist.");
            }

            // ✅ Scroll bottom & top (wait for page stability)
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        } catch (Exception e) {
            System.err.println("❌ Test failed in BlackListCustomer interaction: " + e.getMessage());
            e.printStackTrace();
        }
        //Scroll To Top
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

    }

    
    private void captureFullPageScreenshot(WebDriver driver, String mainFolder, String subFolder, String fileNameTag) {
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String directoryPath = System.getProperty("user.dir") + File.separator + "screenshots" +
                    File.separator + date + File.separator + mainFolder + File.separator + subFolder;

            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
            String filePath = directoryPath + File.separator + fileNameTag + "_" + timestamp + ".png";

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);

            ImageIO.write(screenshot.getImage(), "PNG", new File(filePath));
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
        }
    }



}
