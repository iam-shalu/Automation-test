//
//
//package PG10PageObject;
//
//import java.io.IOException;
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class StateBlackList {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public StateBlackList(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
//    WebElement fraudControlManu;
//
//    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[4]/ul/li[9]/a")
//    WebElement stateBlackList;
//
//    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[1]")
//    WebElement SubMarchant1;
//
//    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
//    WebElement searchSubMerchant1;
//
//    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01']")
//    WebElement Testacs1;
//
//    @FindBy(xpath = "//a[@class=\"btn btn-info btn-sm\"]")
//    WebElement addStateBlackList;
//
//    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[3]")
//    WebElement selectSubElement;
//
//    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
//    WebElement searchselectSubElement1;
//
//    @FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01']")
//    WebElement Testacs2;
//
//   @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
//   WebElement subMerchant2;
//   
//   @FindBy(xpath = "(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]")
//   WebElement dselectAllCheckbox;
//
//   @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[2]")
//   WebElement searchSubMerchant2;
//
////   
////   @FindBy(xpath = "(//li[contains(@class, 'multiselect-item') and contains(@class, 'multiselect-all')]//label[@class='checkbox'][normalize-space()='Select all'])[2]")
////   WebElement dselectAllCheckbox;
////   
////
////   @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
////   WebElement searchSubMerchant2;
//
//   /**
//    * Tries to click an element with retries in case of StaleElementReferenceException.
//    */
//   public boolean retryingFindClick(By locator, int maxAttempts) {
//       int attempts = 0;
//       while (attempts < maxAttempts) {
//           try {
//               WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//               element.click();
//               return true; // success
//           } catch (org.openqa.selenium.StaleElementReferenceException e) {
//               System.err.println("StaleElementException on attempt #" + (attempts+1) + " for locator: " + locator.toString());
//               attempts++;
//           } catch (Exception e) {
//               System.err.println("Non-stale error during clicking: " + e.getMessage());
//               break;
//           }
//       }
//       return false; // failed after retries
//   }
//
//  
//   
//
//
//
//
//   
//  
//   
//    
//
//
//
//    public void interactWithfraudControlStateblackList() throws InterruptedException, IOException {
//    	
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(stateBlackList)).click();
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(SubMarchant1)).click();
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(searchSubMerchant1)).sendKeys("Test-acs-01");
//
//        wait.until(ExpectedConditions.elementToBeClickable(Testacs1)).click();
//
//        driver.manage().window().maximize();
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"fileInput\"]")));
//
//        WebElement blackListUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
//        String filePath = "D:\\Automation\\Excel file\\State BlackList\\StateBlackList.xlsx";
//        blackListUpload.sendKeys(filePath);
//
//        System.out.println("File uploaded successfully.");
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(addStateBlackList)).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(selectSubElement)).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(searchselectSubElement1)).sendKeys("Test-acs-01");
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(Testacs2)).click();
//
//        Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("State"))).sendKeys("Chattisgarh");
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_Save"))).click();
//
//        Thread.sleep(3000);
////        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subMerchant2);
//        
////        Thread.sleep(5000);
//        
//        
////      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dselectAllCheckbox);
////
////        Thread.sleep(5000);
////        
////        wait.until(ExpectedConditions.elementToBeClickable(searchSubMerchant2)).sendKeys("Test-acs-01");
////        
////        Thread.sleep(5000);
//        
//        
//        try {
//            // Click the dropdown with retry
//            boolean dropdownClicked = retryingFindClick(
//                By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"), 3
//            );
//            if (!dropdownClicked) {
//                throw new RuntimeException("Failed to click SubMerchant dropdown after retries.");
//            }
//            System.out.println("Dropdown clicked successfully.");
//
//            Thread.sleep(1000);
//
//            // Click Select All checkbox with retry
//            boolean selectAllClicked = retryingFindClick(
//                By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]"), 3
//            );
//            if (!selectAllClicked) {
//                throw new RuntimeException("Failed to click Select All checkbox after retries.");
//            }
//            System.out.println("Select all clicked successfully.");
//
//            Thread.sleep(1000);
//
//            // Type in the search box with fresh reference (relocate right before typing)
//            WebElement freshSearchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("(//input[@class='form-control multiselect-search'])[2]")
//            ));
//            freshSearchBox.clear();
//            freshSearchBox.sendKeys("Test-acs-01");
//            freshSearchBox.sendKeys(Keys.ENTER);
//            System.out.println("Search text entered and ENTER key pressed.");
//
//        } catch (Exception e) {
//            System.err.println("Error occurred in SubMerchant dropdown interaction: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
//}



