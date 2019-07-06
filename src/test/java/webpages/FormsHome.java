package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormsHome {
    @FindBy(xpath = "//*[@id=\":1f\"]/div[1]/img")
    WebElement blank_form;
    private WebDriver driver;

    //Constructor
    public FormsHome(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }
    //Page functions

    // Start a new form
    public void startNew_form() {
        blank_form.click();
    }
}
