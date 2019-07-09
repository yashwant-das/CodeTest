package tests;

import Utils.SuiteConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class BaseTest {

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

    @BeforeTest
    public void setup() {
        //Setting up Chrome Driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @AfterClass(description = "Class Level Teardown!")
    public void teardown() {
        driver.quit();
    }
}
