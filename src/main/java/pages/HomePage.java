package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Constants;

public class HomePage extends BasePage {

    private final By DASH_TITLE = By.xpath(getDataTestId("titleDashead"));
    private final By EVENT_APP_NAVBAR = By.id("event-app-nav");
    private final By EVENT_APP_MY_EVENTS = By.xpath("//a[@title='My Events']");

    public HomePage(RemoteWebDriver driver) {
        super(driver);
    }

    public MyEventsPage openMyEventsDashboard() {
        if (!getText(DASH_TITLE).equals(Constants.MY_EVENTS_DASHBOARD_TITLE)) {
            clickElement(EVENT_APP_NAVBAR);
            clickElement(EVENT_APP_MY_EVENTS);
        }
        return new MyEventsPage(driver);
    }




}
