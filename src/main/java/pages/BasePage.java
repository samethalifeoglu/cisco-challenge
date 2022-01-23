package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.Constants;

import java.io.File;
import java.time.Duration;

import static util.Constants.*;

public class BasePage {

    public static final Logger logger = Logger.getLogger(Logger.class.getName());

    public RemoteWebDriver driver;
    public WebDriverWait wait;

    public BasePage (RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static String getDataTestId(String dataTestIdValue){
        return "//*[@data-testid='"+dataTestIdValue+"']";
    }

    /**
     * send the path to upload image from the local
     * @param by target element locator
     * @param image_path local image path
     */
    public void uploadImage(By by, String image_path)
    {
        try {
            driver.setFileDetector(new LocalFileDetector());
            File file = new File(image_path);
            String absolutePath = file.getAbsolutePath();
            waitElementAppear(by).sendKeys(absolutePath);
        }catch (Exception e){
            Assert.fail(COULD_NOT_UPLOAD_IMAGE + by + " Error: " + e);
        }
    }

    /**
     * wait until element is visible in page
     * @param by target element locator
     */
    protected WebElement getElement(By by){
        try{
            return wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
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

    /**
     * wait until the page load
     */
    public void waitForPageLoaded(){

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
     * @param by target element locator
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

    /**
     * Wait until element appear
     * @param by target element locator
     */
    public WebElement waitElementAppear(By by) {
        logger.info(Constants.WAIT_ELEMENT + by);
        try{
            return wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            Assert.fail(COULD_NOT_FIND_ELEMENT + by + " Error: " + e);
            return null;
        }
    }

    /**
     * Wait until element is disappeared
     * @param by target element locator
     */
    public void waitElementDisappear(By by) {
        logger.info(Constants.WAIT_ELEMENT + by);
        try{
             wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
        }catch (Exception e){
            Assert.fail(COULD_NOT_DISAPPEAR_ELEMENT + by + " Error: " + e);
        }
    }

    /**
     * Compare element text with expected one
     * @param by target element locator
     * @param expectedText given text of element
     */
    public void doesElementTextEqualsText(By by, String expectedText) {
        WebElement element = getElement(by);
        if (!element.getText().equals(expectedText)) {
            Assert.fail(element + " text does not equal to " + expectedText
                    + " || Actual value : " + element.getText()
                    + " || Expected value : " + expectedText);
        }
    }
}
