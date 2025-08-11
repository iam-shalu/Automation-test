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

	public class PayoutTransaction {
		
	    WebDriver driver;
	    WebDriverWait wait;

	    public PayoutTransaction(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	  
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
	    
	    @FindBy(xpath = "//td[normalize-space()='45640892']")
	    WebElement PayoutId;

	    @FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-server']")
	    WebElement tx_Action;

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
	    
	      
	    public void interactWithtransactionPayoutTxs() throws IOException {
	        try {
	            System.out.println("==== Starting Payout Transactions Test ====");
	            	
	            Thread.sleep(3000);

	            // Wait for merchant modal to close (if it appears)
	                wait.until(ExpectedConditions.or(
	                ExpectedConditions.invisibilityOfElementLocated(By.id("merchantLimitModal")),
	                ExpectedConditions.not(ExpectedConditions.attributeContains(By.id("merchantLimitModal"), "style", "display: block"))
	            ));
	            
	            Thread.sleep(3000);

	            wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
	            wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
	            wait.until(ExpectedConditions.elementToBeClickable(payoutTxs)).click();
	            
	            Thread.sleep(3000);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
	            
	            Thread.sleep(3000);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(payoutTxdateLast7Days)).click();

	            wait.until(ExpectedConditions.elementToBeClickable(By.id("ddlCutOffTime"))).click();
	            
	            
	         // Wait for the cutoff time dropdown element to be present
	            WebElement cutoffTimeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlCutOffTime")));

	            // Wrap it in a Select object
	            Select timeSelect = new Select(cutoffTimeDropdown);

	            // Select the option by visible text "12 AM to 12 AM"
	            timeSelect.selectByVisibleText("12 AM to 12 AM");
	            
	            Thread.sleep(3000);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(By.id("frmsearch"))).click();
	            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();

	            // Wait and move Excel
	            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
	            PG10Base base = new PG10Base();

	            if (base.waitForFileDownload(downloadDir, ".xlsx", 20)) {
	                base.moveDownloadedFileToDatedFolder("payoutTransactions", dateFolder);
	            } else {
	                System.err.println(" No downloaded Excel file found to move.");
	            }

	            wait.until(ExpectedConditions.elementToBeClickable(By.id("dt-length-1"))).click();
	            
	            Thread.sleep(3000);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(PayoutId)).click();
	            
	            Thread.sleep(3000);
	            
	            captureFullPageScreenshot(driver, "Transactions", "Payout Transactions", "Payout_Tx_List");
	            
	            Thread.sleep(3000);
	                
	            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            
	            Thread.sleep(3000);

	            System.out.println("==== Payout Transactions Test Completed ====");

	        } catch (Exception e) {
	            System.err.println(" Unexpected error in Payout Transaction flow: " + e.getMessage());
	        }
	    }


	    // ==== Screenshot Handler ====
	    public void captureFullPageScreenshot(WebDriver driver, String parentFolder, String subFolder, String label) {
	        try {
	            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	            String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

	            File baseDir = new File("screenshots" + File.separator + dateFolder
	                    + File.separator + parentFolder + File.separator + subFolder);

	            if (!baseDir.exists()) baseDir.mkdirs();

	            Screenshot screenshot = new AShot()
	                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
	                    .takeScreenshot(driver);

	            File screenshotFile = new File(baseDir, label + "_" + timestamp + ".png");
	            ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);

	            System.out.println("Screenshot saved at: " + screenshotFile.getAbsolutePath());

	        } catch (IOException e) {
	            System.out.println("Screenshot error: " + e.getMessage());
	        }
	    }
}

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    


