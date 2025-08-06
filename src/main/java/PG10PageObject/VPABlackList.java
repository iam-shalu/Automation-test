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

	public class VPABlackList {
	    WebDriver driver;
	    WebDriverWait wait;

	    public VPABlackList(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	  

	    // Page Elements
	    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
	    WebElement fraudControlManu;

	    @FindBy(xpath = "//a[normalize-space()='VPA BlackList']")
	    WebElement VpaBlackList;
	    
	    @FindBy(xpath = "//a[normalize-space()='Add VPABlackList User']")
	    WebElement AddVPABlackListUser;
	   
	    @FindBy(xpath = "//h3[normalize-space()='VPA BlackList Details']")
	    WebElement VpaBlackListDetailsText;
	    
	    @FindBy(xpath = "//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")
	    WebElement selectAny;
	    
	    @FindBy(xpath = "//input[@class='form-control multiselect-search']")
	    WebElement searchselectAny;
	    
	    @FindBy(xpath = "//label[normalize-space()='FULL']")
	    WebElement Full;
	    
	    @FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
	    WebElement deleteRecord;
	    
	    

	    public void interactWithfraudControlVPABlackList() throws IOException, InterruptedException {
	    	
	    	Thread.sleep(3000);
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(VpaBlackList)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='form-control form-control-sm'])[1]")));

	        WebElement blackListUpload = driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]"));
	        String filePath = "D:\\Automation\\Excel file\\VPA BlackList\\VPABlackList.xlsx"; 
	        blackListUpload.sendKeys(filePath);
	        
	        System.out.println("File uploaded successfully.");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnimport"))).click();
	        
	        Thread.sleep(3000);
	       
	        wait.until(ExpectedConditions.elementToBeClickable(AddVPABlackListUser)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(selectAny)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(searchselectAny)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(Full)).click();
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VPA"))).sendKeys("4123499@paytm");
	        
	        Thread.sleep(3000);
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Save"))).click();
	        
	        Thread.sleep(3000);
	       
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("4123466@paytm");
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnFilter"))).click();
	        
	        Thread.sleep(3000);
	        
	       captureFullPageScreenshot(driver, "Fraud Control", "VPA BlackList", "VpaBlackListDetailsText");
	       
	       Thread.sleep(3000);
	       
	   //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnExport"))).click();
	       
	       wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
	       wait.until(ExpectedConditions.alertIsPresent());
	       driver.switchTo().alert().accept();
	       
	       Thread.sleep(3000);
	       
	       wait.until(ExpectedConditions.elementToBeClickable(deleteRecord)).click();
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


