package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyEventsPage extends BasePage {

  private final By EVENT_NAMES = By.xpath("//*[@data-testid='colEvent'] //h3");
  private final By EVENT_WEB_APP_TAB = By.xpath("//*[contains(@class,'gcon gcon-globe')]");
  private final By EDIT_EVENT_TAB = By.xpath(getDataTestId("sidebarItemEditCommunity"));
  private final By ENABLE_WEB_APP_TOGGLE = By.xpath("//*[contains(@class,'panel-body')]/div[2]//*[contains(@class,'bootstrap-switch wrapper')]");
  private final By PRIVACY_SETTINGS_LOGIN = By.xpath("//*[contains(@class,'radio-custom')] //*[contains(text(),'Log In')] /parent::label");
  private final By WEB_APP_SAVE_BUTTON = By.xpath("//*[@class='btn btn-success']");
  private final By WEB_APP_LINK_COPY_BUTTON = By.xpath("//*[contains(text(),'Copy Link')]");
  private final By WEB_APP_LINK = By.xpath("//*[contains(@class,'input-group')]//input");
  private final By WALL = By.id("Wall");
  private final By EDIT_WALL = By.xpath(getDataTestId("buttonEditFeature"));

  public MyEventsPage(RemoteWebDriver driver) {
    super(driver);
  }

  public MyEventsPage openEvent(String eventName) {
    clickListElementWithText(EVENT_NAMES, eventName);
    return this;
  }

  public MyEventsPage openEventWebAppTab() {
    waitForPageLoaded();
    waitElementAppear(EVENT_WEB_APP_TAB);
    clickElement(EVENT_WEB_APP_TAB);
    return this;
  }

  public MyEventsPage openEditEventTab() {
    clickElement(EDIT_EVENT_TAB);
    return this;
  }

  public MyEventsPage openEditWall() {
    clickElement(WALL);
    clickElement(EDIT_WALL);
    return this;
  }

  public MyEventsPage enableWebApp() {
    waitForPageLoaded();
    if (getAttributeValue(ENABLE_WEB_APP_TOGGLE, "class")
        .contains("bootstrap-switch-off")) {
      clickElement(ENABLE_WEB_APP_TOGGLE);
    }
    return this;
  }

  public MyEventsPage selectLogInPrivacySetting() {
    if (!getAttributeValue(PRIVACY_SETTINGS_LOGIN, "class")
        .contains("radio-info")) {
      clickElement(PRIVACY_SETTINGS_LOGIN);
    }
    return this;
  }

  public MyEventsPage saveWebApp() {
    clickElement(WEB_APP_SAVE_BUTTON);
    return this;
  }

  public String copyWebAppLink() {
    clickElement(WEB_APP_LINK_COPY_BUTTON);
    var url = getAttributeValue(WEB_APP_LINK, "value");
    return url;
  }
}



