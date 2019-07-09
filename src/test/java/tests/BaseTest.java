package tests;

import Utils.SuiteConfiguration;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static String dateName = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa").format(new Date());
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    // Reading configuration from the property file
    SuiteConfiguration config = new SuiteConfiguration();
    String formTitle = config.getProperty("GoogleFormTitle");
    String baseUrl = config.getProperty("url");
    byte[] decodedUsername = Base64.getDecoder().decode(config.getProperty("username"));
    String username = new String(decodedUsername);
    byte[] decodedPassword = Base64.getDecoder().decode(config.getProperty("password"));
    String password = new String(decodedPassword);
    WebDriver driver;
    WebDriverWait wait;

    public BaseTest() throws IOException {
    }

    //Capture the screenshot and return screenshot path
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        // Setting screenshot folder
        String destination = System.getProperty("user.dir") + "/test-report/snapshots/" + screenshotName + "-" + dateName + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        // return screenshot path
        return destination;
    }

    @BeforeTest
    public void setup() {
        // Setting up Chrome Driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseUrl);

        // Extent Reports instantiation
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-report/report/AutomationTestReport-" + dateName + ".html");
        extent = new ExtentReports();

        // Extent Configurations
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "MacBookPro");
        extent.setSystemInfo("Environment", "Staging");
        extent.setSystemInfo("User Name", "Yashwant");
        htmlReporter.config().setDocumentTitle("CodeTest");
        htmlReporter.config().setReportName("Automation Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            //Set the label colour
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case FAILED", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " Test Case FAILED", ExtentColor.RED));
            //Capture screenshot & path and store the path of the screenshot in the string "screenshotPath"
            String screenshotPath = getScreenShot(driver, result.getName());
            //Add the screenshot to the report
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }
    }

    @AfterClass(description = "Class Level Teardown!")
    public void teardown() {
        driver.quit();
        extent.flush();
    }
}
