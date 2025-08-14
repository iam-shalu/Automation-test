
package PG10PageObject;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;

public class StateBlackList {
	WebDriver driver;
	WebDriverWait wait;

	public StateBlackList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//span[normalize-space()='Fraud Control']")
	WebElement fraudControlManu;

	@FindBy(xpath = "/html/body/div[2]/div/nav/div/ul/li[4]/ul/li[9]/a")
	WebElement stateBlackList;

	@FindBy(xpath = "(//span[@class='multiselect-selected-text'])[1]")
	WebElement SubMarchant1;

	@FindBy(xpath = "(//input[@class='form-control multiselect-search'])[1]")
	WebElement searchSubMerchant1;

	@FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")
	WebElement Testacs1;

	@FindBy(xpath = "//a[@class='btn btn-info btn-sm']")
	WebElement addStateBlackList;

	@FindBy(xpath = "(//span[@class='multiselect-selected-text'])[3]")
	WebElement selectSubElement;

	@FindBy(xpath = "(//input[@class='form-control multiselect-search'])[3]")
	WebElement searchselectSubElement1;

	@FindBy(xpath = "//label[@class='radio'][normalize-space()='Test-Acs-01-SM']")
	WebElement Testacs2;

	@FindBy(xpath = "//h3[normalize-space()='State BlackList Details']")
	WebElement StateBlackListText;

	@FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
	WebElement deleteChg;

	@FindBy(xpath = "//tbody/tr[1]/td[4]/a[2]/span[1]")
	WebElement deleteka;

	By subMerchant2Btn = By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]");
	By selectAllChk = By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]");
	By subMerchant2Search = By.xpath("(//input[@class='form-control multiselect-search'])[2]");

	public boolean retryingFindClick(By locator, int maxAttempts) {
		int attempts = 0;
		while (attempts < maxAttempts) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				element.click();
				return true;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				System.err.println("StaleElementException on attempt #" + (attempts + 1) + " for locator: " + locator);
				attempts++;
			} catch (Exception e) {
				System.err.println("Non-stale error during clicking: " + e.getMessage());
				break;
			}
		}
		return false;
	}

	public void interactWithfraudControlStateblackList() throws InterruptedException, IOException {

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(stateBlackList)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(SubMarchant1)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(searchSubMerchant1)).sendKeys("Test-acs-01");
		wait.until(ExpectedConditions.elementToBeClickable(Testacs1)).click();
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"fileInput\"]")));
		WebElement blackListUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
		String filePath = "D:\\Automation\\Excel file\\State BlackList\\StateBlackList.xlsx";
		blackListUpload.sendKeys(filePath);
		System.out.println("File uploaded successfully.");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(addStateBlackList)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectSubElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchselectSubElement1)).sendKeys("Test-acs-01");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Testacs2)).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("State"))).sendKeys("Chattisgarh");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_Save"))).click();
		Thread.sleep(3000);
		try {
			boolean dropdownClicked = retryingFindClick(
					By.xpath("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"), 3);
			if (!dropdownClicked) {
				throw new RuntimeException("Failed to click SubMerchant dropdown after retries.");
			}
			System.out.println("Dropdown clicked successfully.");
			Thread.sleep(1000);
			boolean selectAllClicked = retryingFindClick(
					By.xpath("(//a[contains(@class, 'multiselect-all')]//label[normalize-space()='Select all'])[2]"),
					3);
			if (!selectAllClicked) {
				throw new RuntimeException("Failed to click Select All checkbox after retries.");
			}
			System.out.println("Select all clicked successfully.");
			Thread.sleep(1000);
			WebElement freshSearchBox = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//input[@class='form-control multiselect-search'])[2]")));
			freshSearchBox.clear();
			freshSearchBox.sendKeys("Test-acs-01");
			freshSearchBox.sendKeys(Keys.ENTER);
			System.out.println("Search text entered and ENTER key pressed.");

			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@class='multiselect-container dropdown-menu show']//label[@class='checkbox'][normalize-space()='Test-Acs-01-SM']")));

			checkbox.click();
			System.out.println("Checkbox for Test-Acs-01 clicked successfully.");

		} catch (Exception e) {
			System.err.println("Error occurred in SubMerchant dropdown interaction: " + e.getMessage());
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		String screenshotName = "StateBlackList_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "FraudControl-State BlackList", screenshotName);
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("Chattisgarh");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteChg)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteka)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
	}

}
