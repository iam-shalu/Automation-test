package PG10Base;

import java.io.File;
import java.io.IOException;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import PG10PageObject.Transactions;
import PG10PageObject.WhiteListCustomer;
import PG10PageObject.WhiteList_MechantIP;
import PG10PageObject.Logout;
import PG10PageObject.Payout_BlackListCustomer;
import PG10PageObject.BlackListCustomer;
import PG10PageObject.FTDWhiteListUser;
import PG10PageObject.FraudControl;
//import PG10PageObject.FraudControl;
import PG10PageObject.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class PG10Base {
    public static Logger log = Logger.getLogger(PG10Base.class.getName());
    public static WebDriver driver;
    
    public Login loginPage;
    public Transactions transactionPage;
    public Logout logoutPage;
    public FraudControl fraudControlPage;
    public BlackListCustomer blackListCustomerPage;
    public WhiteListCustomer whiteListCustomerPage;
    public WhiteList_MechantIP whiteListMerchantIPPage;
    public Payout_BlackListCustomer payoutBlackListCustomerPage;
    public FTDWhiteListUser ftdwhiteListPage;
   
    


    @BeforeTest
    public WebDriver initializeDriver() {
        DOMConfigurator.configure("log4j.xml");
        WebDriverManager.chromedriver().setup();

        String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        log.info("Browser launched and maximized");

        driver.get("https://test.paygate10.com/");
       
        log.info("Navigated to PG10 login page");
        return driver;
        
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
        String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";
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

    @BeforeClass
    public void createPageObjects() {
    	
        loginPage = new Login(driver);
        transactionPage = new Transactions(driver);
        logoutPage = new Logout(driver);
        fraudControlPage = new FraudControl(driver);
        blackListCustomerPage = new BlackListCustomer(driver);
        whiteListCustomerPage = new WhiteListCustomer(driver);
        whiteListMerchantIPPage = new WhiteList_MechantIP(driver);
        payoutBlackListCustomerPage = new Payout_BlackListCustomer(driver);
     
        ftdwhiteListPage = new FTDWhiteListUser(driver);
        
        
        
    }


}
