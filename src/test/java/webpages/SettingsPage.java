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

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Presentation'])[1]/following::span[1]")
    WebElement quizzes_tab;

    @FindBy(xpath="//*[@id=\"jmAoTe\"]/div/div/div[1]/label/div/div[1]")
    WebElement make_this_a_quizz;

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[2]/following::span[2]")
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

    public void moveToQuizTab(){
        quizzes_tab.click();
    }

    public void setToQuizzes(){
        make_this_a_quizz.click();

    }
    public void saveProgress(){
        save_button.click();
    }

}
