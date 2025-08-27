package PG10PageObject;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class BlackListCustomer {
	WebDriver driver;
	WebDriverWait wait;

	public BlackListCustomer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

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

	@FindBy(xpath = "//h3[normalize-space()='Blacklist Customer']")
	WebElement BlackListText;

	@FindBy(xpath = "//span[@class='user-name']")
	WebElement LogoutText;

	@FindBy(xpath = "//a[contains(@title,'Delete Blacklist customer')]//span[contains(@class,'fa-lg')]")
	WebElement delete2;

	public void interactWithfraudControlblackList() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(blackListCustomer)).click();
			driver.manage().window().maximize();
			WebElement blackListUpload = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='blacklistcustomerfile']")));
			String filePath = "D:\\Automation\\Excel file\\Blacklist Customer\\BlackList.xlsx";
			blackListUpload.sendKeys(filePath);
			System.out.println("File uploaded successfully.");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("frmimport"))).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader"))); // Optional
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
			
			String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

			if (CommonUtilis.waitForFileDownload(downloadDir, ".xlsx", 20)) {
				CommonUtilis.moveDownloadedFileToDatedFolder("BlackListCustomer", dateFolder);
			} else {
				System.err.println(" No downloaded Excel file found to move.");
			}
			wait.until(ExpectedConditions.elementToBeClickable(ManualAddBlackListCust)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name"))).sendKeys("akash");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("akash13@gmail.com");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MobileNo"))).sendKeys("9632629099");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("IPaddress"))).sendKeys("9.8.7.6");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
			Thread.sleep(3000);
			String expectedEmail = "akash13@gmail.com";
			WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(searchBlackListCust));
			searchBox.clear();
			searchBox.sendKeys(expectedEmail);
			Thread.sleep(3000);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + expectedEmail + "')]")));

			String screenshotName = "BlackListCustomerText_Page_Screenshot";
			System.out.println("Capturing full page screenshot...");
			CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-BlackListCustomer", screenshotName);
			Thread.sleep(3000);
			while (true) {
				List<WebElement> deleteButtons = driver.findElements(By.xpath(
						"//tr[td[contains(text(), '" + expectedEmail + "')]]//a[contains(@onclick, 'DeleteRow')]"));
				if (deleteButtons.isEmpty())
					break;

				WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0)));
				deleteBtn.click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
						"//tr[td[contains(text(), '" + expectedEmail + "')]]//a[contains(@onclick, 'DeleteRow')]")));
				Thread.sleep(3000);
			}

			List<WebElement> remainingRows = driver
					.findElements(By.xpath("//td[contains(text(), '" + expectedEmail + "')]"));
			if (remainingRows.isEmpty()) {
				System.out.println(" All blacklist customers with email '" + expectedEmail + "' deleted successfully.");
				Thread.sleep(3000);
			} else {
				System.err.println("Some records with email '" + expectedEmail + "' still exist.");
			}
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		} catch (Exception e) {
			System.err.println(" Test failed in BlackListCustomer interaction: " + e.getMessage());
			e.printStackTrace();
		}
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		Thread.sleep(3000);
	}
}
