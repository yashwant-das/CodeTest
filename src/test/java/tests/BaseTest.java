package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriverWait wait;
    WebDriver driver;
    String baseUrl = "https://www.google.com/forms/";

    String username = "humbleautomator@gmail.com";
    String passWord = "CodeTest#1234";
    String formTitle = "Automated Google Form Entry";

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
