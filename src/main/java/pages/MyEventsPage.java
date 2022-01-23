package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyEventsPage extends BasePage {

  private final By EVENT_NAME = By.xpath("//*[@data-testid='colEvent'] //*[text()='Test Automation Assessment Event']");
  private final By EVENT_WEB_APP_TAB = By.xpath("//*[contains(@class,'gcon gcon-globe')]");
  private final By EDIT_EVENT_TAB = By.xpath(getDataTestId("sidebarItemEditCommunity"));
  private final By ENABLE_WEB_APP_TOGGLE = By.xpath("//*[contains(@class,'panel-body')]/div[2]//*[contains(@class,'bootstrap-switch wrapper')]");
  private final By WEB_APP_SAVE_BUTTON = By.xpath("//*[@class='btn btn-success']");
  private final By WEB_APP_LINK_COPY_BUTTON = By.xpath("//*[contains(text(),'Copy Link')]");
  private final By WEB_APP_LINK = By.xpath("//*[contains(@class,'input-group')]//input");
  private final By WALL = By.id("Wall");
  private final By EDIT_WALL = By.xpath(getDataTestId("buttonEditFeature"));
  private final By POST_NAME = By.xpath("//*[contains(@class,'post-caption')]/span");
  private final By BASICS = By.xpath("//span[contains(@class,'glyphicon-info-sign')]");
  private final By PRIVACY_SETTINGS = By.xpath("//*[@data-testid='basics-display']/div/ul //*[text()='Privacy Settings']");
  private final By PUBLIC_RADIO_BUTTON = By.xpath(getDataTestId("radioEventPrivacyPublic"));
  private final By PRIVACY_SAVE_BUTTON = By.xpath("//*[contains(@class,'btn-success')]");
  private final By HOME = By.xpath("//*[contains(@class,'fa-home')]");
  private final By LOADER_ICON = By.xpath("//*[contains(@class,'Loader_loading-icon-element')]");

  public MyEventsPage(RemoteWebDriver driver) {
    super(driver);
  }

  public MyEventsPage changePrivacySetting(String eventName) throws InterruptedException {
    clickElement(EVENT_NAME);
    waitForPageLoaded();
    clickElement(EDIT_EVENT_TAB);
    clickElement(BASICS);
    clickElement(PRIVACY_SETTINGS);
    selectPublicRadio();
    clickElement(PRIVACY_SAVE_BUTTON);
    clickElement(HOME);
    waitElementDisappear(LOADER_ICON);
    return this;
  }

  public MyEventsPage selectPublicRadio() {
    if (!getAttributeValue(PUBLIC_RADIO_BUTTON, "class")
            .contains(" selected")) {
      clickElement(PUBLIC_RADIO_BUTTON);
    }
    return this;
  }

  public MyEventsPage openEvent() {
    clickElement(EVENT_NAME);
    return this;
  }

  public MyEventsPage openEventWebAppTab() {
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
    if (getAttributeValue(ENABLE_WEB_APP_TOGGLE, "class")
        .contains("bootstrap-switch-off")) {
      clickElement(ENABLE_WEB_APP_TOGGLE);
    }
    return this;
  }

  public MyEventsPage saveWebApp() {
    clickElement(WEB_APP_SAVE_BUTTON);
    return this;
  }

  public String copyWebAppLink() {
    clickElement(WEB_APP_LINK_COPY_BUTTON);
    return getAttributeValue(WEB_APP_LINK, "value");
  }

  public void checkPostIsListed(String postText) {
    doesElementTextEqualsText(POST_NAME, postText);
  }
}



