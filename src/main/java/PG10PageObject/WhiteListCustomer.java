
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

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import ru.yandex.qatools.ashot.AShot;
	import ru.yandex.qatools.ashot.Screenshot;
	import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
	

	public class WhiteListCustomer {
	    WebDriver driver;
	    WebDriverWait wait;

	    public WhiteListCustomer(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Page Elements
	    
	    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
	    WebElement fraudControlManu;
	    
	    @FindBy(xpath = "//a[normalize-space()='White List Customer']")
	    WebElement whiteListCustomer;
	    
	    @FindBy(xpath = "//button[@title='Select Master Merchant']")
	    WebElement smasterMerchantDropdown;
	    
	    @FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
	    WebElement searchSelectMasterMerchant;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	    WebElement selectWhiteListMasterMerchant;
	    
	    @FindBy(xpath = "//a[@class=\"btn btn-info btn-sm\"]")
	    WebElement addWhiteListCust;
	    
	    @FindBy(xpath = "//h3[normalize-space()='White list Customer Details']")
	    WebElement WhiteListCustomerText;
	    
	    @FindBy(xpath = "//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")
	    WebElement selectAny;
	    
	    @FindBy(xpath = "//input[@placeholder=\"Search\"]")
	    WebElement searchWhiteList;
	    
	    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01']")
	    WebElement Testacs;
	    
	    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	    WebElement smasterMerchantDropdown2 ;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	    WebElement searchWhiteList2;
	      
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	    WebElement Testacs2;
	    
	    @FindBy(xpath = "//h3[normalize-space()='White list Customer Details']")
	    WebElement whiteListCustomerText;
	    
	    @FindBy(xpath = "//tbody/tr[1]/td[13]/a[2]/span[1]")
	    WebElement delete1;
	    
	    @FindBy(xpath = "//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]")
	    WebElement delete2;
	    
	    
	    public void interactWithfraudControl_whiteListCustomer() throws InterruptedException, IOException {
	        
	    	Thread.sleep(3000);

	        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(whiteListCustomer)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(smasterMerchantDropdown)).click();
	            
	        wait.until(ExpectedConditions.elementToBeClickable(searchSelectMasterMerchant)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchSelectMasterMerchant)).sendKeys("Test-Acs-01");
	          
	        wait.until(ExpectedConditions.elementToBeClickable(selectWhiteListMasterMerchant)).click();
	        
	        Thread.sleep(3000);
	       
	        try {
	            
	            driver.manage().window().maximize();
	            Thread.sleep(2000); // Let the page load

	          
	            WebElement whiteListCustomerUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));

	            String filePath = "C:\\Automation\\Whitelist Customer\\WhiteList.xlsx";  
	            
	       
	            
	            whiteListCustomerUpload.sendKeys(filePath);

	            // Wait to see upload result
	            Thread.sleep(5000); 

	            System.out.println("File uploaded successfully.");

	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        
	        Thread.sleep(5000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
	        
	        Thread.sleep(3000);
	        
	    //    addWhiteListCust
	        wait.until(ExpectedConditions.elementToBeClickable(addWhiteListCust)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectAny)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchWhiteList)).sendKeys("Test-acs-01");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName"))).sendKeys("Akash");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("LastName"))).sendKeys("Lade");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys("akash@gmail.com");
	               
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("Phone"))).sendKeys("9632629036");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("IP"))).sendKeys("1.1.1.3");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActive"))).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit"))).click();
	        
	       
	     // Smooth scroll to top
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");

	        wait.until(ExpectedConditions.elementToBeClickable(smasterMerchantDropdown2)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchWhiteList2)).sendKeys("Test-acs-01");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs2)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Akash");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	        
	        Thread.sleep(3000);
        
	        captureFullPageScreenshot(driver, "Fraud Control", "WhiteListCustomer", "whiteListCustomerText");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(delete1)).click();
	        
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept(); // Clicks OK
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(delete2)).click();
	        
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept(); // Clicks OK
	        
	        
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");

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
	    
	    private void scrollToTopAndClick(WebElement element) throws InterruptedException {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");
	        Thread.sleep(500);
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        Thread.sleep(300);
	        js.executeScript("arguments[0].click();", element);
	        
	    }
	    	   
}


