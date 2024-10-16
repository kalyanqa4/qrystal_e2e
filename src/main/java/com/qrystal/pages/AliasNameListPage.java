package com.qrystal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qrystal.pages.WebElementUtils;
import com.qrystal.utils.DriverInitialization;
public class AliasNameListPage extends DriverInitialization {
    private WebDriver driver;
    By SearchBox_selector= By.className("searchInput");
    By SearchIcon_selector= By.className("searchButton");
    public AliasNameListPage(WebDriver driver) {     // Constructor that accepts the WebDriver from DriverInitializationCls
        this.driver = driver;
    }
    public void setSearchKey(String key) { 	// Method to input text into a field
      WebElement searchBox = driver.findElement(SearchBox_selector);
        WebElementUtils.clearAndType (searchBox, key);
    }
     public void clickSearchButton() {		//Method to click the search button
      WebElement searchIcon = driver.findElement(SearchIcon_selector);
       WebElementUtils.clickButton(searchIcon);
    }
    public String getHeader() {
	return driver.findElement(By.id("pageTitle")).getText();
    }
}

