package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {
    private WebDriver driver;

    @FindBy(xpath="//*[@id=\"tJHJj\"]/div[1]/div[2]/div/div[4]/div/span/span/div")
    WebElement settings_icon;

    @FindBy(xpath="//*[@id=\"wizViewportRootId\"]/div[7]/div/div[2]/span/div/div/div[1]/div[3]/span")
    WebElement quizzes_section;

    @FindBy(xpath="//*[@id=\"jmAoTe\"]/div/div/div[1]/label/div/div[1]")
    WebElement make_quizzes;

    @FindBy(xpath="//*[@id=\"wizViewportRootId\"]/div[7]/div/div[2]/div[3]/div[2]")
    WebElement save_button;

    //Constructor
    public SettingsPage(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void openSetting(){
        settings_icon.click();
    }

    public void moveToQuizzes(){
        quizzes_section.click();
    }

    public void setQuizzes(){
        make_quizzes.click();

    }
    public void saveProgress(){
        save_button.click();
    }

}
