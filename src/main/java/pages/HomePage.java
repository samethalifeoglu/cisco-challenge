package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By GENDER_BUTTON = By.xpath("//span[text()='ERKEK']");
    private final By MY_ACCOUNT_TEXT = By.xpath("//*[contains(@class,'link account-user')] //p[@class='link-text']");

    public LoginPage selectGender(){
        clickElement(GENDER_BUTTON);
        return new LoginPage(driver);
    }

    public void checkLoggedIn(){
        doesElementTextEqualsText(MY_ACCOUNT_TEXT,"Hesabım");
    }
}
