package pages;

import org.openqa.selenium.WebDriver;

public class BrowserPage extends BasePage {

  public BrowserPage (WebDriver driver) {
    super(driver);
  }

  public void openUrl(String url) {
    goToUrl(url);
    waitForPageLoaded();
  }
}
