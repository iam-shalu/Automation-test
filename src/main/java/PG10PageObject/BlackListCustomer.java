package PG10PageObject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    
    public void interactWithfraudControlblackList() throws IOException, InterruptedException {
    	
        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(blackListCustomer)).click();

        driver.manage().window().maximize();
        
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='blacklistcustomerfile']")));

        WebElement blackListUpload = driver.findElement(By.xpath("//input[@name='blacklistcustomerfile']"));
        String filePath = "D:\\Automation\\Excel file\\Blacklist Customer\\BlackList.xlsx"; 
        blackListUpload.sendKeys(filePath);
        
        System.out.println("File uploaded successfully.");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmimport")));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
        
     //   Thread.sleep(3000);
         
        wait.until(ExpectedConditions.elementToBeClickable(ManualAddBlackListCust)).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash135@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
  //      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629096");
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");
        
     // Click Save button when visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSave"))).click();
        
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dt-search-0"))).sendKeys("Akash");
        
        Thread.sleep(3000); 
        
        captureFullPageScreenshot(driver, "Fraud Control", "BlackListCustomer", "blackListCustomerText");
        
        Thread.sleep(3000); 
        
        wait.until(ExpectedConditions.elementToBeClickable(deleteBlackListcust1)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
        Thread.sleep(3000); 
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dt-search-0"))).sendKeys("Akash");
        
        
        Thread.sleep(3000); 
        
        // Scroll to bottom
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        
        wait.until(ExpectedConditions.elementToBeClickable(deleteBlackListcust1)).click();
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
