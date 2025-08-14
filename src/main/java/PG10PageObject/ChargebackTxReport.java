package PG10PageObject;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChargebackTxReport {
	WebDriver driver;
	WebDriverWait wait;

	public ChargebackTxReport(WebDriver driver) {
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

	@FindBy(xpath = "//a[normalize-space()='ChargeBack Txs Report']")
	WebElement chargebackTxReport;

	public void interactWithtransactionchargebackTX() throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(transactionsMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(bnibMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(chargebackTxReport)).click();
		

	}
}
