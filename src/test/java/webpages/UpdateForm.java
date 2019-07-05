package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateForm {
    private WebDriver driver;


    @FindBy(xpath="//*[@id=\"SchemaEditor\"]/div/div[1]/div/div/div[1]/div")
    WebElement new_question;


    @FindBy(xpath="//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[3]/div/div/div/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/div/div[2]/input")
    WebElement new_option;

    @FindBy(xpath="//*[@id=\"SchemaEditor\"]/div/div[2]/div/div/div[2]/div[2]/div[1]/div/div/span/div/div[1]/div[2]/textarea")
    WebElement form_title;

    //Constructor
    public UpdateForm(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void fillFormTitle(String title){
        form_title.click();
        form_title.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        form_title.sendKeys(title);
    }
    public void addQuestion(){
       new_question.click();
    }

    public void addOption(){
        new_option.click();
    }
}
