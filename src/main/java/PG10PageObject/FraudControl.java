
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

	public class FraudControl {
	    WebDriver driver;
	    WebDriverWait wait;

	    public FraudControl(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Page Elements
	    
	    //BlackList
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
	       
	    @FindBy(xpath = "//tbody/tr[31]/td[9]/a[2]/span[1]")
	    WebElement deleteBlackListcust1;

	    @FindBy(xpath = "//tbody/tr[32]/td[9]/a[2]/span[1]")
	    WebElement deleteBlackListcust2;
	    
	    @FindBy(xpath = "//h3[normalize-space()='Blacklist Customer']")
	    WebElement BlackListText;
	    
	    //WhiteList

	    @FindBy(xpath = "//a[normalize-space()='White List Customer']")
	    WebElement whiteListCustomer;
	    
	    @FindBy(xpath = "//button[@title='Select Master Merchant']")
	    WebElement smasterMerchantDropdown;
	    
	    @FindBy(xpath = "//input[@class=\"form-control multiselect-search\"]")
	    WebElement searchSelectMasterMerchant;
	    
	    @FindBy(xpath = "//input[@id=\"fileInput\"]")
	    WebElement chooseFile;
	  
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	    WebElement selectWhiteListMasterMerchant;
	    
	    @FindBy(xpath = "//a[normalize-space()='Add Whitelist Customer']")
	    WebElement addWhiteListMerchantIp;
	    
	    @FindBy(xpath = "//button[@title='Select Any']")
	    WebElement masterMerchantDropdown;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	    WebElement selectTestAcs01;
	       
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
	    WebElement optionTestAcs01;
	    
	    @FindBy(xpath = "//button[@id='btnFilter']")
	    WebElement filterWhitelistCust;
	    
	    @FindBy(xpath = "//td[normalize-space()='196']")
	    WebElement idWhiteListCust;
	    
	    @FindBy(xpath = "//span[contains(@class,'dtr-data')]//a[contains(@title,'Delete Record')]//span[contains(@class,'fa-lg')]")
	    WebElement deleteIdWhiteList;
	    
	    @FindBy(xpath = "//a[normalize-space()='Click here']")
	    WebElement sampleFileDownload;
	    
	    @FindBy(xpath = "//a[normalize-space()='White List Merchant IP']")
	    WebElement whiteListMerchantIp;
	    
	    @FindBy(xpath = "//a[@class=\"btn btn-info btn-sm\"]")
	    WebElement addWhiteListCust;
	    
	    @FindBy(xpath = "//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")
	    WebElement selectAny;
	    
	    @FindBy(xpath = "//input[@placeholder=\"Search\"]")
	    WebElement searchWhiteList;
	    
	    @FindBy(xpath = "//label[normalize-space()='Test-Acs-01']")
	    WebElement Testacs;
	    
	    @FindBy(xpath = "//input[@id=\"FirstName\"]")
	    WebElement FirstName;
	   
	    
	    public void interactWithfraudControlblackList() throws InterruptedException, IOException {
	    	
	    	Thread.sleep(3000);

	        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(blackListCustomer)).click();
	         
	        Thread.sleep(3000);
	        
	        try {
	            
	            driver.manage().window().maximize();
	            Thread.sleep(2000); // Let the page load

	          
	            WebElement blackListUpload = driver.findElement(By.xpath("//input[@name=\"blacklistcustomerfile\"]"));

	            
	            String filePath = "C:\\Automation\\Blacklist Customer\\BlackList_SampleFile (4).xlsx";  

	            
	            blackListUpload.sendKeys(filePath);

	            // Wait to see upload result
	            Thread.sleep(5000); 

	            System.out.println("File uploaded successfully.");

	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        
	        Thread.sleep(5000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(ManualAddBlackListCust)).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("Name"))).sendKeys("akash");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys("akash13@gmail.com	");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("MobileNo"))).sendKeys("9632629034");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("IPaddress"))).sendKeys("1.2.3.4");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
	        
	        captureFullPageScreenshot(driver, "Fraud Control", "BlackListCustomer", "blackListCustomerText");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("dt-search-0"))).sendKeys("Akash");
	        
	        Thread.sleep(2000);

	        // Scroll to bottom
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	             
	        wait.until(ExpectedConditions.elementToBeClickable(deleteBlackListcust1)).click();
	        
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept(); // Clicks OK
	        
	        wait.until(ExpectedConditions.elementToBeClickable(deleteBlackListcust2)).click();
	        
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept(); // Clicks OK
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
	       
	        Thread.sleep(3000);
	        
	    }
	   
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

	            String filePath = "C:\\Automation\\Whitelist Customer\\WhiteList_SampleFile(2).xlsx";  

	            
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