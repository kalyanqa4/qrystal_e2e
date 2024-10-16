package com.qrystal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import com.qrystal.pages.WebElementUtils;
import com.qrystal.utils.DriverInitialization;
public class AliasNameDeletePage extends DriverInitialization {
    private WebDriver driver;
    public AliasNameDeletePage (WebDriver driver) {     
        this.driver = driver;
    }
   public String  getAlertText() {
	   WebElementUtils.waitForAlert(driver);
       Alert alert = driver.switchTo().alert();  // Switch to the alert 
       String alertText = alert.getText();       // Get alert text 
       return alertText;
   }
    public void acceptAlert() {
 	  WebElementUtils.waitForAlert(driver);
      Alert alert = driver.switchTo().alert(); 
       alert.accept();
    }
    public void cancelAlert() {
 	  WebElementUtils.waitForAlert(driver);
      Alert alert = driver.switchTo().alert(); 
      alert.dismiss();
    }
}
