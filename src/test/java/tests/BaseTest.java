package tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    public WebDriverWait wait;

    FileInputStream fis = new FileInputStream("TestData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public BaseTest() throws IOException {
    }

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.google.com/forms/");
    }

    @AfterClass(description = "Class Level Teardown!")
    public void teardown () {
        driver.quit();
    }
}
