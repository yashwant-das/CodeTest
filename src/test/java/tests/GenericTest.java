package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import webpages.LoginPage;

import java.io.IOException;

public class GenericTest extends BaseTest {
    private LoginPage googleLoginPage;
    int sheet_index = 0;

    String username= "humbleautomator@gmail.com";
    String passWord= "CodeTest#1234";
    String formTitleText = "Automated Google Form Test";

    public GenericTest() throws IOException {
    }

    @Test
    public void BaseCompliedTest() throws InterruptedException {

        enterUserCredentials();
        createForm();
        fillformtitle();

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
                    Thread.sleep(3000);
                }
            }

            if(i<=2){
                addQuestion();
            }
        }

    }

    public void fillformtitle(){
        //Enter Form Title
        WebElement formTitle = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[2]/div[2]/div[1]/div/div/span/div/div[1]/div[2]/textarea"));
        formTitle.click();
        formTitle.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        formTitle.sendKeys(formTitleText);
    }
    public void createForm() throws InterruptedException {//Create a blank form
        WebElement create_blankForm = driver.findElement(By.xpath("//*[@id=\":1f\"]/div[1]/img"));
        create_blankForm.click();
        Thread.sleep(5000);

    }

    public void enterUserCredentials() throws InterruptedException {
        //Enter Username
        WebElement userEmail = driver.findElement(By.id("identifierId"));
        userEmail.sendKeys(username);

        //Click Next-1
        WebElement user_next = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span"));
        user_next.click();

        //Enter Password
        WebElement userPassword = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        userPassword.sendKeys(passWord);

        //Click Next-2
        WebElement pass_next = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span"));
        pass_next.click();
        Thread.sleep(5000);

    }

    public void add_option(int val){
        driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div["+val+"]/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/div/div[2]/input")).click();
    }

    public void save(){
        //Save
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"wizViewportRootId\"]/div[7]/div/div[2]/div[3]/div[2]"));
        saveButton.click();
    }

    public void doFinalSteps() throws InterruptedException {
        //Click on Setting icon
        WebElement settingButton = driver.findElement(By.xpath("//*[@id=\"tJHJj\"]/div[1]/div[2]/div/div[4]/div/span/span/div"));
        settingButton.click();

        //Click on quizzes
        WebElement quizzesButton = driver.findElement(By.xpath("//*[@id=\"wizViewportRootId\"]/div[7]/div/div[2]/span/div/div/div[1]/div[3]/span"));
        quizzesButton.click();

        //Make this a quiz
        WebElement makeQuizz = driver.findElement(By.xpath("//*[@id=\"jmAoTe\"]/div/div/div[1]/label/div/div[1]"));
        makeQuizz.click();
        Thread.sleep(1000);

        save();
        driver.close();

    }

    public void addQuestion(){
        WebElement addQuestionBtn = driver.findElement(By.xpath("//*[@id=\"SchemaEditor\"]/div/div[1]/div/div/div[1]/div"));
        addQuestionBtn.click();
    }
}
