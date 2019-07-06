package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import webpages.FormsHome;
import webpages.LoginPage;
import webpages.SettingsPage;
import webpages.UpdateForm;
import java.io.IOException;

import static java.lang.Thread.*;

public class PageObjectTest extends BaseTest {

    String username= "humbleautomator@gmail.com";
    String passWord= "CodeTest#1234";
    String formTitle = "Automated Google Form Entry";

    public PageObjectTest() throws IOException {
    }

    @Test
    public void loginAsTestUser() {
        //Create object of HomePage Class
        LoginPage loginPage = new LoginPage(driver);

        //Enter username & click next
        loginPage.setUser_email(username);
        loginPage.confirmUser_email();

        //Enter password & click next
        loginPage.setUser_password(passWord);
        loginPage.confirmUser_password();

    }

    @Test(dependsOnMethods = {"loginAsTestUser"})
    public void createBlankForm() throws InterruptedException {
        //Create object of FormsHome Class
        FormsHome formsHome = new FormsHome(driver);
        formsHome.startNew_form();
        sleep(5000);
    }

    @Test(dependsOnMethods = {"createBlankForm"})
    public void updateForm() throws InterruptedException {
        //Create object of FormsHome Class
        UpdateForm updateForm = new UpdateForm(driver);
        updateForm.fillFormTitle(formTitle);


        sheet_index = sheet.getPhysicalNumberOfRows();
        for(int i = 1; i<=sheet_index-1; i++) {

            //Enter Question
            WebElement question = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div["+i+"]/div/div/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/span/div/div/div[1]/div[2]/textarea"));
            question.click();
            question.sendKeys(sheet.getRow(i).getCell(0).toString());
            Thread.sleep(1000);

            //Enter Options
            for(int j = 1; j<=4; j++){

                WebElement options = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div["+i+"]/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[1]/div["+j+"]/div[1]/div[3]/div[1]/div/span/div/div/div[1]/input"));
                if (j<=1){
                    options.click();
                }
                options.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
                options.sendKeys(sheet.getRow(i).getCell(j).toString());
                Thread.sleep(1000);

                if (j<=3){
                    add_option(i);
                    Thread.sleep(1000);
                }
            }

            if(i<=2){
                addQuestion();
                Thread.sleep(1000);
            }
        }

    }

    @Test(dependsOnMethods = {"updateForm"})
    public void changeToQuizzes() throws InterruptedException {
        //Create object of FormsHome Class
        SettingsPage settingsPage = new SettingsPage(driver);
        sleep(3000);
        settingsPage.openSetting();
        settingsPage.moveToQuizTab();
        Thread.sleep(1000);
        settingsPage.setToQuizzes();
        Thread.sleep(1000);
        settingsPage.saveProgress();
        Thread.sleep(1000);
    }

    public void add_option(int val){
        driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div["+val+"]/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/div/div[2]/input")).click();
    }

    public void addQuestion(){
        WebElement addQuestionBtn = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[1]/div/div/div[1]/div"));
        addQuestionBtn.click();
    }
}
