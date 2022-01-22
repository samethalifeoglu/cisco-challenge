package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By LOGIN_BUTTON = By.xpath("//p[text()='Giriş Yap']");
    private final By EMAIL_INPUT_FIELD = By.xpath(getDataTestId("email-input"));
    private final By PASSWORD_INPUT_FIELD = By.xpath(getDataTestId("password-input"));
    private final By LOGIN_SUBMIT_BUTTON = By.xpath("//button[@type='submit']");

    public HomePage loginValidUser(String email, String password){
        clickElement(LOGIN_BUTTON);
        setText(EMAIL_INPUT_FIELD, email);
        setText(PASSWORD_INPUT_FIELD, password);
        clickElement(LOGIN_SUBMIT_BUTTON);
        return new HomePage(driver);
    }
}
