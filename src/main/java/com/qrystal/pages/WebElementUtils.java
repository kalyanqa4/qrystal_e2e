package com.qrystal.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {
  public static void clearAndType(WebElement element, String text) {     // Method to clear & type in a WebElement
        		element.clear(); 		// Clear the input field
        		element.sendKeys(text); 	// Send keys to the input field
  }
  public static void clickButton(WebElement element) {
        		element.click();		 // Click the WebElement
  }
  
  public static void waitForAlert(WebDriver driver) {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration for timeout
      wait.until(ExpectedConditions.alertIsPresent());  
  }
}

