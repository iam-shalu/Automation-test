package PG10PageObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PG10utils.CommonUtilis;

public class SearchTxHistory {
	WebDriver driver;
    WebDriverWait wait;
    public SearchTxHistory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @FindBy(xpath = "//span[normalize-space()='Transactions']")
    WebElement transactionsMenu;

    @FindBy(xpath = "//a[@id='submenuTxDropdown']")
    WebElement bnibMenu;
    
    @FindBy(xpath = "//a[normalize-space()='Search Tx History']")
    WebElement searchTxHist;
    
    @FindBy(xpath = "//td[@class='sorting_1 dtr-control']")
    WebElement ChargebackId;
    
    @FindBy(xpath = "//h3[normalize-space()='Search Tx History List']")
    WebElement SearchTxHistoryPage;
    
    public void interactWithtransactionsSearchTxHist() throws InterruptedException, IOException {
    	Thread.sleep(5000);
    	wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(searchTxHist)).click();
        Thread.sleep(3000);      
        WebElement searchFieldDropdown = driver.findElement(By.id("ddlSearchParam"));
        Select dropdown = new Select(searchFieldDropdown);
        dropdown.selectByVisibleText("ChargeBack Tx Id");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearchValue"))).sendKeys("123");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(ChargebackId)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownloadExcel"))).click();
        Thread.sleep(3000);
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
		if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
			CommonUtilis.moveDownloadedFileToDatedFolder("searchTxHist", dateFolder);
		} else {
			System.err.println(" No downloaded Excel file found to move.");
		}
		Thread.sleep(3000);
		String screenshotName = "SearchTxHistoryPage";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "SearchTxHistoryPage", screenshotName);
		Thread.sleep(3000);
		
    }
}

