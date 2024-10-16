package com.qrystal.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverInitialization {
	
	public WebDriver driver;

	public  DriverInitialization() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe"); // Update with your path

        // Create a new instance of the ChromeDriver
         driver = new ChromeDriver();
        // Navigate to a webpage
        driver.get("");
	}
}
