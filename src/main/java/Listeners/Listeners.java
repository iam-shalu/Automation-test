package Listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Reports.ExtentReportManager;

public class Listeners implements ITestListener {
	
	ExtentReports extent = ExtentReportManager.getReportObject();
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}

