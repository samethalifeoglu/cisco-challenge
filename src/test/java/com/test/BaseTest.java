package com.test;

import com.browserstack.DriverAbstraction;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import util.Configuration;

public class BaseTest {

    WebDriver driver;
    Configuration configuration = new Configuration();

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"config", "environment"})
    public void setUp(String config_file, String environment) throws Exception {

        //Create a remote driver. All test classes use this.
        driver = DriverAbstraction.getDriver(config_file, environment);

        //Maximize Window
        driver.manage().window().maximize();

    }

    @AfterMethod(alwaysRun=true)
    public void tearDown(){
        driver.quit();

    }
}
