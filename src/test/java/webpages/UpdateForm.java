package webpages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class UpdateForm {

    //Reading Excel Data
    int sheet_index = 0;
    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//test-data/TestData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheetAt(0);

    @FindBy(xpath = "//*[@id=\"SchemaEditor\"]/div/div[1]/div/div/div[1]/div")
    WebElement new_question;

    @FindBy(xpath = "//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[2]/div[2]/div[1]/div/div/span/div/div[1]/div[2]/textarea")
    WebElement form_title;
    private WebDriver driver;

    //Constructor
    public UpdateForm(WebDriver driver) throws IOException {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Fill form title
    public void fillFormTitle(String title) {
        form_title.click();
        form_title.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        form_title.sendKeys(title);
    }

    //Function to add a question
    public void addQuestion() {
        new_question.click();
    }

    //Function to add an option
    public void addOption(int val) {
        driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div[" + val + "]/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/div/div[2]/input")).click();
    }

    //Function to fill form data from testData file
    public void fillFormData() throws InterruptedException {
        sheet_index = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i <= sheet_index - 1; i++) {

            //Enter Question
            WebElement question = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div[" + i + "]/div/div/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/span/div/div/div[1]/div[2]/textarea"));
            question.click();
            question.sendKeys(sheet.getRow(i).getCell(0).toString());
            Thread.sleep(1000);

            //Enter Options
            for (int j = 1; j <= 4; j++) {

                WebElement options = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div[" + i + "]/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[1]/div[" + j + "]/div[1]/div[3]/div[1]/div/span/div/div/div[1]/input"));
                if (j <= 1) {
                    options.click();
                }
                options.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                options.sendKeys(sheet.getRow(i).getCell(j).toString());
                Thread.sleep(1000);

                if (j <= 3) {
                    addOption(i);
                    Thread.sleep(1000);
                }
            }

            if (i <= 2) {
                addQuestion();
                Thread.sleep(1000);
            }
        }

    }
}
