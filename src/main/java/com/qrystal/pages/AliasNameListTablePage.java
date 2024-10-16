package com.qrystal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qrystal.pages.WebElementUtils;
import com.qrystal.utils.DriverInitialization;

public class AliasNameListTablePage extends DriverInitialization {
	  private WebDriver driver;
	
	  By DeleteAll_selector= By.xpath("//button[text()=’Delete All’]");
	  By AddButton_selector= By.xpath("//button[text()=’Add]");
	  By ExportButton_selector= By.xpath("//button[text()=’Export’]");
	  public AliasNameListTablePage(WebDriver driver) {     
	        this.driver = driver;
	  }
	  public By getRowByName(String name) { 
	       return By.xpath("//table[@id='AliasListTable']//td[text()='" + name + "']"); 
	  }
	  public String getColumnValue(String name,int columnIndex) {
	     WebElement rowElement = driver.findElement(getRowByName(name));
	     return rowElement.findElement(By.xpath("./following-sibling::td["+ columnIndex + "]")).getText(); 
	  }
	  public void clickDeleteIcon(String name) {
	      WebElement rowElement = driver.findElement(getRowByName(name));
	      rowElement.findElement(By.xpath("./preceeding-sibling::td[2]//i[@class='delete-icon']")).click(); 
	  }
	  public void clickEditIcon(String name) {
	      WebElement rowElement = driver.findElement(getRowByName(name));
	      rowElement.findElement(By.xpath("./preceeding-sibling::td[2]//i[@class=edit-icon']")).click(); 
	  }
	  public void clickDeleteAllButton() {
	       WebElementUtils.clickButton(findElement(DeleteAll_selector));
	  }
	  public void clickAddButton() {
	       WebElementUtils.clickButton(findElement(AddButton_selector));
	  }
	  public void clickExportButton() {
	       WebElementUtils.clickButton(findElement(ExportButton_selector));
	  }
	  private WebElement findElement(By selector) {		
	        return driver.findElement(selector);
	  }
}
