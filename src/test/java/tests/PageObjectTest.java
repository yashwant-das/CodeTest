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

    // Login with user credentials
    @Test
    public void googleFormsLogin() {
        //Create object of HomePage Class
        LoginPage loginPage = new LoginPage(driver);
        //Enter username & click next
        loginPage.setUser_email(username);
        loginPage.confirmUser_email();
        //Enter password & click next
        loginPage.setUser_password(passWord);
        loginPage.confirmUser_password();
    }

    //Create a new form
    @Test(dependsOnMethods = {"googleFormsLogin"})
    public void createBlankForm() throws InterruptedException {
        //Create object of FormsHome Class
        FormsHome formsHome = new FormsHome(driver);
        //Create a new form
        formsHome.startNew_form();
        sleep(5000);
    }

    //Fill form data
    @Test(dependsOnMethods = {"createBlankForm"})
    public void fillFormWithTestData() throws InterruptedException, IOException {
        //Create object of FormsHome Class
        UpdateForm updateForm = new UpdateForm(driver);
        //Fill form title
        updateForm.fillFormTitle(formTitle);
        //Fill form Data
        updateForm.fillFormData();
    }

    //Change to quiz and save the progress
    @Test(dependsOnMethods = {"fillFormWithTestData"})
    public void changeToQuizzesAndSaveProgress() throws InterruptedException {
        //Create object of FormsHome Class
        SettingsPage settingsPage = new SettingsPage(driver);
        sleep(3000);
        //Open Setting
        settingsPage.openSetting();
        //Move to Quizzes
        settingsPage.moveToQuizTab();
        Thread.sleep(1000);
        //Make this a quiz
        settingsPage.setToQuizzes();
        Thread.sleep(1000);
        //Save Progress
        settingsPage.saveProgress();
        Thread.sleep(1000);
    }
}