package PG10PageObject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class StateBlackList {
    WebDriver driver;
    WebDriverWait wait;

    public StateBlackList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//span[normalize-space()='Fraud Control']")
    WebElement fraudControlManu;

    @FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[4]/ul/li[9]/a")
    WebElement stateBlackList;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[1]")
    WebElement SubMarchant1;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
    WebElement searchSubMerchant1;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
    WebElement Testacs1;

    @FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
    WebElement addStateBlackList;

    @FindBy(xpath = "(//span[@class='multiselect-selected-text'])[3]")
    WebElement selectSubElement;

    @FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
    WebElement searchselectSubElement1;

    @FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
    WebElement Testacs2;
    
    @FindBy(xpath = "//h3[normalize-space()='State BlackList Details']")
    WebElement StateBlackListText;
    
    @FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
    WebElement deleteChg;
    
    @FindBy(xpath = "//tbody/tr[1]/td[4]/a[2]/span[1]")
    WebElement deleteka;

    // These are used only via By locators in retrying method
    By subMerchant2Btn = By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]");
    By selectAllChk = By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]");
    By subMerchant2Search = By.xpath("(//input[@class='form-control multiselect-search'])[2]");

    // Retry method to safely click on potentially stale elements
    public boolean retryingFindClick(By locator, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return true;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.err.println("StaleElementException on attempt #" + (attempts + 1) + " for locator: " + locator);
                attempts++;
            } catch (Exception e) {
                System.err.println("Non-stale error during clicking: " + e.getMessage());
                break;
            }
        }
        return false;
    }
   
   public void interactWithfraudControlStateblackList() throws InterruptedException, IOException {

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
    wait.until(ExpectedConditions.elementToBeClickable(stateBlackList)).click();

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(SubMarchant1)).click();

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(searchSubMerchant1)).sendKeys("Test-acs-01");

    wait.until(ExpectedConditions.elementToBeClickable(Testacs1)).click();

    driver.manage().window().maximize();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"fileInput\"]")));

    WebElement blackListUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
    String filePath = "D:\\Automation\\Excel file\\State BlackList\\StateBlackList.xlsx";
    blackListUpload.sendKeys(filePath);

    System.out.println("File uploaded successfully.");

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(addStateBlackList)).click();

    wait.until(ExpectedConditions.elementToBeClickable(selectSubElement)).click();

    wait.until(ExpectedConditions.elementToBeClickable(searchselectSubElement1)).sendKeys("Test-acs-01");

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(Testacs2)).click();

    Thread.sleep(3000);

    wait.until(ExpectedConditions.elementToBeClickable(By.id("State"))).sendKeys("Chattisgarh");

    wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_Save"))).click();

    Thread.sleep(3000);

    try {
        // Click the dropdown with retry
        boolean dropdownClicked = retryingFindClick(
            By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"), 3
        );
        if (!dropdownClicked) {
            throw new RuntimeException("Failed to click SubMerchant dropdown after retries.");
        }
        System.out.println("Dropdown clicked successfully.");

        Thread.sleep(1000);

        // Click Select All checkbox with retry
        boolean selectAllClicked = retryingFindClick(
            By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]"), 3
        );
        if (!selectAllClicked) {
            throw new RuntimeException("Failed to click Select All checkbox after retries.");
        }
        System.out.println("Select all clicked successfully.");

        Thread.sleep(1000);

        // Type in the search box with fresh reference (relocate right before typing)
        WebElement freshSearchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//input[@class='form-control multiselect-search'])[2]")));
        freshSearchBox.clear();
        freshSearchBox.sendKeys("Test-acs-01");
        freshSearchBox.sendKeys(Keys.ENTER);
        System.out.println("Search text entered and ENTER key pressed.");

        // Click the checkbox for Test-Acs-01
     // Click the checkbox for Test-Acs-01
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
        ));
        
       
        checkbox.click();
        System.out.println("Checkbox for Test-Acs-01 clicked successfully.");


    } catch (Exception e) {
        System.err.println("Error occurred in SubMerchant dropdown interaction: " + e.getMessage());
        e.printStackTrace();
    }
    
    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
    
    Thread.sleep(3000);
    
    captureFullPageScreenshot(driver, "Fraud Control", "StateBlackList", "StateBlackListText");
    
    Thread.sleep(3000);
    
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    
    Thread.sleep(3000);
    
    wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Chattisgarh");
    
    Thread.sleep(3000);
    
    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
    
    Thread.sleep(3000);
    
    wait.until(ExpectedConditions.elementToBeClickable(deleteChg)).click();
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    
    Thread.sleep(3000);
    
    wait.until(ExpectedConditions.elementToBeClickable(deleteka)).click();
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    
    Thread.sleep(3000);
    
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
