package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.Constants;

import java.time.Duration;
import java.util.ArrayList;

import static util.Constants.*;

public class BasePage {

    public static final Logger logger = Logger.getLogger(Logger.class.getName());

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public static String getDataTestId(String dataTestIdValue){
        return "//*[@data-testid=\""+dataTestIdValue+"\"]";
    }

    protected WebElement getElement(By by){
        try{
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            Assert.fail(COULD_NOT_FIND_ELEMENT + by + " Error: " + e);
            return null;
        }
    }

    /**
     * Open given url
     * @param url
     */
    public void goToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail(COULD_NOT_OPEN_URL + url + "Error: " + e);
        }
    }

    public void waitForPageLoaded(){

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(jsLoad);
        }catch (Throwable e){
            Assert.fail(PAGE_TIME_OUT);
        }
    }

    /**
     * Clicks element
     * @param by target element locator
     */
    public void clickElement(By by){
        try {
            getElement(by).click();

            logger.info(CLICK_ELEMENT + by);
        }catch (Exception e){
            Assert.fail(COULD_NOT_CLICK_ELEMENT + by + " Error : " + e);
        }
    }

    /**
     * Set a text to element
     * @param by   target element locator
     * @param data given text
     */
    public void setText(By by, String data){
        try {
            getElement(by).sendKeys(data);
        }catch (Exception e){
            Assert.fail(COULD_NOT_SEND_KEY_TO_ELEMENT + by + " Error : " + e);
        }
    }

    /**
     * Get text from element
     * @param by target element locator
     * @return text of element
     */
    public String getText(By by) {
        try {
            logger.info(Constants.GET_TEXT_OF_ELEMENT + by);
            String text = getElement(by).getText();
            logger.info(Constants.TEXT_OF_ELEMENT + text);
            return text;
        } catch (Exception e) {
            Assert.fail(Constants.COULD_NOT_GET_TEXT_OF_ELEMENT + by);
            return null;
        }
    }

    /**
     * Get a value of an element attribute
     * @param by        target element locator
     * @param attribute given attribute of element
     * @return given attribute value of element
     */
    public String getAttributeValue(By by, String attribute) {
        try {
            logger.info(Constants.GET_TEXT_OF_ELEMENT + by);
            String text = getElement(by).getAttribute(attribute);
            logger.info(Constants.TEXT_OF_ELEMENT + text);
            return text;
        } catch (Exception e) {
            Assert.fail(Constants.COULD_NOT_GET_ATTRIBUTE_OF_ELEMENT + by + "Error : " + e);
            return null;
        }
    }

    public void doesElementTextEqualsText(By by, String expectedText) {
        WebElement element = getElement(by);
        if (!element.getText().equals(expectedText)) {
            Assert.fail(element + " text does not equal to " + expectedText
                    + " || Actual value : " + element.getText()
                    + " || Expected value : " + expectedText);
        }
    }

    public void doesElementTextContainText(By by, String expectedText) {
        WebElement element = getElement(by);
        if (!element.getText().contains(expectedText)) {
            Assert.fail(element + " text does not contain to " + expectedText
                    + " || Actual value : " + element.getText()
                    + " || Expected value : " + expectedText);
        }
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
