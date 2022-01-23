package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebAppPage extends BasePage {

  private final By WEB_APP_LOGIN_EMAIL = By.id("email");
  private final By WEB_APP_LOGIN_CONTINUE = By.id("continue-button");
  private final By WEB_APP_LOGIN_PASSWORD = By.id("password-input");
  private final By WEB_APP_LOGIN_BUTTON = By.id("login-button");
  private final By WALL = By.xpath("//*[@data-testid='listItem']//*[contains(text(),'Wall')]");
  private final By NEW_POST = By.xpath(getDataTestId("new-post-text"));
  private final By NEW_POST_INPUT = By.xpath("//*[@data-testid='post-modal-input']//*[contains(@placeholder,'Test?')]");
  private final By NEW_POST_ADD_IMAGE = By.xpath(getDataTestId("post-modal-add-image"));
  private final By NEW_POST_IMAGE_UPLOAD_INPUT = By.xpath("//input[@class='fsp-local-source__fileinput']");
  private final By NEW_POST_IMAGE_UPLOAD_SAVE_BUTTON = By.xpath("//*[@title='Save']");
  private final By NEW_POST_IMAGE_UPLOAD_BUTTON = By.xpath("//*[@title='Upload']");
  private final By NEW_POST_SEND_BUTTON = By.xpath(getDataTestId("post-modal-send"));
  private final By LIKE_BUTTON = By.xpath(getDataTestId("post-list-item-like"));

  public WebAppPage(RemoteWebDriver driver) {
    super(driver);
  }

  public WebAppPage loginToWebApp(String mail, String pass) {
    setText(WEB_APP_LOGIN_EMAIL, mail);
    clickElement(WEB_APP_LOGIN_CONTINUE);
    setText(WEB_APP_LOGIN_PASSWORD, pass);
    clickElement(WEB_APP_LOGIN_BUTTON);
    return this;
  }

  public WebAppPage openWall() {
    clickElement(WALL);
    return this;
  }

  public WebAppPage sendNewPost(String textMessage, String imagePath) {
    clickElement(NEW_POST);
    setText(NEW_POST_INPUT, textMessage);
    clickElement(NEW_POST_ADD_IMAGE);
    uploadImage(NEW_POST_IMAGE_UPLOAD_INPUT, imagePath);
    clickElement(NEW_POST_IMAGE_UPLOAD_SAVE_BUTTON);
    clickElement(NEW_POST_IMAGE_UPLOAD_BUTTON);
    clickElement(NEW_POST_SEND_BUTTON);
    waitElementAppear(LIKE_BUTTON);
    return this;
  }
}
