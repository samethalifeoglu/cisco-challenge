package com.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import util.Constants;
import util.RandomUtil;

import static util.Constants.IMAGE_PATH;

public class SingleTest extends BaseTest {

    private final String userEmail = configuration.getEmail();
    private final String userPassword = configuration.getPass();

    private BrowserPage browserPage;
    private LoginPage loginPage;
    private WebAppPage webAppPage;


    @BeforeMethod()
    public void pageInstantiations() {
        browserPage = new BrowserPage(driver);
        loginPage = new LoginPage(driver);
        webAppPage = new WebAppPage(driver);
    }

    @Test
    public void challenge(){

        browserPage.openUrl(Constants.URL);

        HomePage homePage = loginPage
                .loginWithValidUser(userEmail, userPassword);

        MyEventsPage myEventsPage = homePage
                .openMyEventsDashboard();

        var webAppLink = myEventsPage
                .openEvent(Constants.EVENT_NAME)
                .openEventWebAppTab()
                .enableWebApp()
                .saveWebApp()
                .copyWebAppLink();

        browserPage.openNewWindow(webAppLink);

        webAppPage
                .loginToWebApp(userEmail, userPassword)
                .openWall()
                .sendNewPost(RandomUtil.createRandomPostMessage(),IMAGE_PATH);


        browserPage.switchToMainWindow();

        myEventsPage
                .openEditEventTab()
                .openEditWall();


    }
}
