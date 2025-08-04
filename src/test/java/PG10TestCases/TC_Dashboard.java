package PG10TestCases;

import org.testng.annotations.Test;

import PG10Base.PG10Base;

public class TC_Dashboard extends PG10Base {

    @Test(priority = 2)
    public void verifyDashboardButtons() throws Exception {
        log.info("==== Starting Dashboard Test ====");

        // Handle dashboard interactions (limit bar, filters, export)
        dashboardPage.interactWithDashboard();

        // Capture full-page screenshot after interactions
        captureFullPageScreenshot("Dashboard", "After_Button_Clicks");

        log.info("Full-page screenshot captured: Dashboard > After_Button_Clicks");
        log.info("==== Dashboard Test Completed ====");
    }
}
