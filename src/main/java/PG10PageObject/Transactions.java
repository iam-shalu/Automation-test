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

public class Transactions {
	
    WebDriver driver;
    WebDriverWait wait;

    public Transactions(WebDriver driver) {
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

    @FindBy(xpath = "")
    WebElement txId;

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
    
    
    // ==== DEPOSIT TRANSACTIONS ====
    public void interactWithtransactionsDepositTxs() throws InterruptedException, IOException {
    
    	
    //	 wait.until(ExpectedConditions.elementToBeClickable(CloseLimit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(depositTxsOption)).click();

        wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
        WebElement last7DaysOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@data-range-key='Last 7 Days']")));
        last7DaysOption.click();

        wait.until(ExpectedConditions.elementToBeClickable(filter)).click();

        wait.until(ExpectedConditions.elementToBeClickable(export)).click();
        Thread.sleep(3000);

//        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        new PG10Base().moveDownloadedFileToDatedFolder("depositTransactions", dateFolder);

        Thread.sleep(3000);
        
        wait.until(ExpectedConditions.elementToBeClickable(txId)).click();

    //    captureFullPageScreenshot(driver, "Transactions", "Deposit Transactions", "deposit_Tx");
        
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

    // ==== PAYOUT TRANSACTIONS ====
    public void interactWithtransactionPayoutTxs() throws InterruptedException, IOException {
        try {
            wait.until(ExpectedConditions.visibilityOf(transactionsMenu));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", transactionsMenu);
            wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", transactionsMenu);
        } catch (Exception e) {
            System.out.println("Transactions menu click failed: " + e.getMessage());
        }

        wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(payoutTxs)).click();

        wait.until(ExpectedConditions.elementToBeClickable(dateRange)).click();
        wait.until(ExpectedConditions.elementToBeClickable(payoutTxdateLast7Days)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("ddlCutOffTime"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("frmsearch"))).click();
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
        Thread.sleep(3000);

//        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        new PG10Base().moveDownloadedFileToDatedFolder("payoutTransactions", dateFolder);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("dt-length-1"))).click();
        Thread.sleep(3000);

        captureFullPageScreenshot(driver, "Transactions", "Payout Transactions", "Payout_Tx_List");

        Thread.sleep(2000);
        
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

            System.out.println("📸 Screenshot saved at: " + screenshotFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }
    }
}
