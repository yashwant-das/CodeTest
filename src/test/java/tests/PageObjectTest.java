package tests;

import org.testng.annotations.Test;
import webpages.FormsHome;
import webpages.LoginPage;
import webpages.SettingsPage;
import webpages.UpdateForm;

import java.io.IOException;

import static java.lang.Thread.*;

public class PageObjectTest extends BaseTest {

    public PageObjectTest() throws IOException {
    }

    // Login with user credentials
    @Test
    public void googleFormsLogin() {
        logger = extent.createTest("Google Forms Login");
        //Create object of HomePage Class
        LoginPage loginPage = new LoginPage(driver);
        //Enter username & click next
        logger.createNode("Enter username");
        loginPage.setUser_email(username);
        logger.createNode("Click next");
        loginPage.confirmUser_email();
        //Enter password & click next
        logger.createNode("Entering password");
        loginPage.setUser_password(password);
        logger.createNode("Click next");
        loginPage.confirmUser_password();
    }

    //Create a new form
    @Test(dependsOnMethods = {"googleFormsLogin"})
    public void createBlankForm() throws InterruptedException {
        logger = extent.createTest("Create a blank Google Form");
        //Create object of FormsHome Class
        FormsHome formsHome = new FormsHome(driver);
        //Create a new form
        logger.createNode("Create a new form");
        formsHome.startNew_form();
        sleep(5000);
    }

    //Fill form data
    @Test(dependsOnMethods = {"createBlankForm"})
    public void fillFormWithTestData() throws InterruptedException, IOException {
        logger = extent.createTest("Fill Forms With Test Data");
        //Create object of FormsHome Class
        UpdateForm updateForm = new UpdateForm(driver);
        //Fill form title
        logger.createNode("Fill form title");
        updateForm.fillFormTitle(formTitle);
        //Fill form Data
        logger.createNode("Fill form Data");
        updateForm.fillFormData();
    }

    //Change to quiz and save the progress
    @Test(dependsOnMethods = {"fillFormWithTestData"})
    public void changeToQuizzesAndSaveProgress() throws InterruptedException {
        logger = extent.createTest("Access settings, Change To Quizzes and Save the Progress");
        //Create object of FormsHome Class
        SettingsPage settingsPage = new SettingsPage(driver);
        sleep(3000);
        //Open Setting
        logger.createNode("Open Setting");
        settingsPage.openSetting();
        //Move to Quizzes
        logger.createNode("Move to Quizzes");
        settingsPage.moveToQuizTab();
        Thread.sleep(1000);
        //Make this a quiz
        logger.createNode("Make this a quiz");
        settingsPage.setToQuizzes();
        Thread.sleep(1000);
        //Save Progress
        logger.createNode("Save Progress");
        settingsPage.saveProgress();
        Thread.sleep(1000);
    }
}
