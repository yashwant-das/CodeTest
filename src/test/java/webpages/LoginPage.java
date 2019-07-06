package webpages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    @FindBy(id = "identifierId")
    WebElement user_email;
    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    WebElement user_password;
    @FindBy(xpath = "//*[@id=\"identifierNext\"]/span/span")
    WebElement identifierNext;
    @FindBy(xpath = "//*[@id=\"passwordNext\"]/span/span")
    WebElement passwordNext;
    private WebDriver driver;


    //Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    // Enter email
    public void setUser_email(String email) {
        user_email.clear();
        user_email.sendKeys(email);
    }

    // Enter password
    public void setUser_password(String password) {
        user_password.clear();
        user_password.sendKeys(password);
    }

    //Click next after entering email
    public void confirmUser_email() {
        identifierNext.click();
    }

    //Click next after entering password
    public void confirmUser_password() {
        passwordNext.click();
    }
}

