package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.Constants;

import java.util.ArrayList;

public class BrowserPage extends BasePage {

  public BrowserPage (WebDriver driver) {
    super(driver);
  }

  /**
   * Open url
   * @param url
   */
  public void openUrl(String url) {
    goToUrl(url);
  }

  /**
   * Open url in new window
   * @param url
   */
  public void openNewWindow(String url) {
    try {
      ((JavascriptExecutor) driver)
              .executeScript("window.open(arguments[0])", url);
      ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs.get(1));
      logger.info(Constants.OPEN_URL + url);
    } catch (Exception e) {
      Assert.fail(Constants.COULD_NOT_OPEN_URL + url + " Error : " + e);
    }
  }

  /**
   * Switch to main window
   */
  public void switchToMainWindow() {
    try {
      driver.close();
      ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs.get(0));
      logger.info("Switch to main window");
    } catch (Exception e) {
      Assert.fail("Could not switch to main window ||" + " Error : " + e);
    }
  }

}
