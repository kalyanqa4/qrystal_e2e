package com.qrystal.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qrystal.pages.WebElementUtils;
import com.qrystal.utils.DriverInitialization;

public class AliasNameAddEditPage extends DriverInitialization {
	private WebDriver driver;
	By AddressBox_selector = By.id("addressBox");
	By AliasNameBox_selector = By.id("aliasname");
	By AliasTypeSelect_selector = By.id("aliasTypeSelect");
	By BackButton_selector = By.className("BackButton");
	By SaveButton_selector = By.className("SaveButton");
	By closeIcon_selector = By.name("closeIcon");

	public AliasNameAddEditPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setAddress(String text) {
	    WebElementUtils.clearAndType (findElement(AddressBox_selector), text);
	}
	
	public void setName(String text) {
		WebElementUtils.clearAndType(findElement(AliasNameBox_selector), text);
	}
	
	public void clickSaveButton() {
		driver.findElement(SaveButton_selector).click();
	}
	
	public void clickBackButton() {
		driver.findElement(BackButton_selector).click();
	}
	
	public void clickCloseIcon() {
		driver.findElement(closeIcon_selector).click();
	}
	
	public void selectAliasType (String type) {
		WebElement aliasTypeElement = driver.findElement(AliasTypeSelect_selector); 
		 Select aliasType = new Select(aliasTypeElement); 
		aliasType.selectByVisibleText(type);
	}

	public List<String> getOptionsinAliasType() {
		WebElement aliasTypeElement = driver.findElement(AliasTypeSelect_selector);
		Select aliasType = new Select(aliasTypeElement);
		List<WebElement> actualOptions = aliasType.getOptions();
		List<String> actualOptionsText = actualOptions.stream().map(WebElement::getText).toList();
		return actualOptionsText;
	}

	private WebElement findElement(By selector) {
		return driver.findElement(selector);
	}
}
