package PG10Base;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import PG10PageObject.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PG10Base {

    public static Logger log = Logger.getLogger(PG10Base.class.getName());
    public static WebDriver driver;

    public static Login loginPage;
    public static Transactions transactionPage;
    public static BlackListCustomer blackListCustomerPage;
    public static Logout logoutPage;
    public static WhiteListCustomer whiteListCustomerPage;
    public static WhiteList_MechantIP whiteListMerchantIPPage;
    public static Payout_BlackListCustomer payoutBlackListCustomerPage;
    public static FTDWhiteListUser ftdwhiteListPage;
    public static VPABlackList vpablackListPage;
    public static StateBlackList stateblackListPage;
    public static CityBlackList cityblackListPage;
    public static FraudControl fraudControlPage; 
    public static DepositTransaction depositTransactionPage;
    public static PayoutTransaction payoutTransactionPage;
    public static Dashboard	dashboardPage;
    
    
    @BeforeSuite
    public void setUpSuite() {
        try {
            DOMConfigurator.configure("log4j.xml");
            WebDriverManager.chromedriver().setup();

            String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadDir);
            prefs.put("download.prompt_for_download", false);
            prefs.put("safebrowsing.enabled", true);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--remote-allow-origins=*");
            
            //options.addArguments("--force-device-scale-factor=0.75");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            log.info("Browser launched and maximized");

            driver.get("https://paygate10.com/Login");
            log.info("Navigated to PG10 login page");

            // Page object initialization
            loginPage = new Login(driver);
            transactionPage = new Transactions(driver);
            blackListCustomerPage = new BlackListCustomer(driver);
            logoutPage = new Logout(driver);
            whiteListCustomerPage = new WhiteListCustomer(driver);
            whiteListMerchantIPPage = new WhiteList_MechantIP(driver);
            ftdwhiteListPage = new FTDWhiteListUser(driver);
            vpablackListPage = new VPABlackList(driver);
            stateblackListPage = new StateBlackList(driver);
            cityblackListPage = new CityBlackList(driver);
            fraudControlPage = new FraudControl(driver);
            depositTransactionPage = new DepositTransaction(driver);
            payoutTransactionPage = new PayoutTransaction(driver);
            dashboardPage = new Dashboard(driver);
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Setup failed: " + e.getMessage());
            throw new RuntimeException("Driver setup or page object creation failed");
        }
    }

    public void captureFullPageScreenshot(String moduleName, String label) {
        try {
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

            File baseDir = new File("screenshots" + File.separator + dateFolder + File.separator + moduleName);
            if (!baseDir.exists()) baseDir.mkdirs();

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);

            File screenshotFile = new File(baseDir, label + "_" + timestamp + ".png");
            ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);

            log.info("Full-page screenshot captured: " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Full-page screenshot capture failed: " + e.getMessage());
        }
    }

    public void moveDownloadedFileToDatedFolder(String moduleName, String dateStr) {
    	String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        File downloadFolder = new File(downloadDir);
        File[] files = downloadFolder.listFiles((dir, name) -> name.endsWith(".xlsx"));

        if (files == null || files.length == 0) {
            log.warn("No downloaded Excel file found to move.");
            return;
        }

        File latestFile = files[0];
        for (File f : files) {
            if (f.lastModified() > latestFile.lastModified()) {
                latestFile = f;
            }
        }

        File targetFolder = new File(downloadDir + File.separator + dateStr + File.separator + moduleName);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
            log.info("Created folder: " + targetFolder.getAbsolutePath());
        }

        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        File newFile = new File(targetFolder, "Export_" + timestamp + ".xlsx");

        if (latestFile.renameTo(newFile)) {
            log.info("Exported file moved to: " + newFile.getAbsolutePath());
        } else {
            log.error("Failed to move exported file.");
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed after suite completion");
        }
    }
}
