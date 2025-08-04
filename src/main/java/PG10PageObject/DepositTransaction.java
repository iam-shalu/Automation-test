
	package PG10PageObject;
	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Date;
	import javax.imageio.ImageIO;

	import org.openqa.selenium.*;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.*;

	import PG10Base.PG10Base;
	import ru.yandex.qatools.ashot.AShot;
	import ru.yandex.qatools.ashot.Screenshot;
	import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

	public class DepositTransaction {
		
	    WebDriver driver;
	    WebDriverWait wait;

	    public DepositTransaction(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Page Elements
	    
	    @FindBy(xpath = "//div[@class='modal-dialog']//button[@type='button'][normalize-space()='Close']")
	    WebElement CloseLimit;
	    
	    @FindBy(xpath = "//span[normalize-space()='Transactions']")
	    WebElement transactionsMenu;

	    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
	    WebElement bnibMenu;

	    @FindBy(xpath = "//a[normalize-space()='Deposit Txs']")
	    WebElement depositTxsOption;

	    @FindBy(xpath = "//select[@id='fieldname1']")
	    WebElement searchField;

	    @FindBy(xpath = "//input[@id='txtDateRange']")
	    WebElement dateRange;

	    @FindBy(xpath = "//li[@class='active']")
	    WebElement dateLast7days;

	    @FindBy(xpath = "//button[@id='btnfiltersearch']")
	    WebElement filter;

	    @FindBy(xpath = "//button[@id='btnDownloadExcel']")
	    WebElement export;

	    @FindBy(xpath = "//td[normalize-space()='36720055']")
	    WebElement txId;

	    @FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
	    WebElement tx_Action;
	    
	    @FindBy(xpath = "//h3[normalize-space()='Deposit Txs']")
	    WebElement depositTxText ;
	    
	    @FindBy(xpath = "/html/body/div[2]/div/header/div/div/div/div/h3")
	    WebElement viewTxButton;

	    @FindBy(xpath = "//input[@id='searchval1']")
	    WebElement enterValue;

	    @FindBy(xpath = "//a[@href='/BNIBPayout/Transactions']")
	    WebElement payoutTxs;

	    @FindBy(xpath = "//li[normalize-space()='Last 7 Days']")
	    WebElement payoutTxdateLast7Days;

	    @FindBy(xpath = "//h3[normalize-space()='Payout Tx List']")
	    WebElement payoutTxList;
	  
	    
	    // ==== DEPOSIT TRANSACTIONS ====
	    public void interactWithtransactionsDepositTxs() throws InterruptedException, IOException {
	    	
	    	Thread.sleep(3000);
	    
	    	
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(depositTxsOption)).click();
	        
	        Thread.sleep(3000);
	           
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtDateRange"))).click();

	   //     wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
	        
	        Thread.sleep(3000);
	        WebElement last7DaysOption = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//li[@data-range-key=\"Last 7 Days\"]")));
	        last7DaysOption.click();
	        
	        Thread.sleep(3000);

	        wait.until(ExpectedConditions.elementToBeClickable(filter)).click();

	        wait.until(ExpectedConditions.elementToBeClickable(export)).click();
	        
	        Thread.sleep(3000);

	       String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        new PG10Base().moveDownloadedFileToDatedFolder("depositTransactions", dateFolder);

	        Thread.sleep(3000);
	        
	        
	        wait.until(ExpectedConditions.elementToBeClickable(txId)).click();
	        
	        Thread.sleep(3000);
	        
	        captureFullPageScreenshot(driver, "Transactions", "Deposit Transactions", "depositTxText");
	        
	        Thread.sleep(3000);
	        
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        
	        wait.until(ExpectedConditions.elementToBeClickable(tx_Action)).click();
	        
	        Thread.sleep(3000);
	        
	        String originalWindow = driver.getWindowHandle();
	        wait.until(ExpectedConditions.elementToBeClickable(tx_Action)).click();

	        new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(d -> driver.getWindowHandles().size() > 1);

	        for (String handle : driver.getWindowHandles()) {
	            if (!handle.equals(originalWindow)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	            
	        }
	        
	        Thread.sleep(3000);

	        wait.until(ExpectedConditions.visibilityOf(viewTxButton));
	        captureFullPageScreenshot(driver, "Transactions", "Deposit Transactions", "View_Tx_Details");
	        

	        driver.close();
	        driver.switchTo().window(originalWindow);

	        Select searchDropdown = new Select(driver.findElement(By.id("fieldname1")));
	        searchDropdown.selectByValue("iPayinfo");

	        Thread.sleep(2000);

	        Select selectFilterType = new Select(driver.findElement(By.id("filtertype1")));
	        selectFilterType.selectByValue("Equals");

	        wait.until(ExpectedConditions.elementToBeClickable(enterValue)).sendKeys("gomzi001@axl");

	        Thread.sleep(2000);
	    }


		private void captureFullPageScreenshot(WebDriver driver2, String mainFolder, String subFolder, String fileNameTag) {
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
