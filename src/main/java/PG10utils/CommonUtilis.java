package PG10utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilis {
	
	  public static void takeScreenshot(WebDriver driver, String moduleName, String label) throws IOException {
	    String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	    String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

	    File baseDir = new File("screenshots" + File.separator + dateFolder + File.separator + moduleName);
	    if (!baseDir.exists()) baseDir.mkdirs();

	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File outputFile = new File(baseDir, label + "_" + timestamp + ".png");
	    Files.copy(screenshot.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	    System.out.println("Screenshot saved: " + outputFile.getAbsolutePath());
	}
	 
    // Wait and return latest download
    public static File waitForNewDownload(File dir, int timeoutInSeconds) throws InterruptedException {
        long end = System.currentTimeMillis() + (timeoutInSeconds * 1000L);
        while (System.currentTimeMillis() < end) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".csv") || name.endsWith(".xlsx"));
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (!f.getName().endsWith(".crdownload") && f.canRead()) return f;
                }
            }
            Thread.sleep(2000);
        }
        return null;
    }

    // Rename downloaded file
    public static void renameDownloadedFile(File file, String label) throws IOException {
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        Path targetDir = Paths.get("downloads", dateFolder, label);
        Files.createDirectories(targetDir);

        String newFileName = label + "_" + timestamp + ".xlsx";
        Path targetPath = targetDir.resolve(newFileName);

        Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved to: " + targetPath);
    }
    

    // Scroll to top
    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        System.out.println("Scrolled to top.");
    }

    // Switch to new browser tab
    public static void switchToNewTab(WebDriver driver) {
        String originalTab = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> driver.getWindowHandles().size() > 1);

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    // Generic calendar date picker
    public static void selectCalendarDate(WebDriver driver, WebElement field, WebElement dateOption) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dateOption)).click();
    }
}


