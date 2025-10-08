package PG10PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10utils.CommonUtilis;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Dashboard {
	WebDriver driver;
	WebDriverWait wait;

	public Dashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div")
	WebElement limitBarPopup;

	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div[3]/button")
	WebElement popupCloseButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/input")
	WebElement dateRangeField;

	@FindBy(xpath = "/html/body/div[3]/div[1]/ul/li[2]")
	WebElement yesterday;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/div[1]/div[3]/div/button[1]")
	WebElement filterButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[3]/div/div/div[2]/div/div[1]/input")
	WebElement payoutDateRangeField;

	@FindBy(xpath = "/html/body/div[5]/div[1]/ul/li[2]")
	WebElement calendarYesterday;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[3]/div/div/div[2]/div/div[6]/button")
	WebElement payoutFilterButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[11]/div/div/div[2]/div/div[2]/div[2]/button")
	WebElement loadListButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[12]/div/div/div[2]/div[1]/div/div[4]/button")
	WebElement loadDataButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/div[1]/div[3]/div/button[2]")
	WebElement viewTxsButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]/form/button")
	WebElement approvedTxsButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div/div/div[1]/div[2]/button")
	WebElement exportButton;

	public void interactWithDashboard() throws Exception {
		closeLimitBarIfPresent();

		CommonUtilis.selectCalendarDate(driver, dateRangeField, yesterday);

		filterButton.click();
		Thread.sleep(1000);

		CommonUtilis.selectCalendarDate(driver, payoutDateRangeField, calendarYesterday);

		payoutFilterButton.click();
		Thread.sleep(1000);

		clickAllLoadGraphButtons();
		Thread.sleep(2000);

		CommonUtilis.scrollToTop(driver);

		Thread.sleep(3000);
		String screenshotName = "Dashboard_Page_Screenshot";
		System.out.println("Capturing full page screenshot...");
		CommonUtilis.captureFullPageScreenshot(driver, "Dashboard", screenshotName);
		CommonUtilis.scrollToTop(driver);
	}

	public void clickAllLoadGraphButtons() {
		try {
			int index = 0;
			List<WebElement> loadGraphButtons;

			while (true) {
				loadGraphButtons = driver.findElements(By.xpath("//button[contains(normalize-space(),'Load Graph')]"));

				if (index >= loadGraphButtons.size()) {
					break; // No more new buttons
				}

				try {
					WebElement button = loadGraphButtons.get(index);
					CommonUtilis.scrollToTop(driver);
					wait.until(ExpectedConditions.elementToBeClickable(button)).click();
					System.out.println("Clicked Load Graph button #" + (index + 1));
					Thread.sleep(1000); // Allow time for dynamic content like Load List/Data

					// Special handling: after 7th click, click Load List + Load Data
					if (index == 6) {
						clickLoadListAndDataButtons(); // Click only once
					}

					index++;
				} catch (StaleElementReferenceException e) {
					System.out.println("Stale element, refreshing button list...");
					// Do nothing — It will be fetched again in the next iteration.
				} catch (Exception e) {
					System.out.println("Error clicking Load Graph button #" + (index + 1) + ": " + e.getMessage());
					index++; // Skip problematic button
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing Load Graph buttons: " + e.getMessage());
		}
	}

	private void clickLoadListAndDataButtons() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loadListButton);
			wait.until(ExpectedConditions.elementToBeClickable(loadListButton)).click();
			System.out.println("Clicked 'Load List' button.");
			Thread.sleep(1000);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loadDataButton);
			wait.until(ExpectedConditions.elementToBeClickable(loadDataButton)).click();
			System.out.println("Clicked 'Load Data' button.");
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Error clicking Load List/Data buttons: " + e.getMessage());
		}
	}

	private void closeLimitBarIfPresent() throws InterruptedException {
		try {
			
			wait.until(ExpectedConditions.visibilityOf(limitBarPopup));

			// === Take screenshot BEFORE closing the pop up ===
			CommonUtilis.takeScreenshot(driver, "Dashboard", "LimitBarPopup");

			Thread.sleep(1000);
			popupCloseButton.click();
			System.out.println("Limit Bar popup closed.");

		} catch (TimeoutException e) {
			System.out.println("Limit Bar popup not present.");
		} catch (IOException e) {
			System.out.println("Screenshot error: " + e.getMessage());
			
		}
	}

}
