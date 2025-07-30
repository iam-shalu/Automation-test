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

import net.bytebuddy.asm.MemberSubstitution.FieldValue;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class WhiteList_MechantIP {
    WebDriver driver;
    WebDriverWait wait;

    public WhiteList_MechantIP(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Page Elements
    @FindBy(xpath = "//span[normalize-space()='Fraud Control']") 
    WebElement fraudControlManu;

    @FindBy(xpath = "//a[normalize-space()='White List Merchant IP']") 
    WebElement whiteListMerchantIp;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[1]")
    WebElement sMasterMerchant;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@placeholder='Search']")
    WebElement searchWhiteListMasterMerchant;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu show']//label[@class='radio'][normalize-space()='Test-Acs-01']")
    WebElement Testacs01;

    @FindBy(xpath = "//a[normalize-space()='Add Whitelist Merchant IP']")
    WebElement AddWhiteListMerchantIp;

    @FindBy(xpath = "//button[@title='Select Any']")
    WebElement addWhiteLiStselectMasterMerchant;

    @FindBy(xpath = "//*[@id=\"frmWhiteListCustomer\"]/div[1]/div/div[1]/div/div/div/ul/li[1]/div/input")
    WebElement searchTestAcs;

    @FindBy(xpath = "//*[@id=\"frmWhiteListCustomer\"]/div[1]/div/div[1]/div/div/div/ul/li[500]/a/label")
    WebElement selectTestAcs01;

    @FindBy(xpath = "(//button[@class=\"multiselect dropdown-toggle btn btn-default\"])[2]")
    WebElement sMasterMerchant2;

    @FindBy(xpath = "(//input[@class=\"form-control multiselect-search\"])[2]")
    WebElement searchWhiteListMasterMerchant2;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/ul/li[500]/a/label")
    WebElement Testacs013;

    @FindBy(xpath = "//h3[text()='White list Master Merchant IP Details']")
    WebElement WhitelistMasterMerchantIPDetails ;

    @FindBy(xpath = "//span[@class='fa fa-trash-o fa-lg']")
    WebElement deleteIp;

    public void interactWithfraudControlwhiteListMerchIP() throws IOException, InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(fraudControlManu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(whiteListMerchantIp)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant)).sendKeys("Test-acs-01");
        wait.until(ExpectedConditions.elementToBeClickable(Testacs01)).click();

        driver.manage().window().maximize();
        waitForPageLoad();

        WebElement WhiteListMerchantIPUpload = driver.findElement(By.xpath("//input[@id=\"fileInput\"]"));
        String filePath = "D:\\Automation\\Excel file\\WhiteList Merchant Ip\\WhiteList_MerchantIP.xlsx"; 
        WhiteListMerchantIPUpload.sendKeys(filePath);
        waitForUploadComplete();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnimport"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AddWhiteListMerchantIp)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addWhiteLiStselectMasterMerchant)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchTestAcs)).sendKeys("Test-acs-01");
        wait.until(ExpectedConditions.elementToBeClickable(selectTestAcs01)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("IP"))).sendKeys("1.5.7.8");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActive"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(sMasterMerchant2)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchWhiteListMasterMerchant2)).sendKeys("Test-acs-01");
        wait.until(ExpectedConditions.elementToBeClickable(Testacs013)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();

        captureFullPageScreenshot(driver, "Fraud Control", "WhiteListMasterMerchantIp", "WhitelistMasterMerchantIPDetails");
        
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnExport"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1.5.7.8");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnFilter"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(deleteIp)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch"))).sendKeys("1.1.1.1");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(deleteIp)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
        Thread.sleep(3000);
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    private void waitForUploadComplete() {
        try {
            Thread.sleep(5000); // Replace with explicit condition if possible
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

    private void scrollToTopAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
}



