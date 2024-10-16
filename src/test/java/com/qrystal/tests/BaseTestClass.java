package com.qrystal.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.qrystal.pages.APIUtils;
import com.qrystal.pages.AliasNameAddEditPage;
import com.qrystal.pages.AliasNameDeletePage;
import com.qrystal.pages.AliasNameListPage;
import com.qrystal.pages.AliasNameListTablePage;
import com.qrystal.pages.LoginPage;
import com.qrystal.utils.DriverInitialization;

public class BaseTestClass extends DriverInitialization {
    protected AliasNameAddEditPage aliasNameAddOrEditPage;
    protected AliasNameListPage aliasNameListPage;
    protected AliasNameDeletePage  aliasNameDeletePage;
    protected AliasNameListTablePage aliasNameListTablePage;
    protected LoginPage loginPage;
    public SoftAssert softAssert ;
    public APIUtils apiutils;
 
    @BeforeMethod
    public void setup(){              // Initialize Page Object classes
        aliasNameAddOrEditPage = new AliasNameAddEditPage(driver);
        aliasNameListPage = new AliasNameListPage(driver);
        aliasNameDeletePage = new AliasNameDeletePage  (driver);
        aliasNameListTablePage = new AliasNameListTablePage(driver);
        softAssert = new SoftAssert();
        loginPage =  new LoginPage(driver);
        apiutils = new APIUtils();
    }
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
