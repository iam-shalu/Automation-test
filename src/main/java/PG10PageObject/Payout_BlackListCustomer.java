	
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

	public class Payout_BlackListCustomer {
	    WebDriver driver;
	    WebDriverWait wait;

	    public Payout_BlackListCustomer(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Page Elements
	    
	    //BlackList
	    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
	    WebElement fraudControlManu;

	    @FindBy(xpath = "//a[normalize-space()='BlackList Payout Customer']")
	    WebElement payoutblackListCustomer;
	    
	    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	    WebElement sMasterMerchant;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[1]")
	    WebElement sSearchMasterMerchant;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	    WebElement Testacs01;
	    
	    @FindBy(xpath = "(//button[@class=\"btn btn-info btn-sm\"])[1]")
	    WebElement addPayoutBlackList;
	    
	    @FindBy(xpath = "(//span[@class=\"multiselect-selected-text\"])[3]")
	    WebElement addsMasterMerchant;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[3]")
	    WebElement sSearchMasterMerchant3;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	    WebElement selectTestacs013;
	    
	    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
	    WebElement smasterMerchant3;  
	    
	    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
	    WebElement sMasterMerchant2;
	    
	    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
	    WebElement searchMasterMerchant2;
	    
	    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01-MM']")
	    WebElement TestacsMasterMerch03;
	   
	    @FindBy(xpath = "//h3[normalize-space()='Payout BlackList Customers']")
	    WebElement PayoutblackListCustomerText;
	    
	    @FindBy(xpath = "(//input[@class='form-control form-control-sm'])[2]")
	    WebElement searchByIP;
	    
	    @FindBy(xpath = "//i[@class='fa fa-trash-o fa-lg']")
	    WebElement deleteRecord;
	  	    
	    @FindBy(xpath = "(//span[@class=\"fa fa-trash-o fa-lg\"])[2]")
	    WebElement deleteRecord2;
	    
	 
	    
	    public void interactWithfraudControlPayoutblackListCust() throws InterruptedException, IOException {
	    	
	    	Thread.sleep(3000);

	        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(payoutblackListCustomer)).click();
	         
	        wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(sSearchMasterMerchant)).sendKeys("Test-acs-01");
	        wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();
	        
	        Thread.sleep(3000);
	       
	       try {
	            driver.manage().window().maximize();
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fileInput']")));
	            WebElement blackListUpload = driver.findElement(By.xpath("//input[@id='fileInput']"));
	            String filePath = "D:\\Automation\\Excel file\\PayoutBlackList Customer\\BlacklistPayoutCustomer.xlsx";  
	            blackListUpload.sendKeys(filePath);
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']"))); // hypothetical loader wait
	            System.out.println("File uploaded successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(addPayoutBlackList)).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(addsMasterMerchant)).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(sSearchMasterMerchant3)).sendKeys("Test-acs-01");
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(selectTestacs013)).click();
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("AccountNo"))).sendKeys("1234234346789");
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("BankIFSC"))).sendKeys("ICICI008");
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant2)).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(searchMasterMerchant2)).sendKeys("Test-acs-01");
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(TestacsMasterMerch03)).click();
	       Thread.sleep(3000);
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	       Thread.sleep(3000);       	             
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
	       
	       captureFullPageScreenshot(driver, "Fraud Control", "BlackListCustomer", "PayoutblackListCustomerText");
	       
	       Thread.sleep(3000);
	       
	       ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	       
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1234234346789");
	       
	       wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	       
	       Thread.sleep(3000);
	       
	       	wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        
	        Thread.sleep(3000);
	        
		   wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();

		   wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
	        
		   wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1234234346796");
		   
		   wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		   
		   
		   wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	       wait.until(ExpectedConditions.alertIsPresent());
	       driver.switchTo().alert().accept();
	       
	       
	       Thread.sleep(3000);
	       
	     //Scroll To Top
           ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	       
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
	        wait.until(ExpectedConditions.visibilityOf(element));
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	    }

	}



