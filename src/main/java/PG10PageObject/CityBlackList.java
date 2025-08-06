package PG10PageObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CityBlackList {
	 WebDriver driver;
	 WebDriverWait wait;

	    public CityBlackList(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	    }
	    
	    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
	    WebElement fraudControlManu;
	    
	    @FindBy(xpath = "//a[normalize-space()='City BlackList']")
	    WebElement cityBlackList;
	    
	    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	    WebElement SubMastermerchant1;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	    WebElement searchMasterMerchant1;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
	    WebElement Testacs1;
	 
	    @FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
	    WebElement addcityBlackList;
	    
	    @FindBy(xpath = "(//button[@class='multiselect dropdown-toggle btn btn-default'])[3]")
	    WebElement selectSubMerchant2;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
	    WebElement searchSubTestacs2;
	    
	    @FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
	    WebElement selectsubTestacs2;
	    
	    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[2]")
	    WebElement SubMastermerchant3;
	    
	    @FindBy(xpath = "//li[@class='multiselect-item multiselect-all active']//label[@class='checkbox'][normalize-space()='Select all']")
	    WebElement selectAllbtn3;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	    WebElement searchSubMastermerchant3;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
	    WebElement Testacs3;
	    
	    @FindBy(xpath = "//h3[normalize-space()='City BlackList Details']")
	    WebElement cityBlackListDetails;
	    
	    @FindBy(xpath = "//span[@class=\"fa fa-trash-o fa-lg\"]")
	    WebElement deleteRecord;
	    
	    
	    
	    public void interactWithfraudControlblackList() throws InterruptedException, IOException {
	    	
	    	Thread.sleep(3000);
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(cityBlackList)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant1)).click();
	 
	        wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant1)).sendKeys("Test-acs-01");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs1)).click();
	        
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));

	        WebElement blackListUpload = driver.findElement(By.xpath("//input[@id='fileInput']"));
	        String filePath = "D:\\Automation\\Excel file\\CityBlackList Customer\\CityBlackList.xlsx"; 
	        blackListUpload.sendKeys(filePath);
	        
	        System.out.println("File uploaded successfully.");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnimport"))).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(addcityBlackList)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectSubMerchant2)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchSubTestacs2)).sendKeys("Test-acs-01");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectsubTestacs2)).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("City"))).sendKeys("Surat");
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Save"))).click();
	        
	        Thread.sleep(5000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant3)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectAllbtn3)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchSubMastermerchant3)).sendKeys("Test-acs-01");
	        
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs3)).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
	        
	        Thread.sleep(3000);
	        
	        captureFullPageScreenshot(driver, "Fraud Control", "CityBlackList", "cityBlackListDetails");
	        
	        Thread.sleep(3000);
	        
	//        txtSearch
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Surat");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(SubMastermerchant3)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectAllbtn3)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchSubMastermerchant3)).sendKeys("Test-acs-01");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs3)).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
	        
	        
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("Bangalore");
	        
	        Thread.sleep(3000);	        
	        
	        wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        
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
