package com.test;

import org.apache.commons.lang3.RandomStringUtils;
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
    public void challenge() throws InterruptedException {

        browserPage.openUrl(Constants.URL);

        HomePage homePage = loginPage
                .loginWithValidUser(userEmail, userPassword);

        MyEventsPage myEventsPage = homePage
                .openMyEventsDashboard()
                .changePrivacySetting(Constants.EVENT_NAME);

        var webAppLink = myEventsPage
                .openEvent()
                .openEventWebAppTab()
                .enableWebApp()
                .saveWebApp()
                .copyWebAppLink();

        browserPage.openNewWindow(webAppLink);

        var postText = RandomUtil.createRandomPostText();
        webAppPage
                .loginToWebApp(userEmail, userPassword)
                .openWall()
                .sendNewPost(postText,IMAGE_PATH);

        browserPage.switchToMainWindow();

        myEventsPage
                .openEditEventTab()
                .openEditWall()
                .checkPostIsListed(postText);

    }
}
