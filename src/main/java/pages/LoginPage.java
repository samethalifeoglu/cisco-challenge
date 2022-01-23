package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends BasePage {

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
    }

    private final By LOGIN_LINK = By.xpath(getDataTestId("linkLogin"));
    private final By EMAIL_INPUT_FIELD = By.xpath(getDataTestId("inputEmail"));
    private final By PASSWORD_INPUT_FIELD = By.xpath(getDataTestId("inputPassword"));
    private final By LOGIN_BUTTON = By.xpath(getDataTestId("btnLogin"));

    public HomePage loginWithValidUser(String email, String password){
        clickElement(LOGIN_LINK);
        setText(EMAIL_INPUT_FIELD, email);
        setText(PASSWORD_INPUT_FIELD, password);
        clickElement(LOGIN_BUTTON);
        return new HomePage(driver);
    }
}
