package com.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowserPage;
import pages.HomePage;
import pages.LoginPage;
import util.Constants;

public class SingleTest extends BaseTest {

    private final String trendyolEmail = configuration.getEmail();
    private final String trendyolPassword = configuration.getPass();

    private HomePage homePage;
    private BrowserPage browserPage;

    @BeforeMethod()
    public void pageInstantiations() {
        homePage = new HomePage(driver);
        browserPage = new BrowserPage(driver);
    }

    @Test
    public void challenge(){

        browserPage.openUrl(Constants.URL);

        LoginPage loginPage = homePage.selectGender();

        loginPage
                .loginValidUser(trendyolEmail,trendyolPassword)
                .checkLoggedIn();
    }
}
