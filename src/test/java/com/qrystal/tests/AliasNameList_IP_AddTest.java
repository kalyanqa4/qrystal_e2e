package com.qrystal.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qrystal.utils.ExcelUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class AliasNameList_IP_AddTest extends BaseTestClass {
   @BeforeMethod
     public void beforeEachTest() {    // Actions to perform before each test method
         driver.manage().deleteAllCookies(); 
        // loginPage.login();
     } 
    @DataProvider(name = "Add_IP_DataProvider")
    public Iterator<Object[]> provideExcelData(Method method) throws IOException {
        String filePath = "path/to/your/excel.xlsx";  // Update with your file path
        String tagName = method.getDeclaringClass().getName() ;// Tag name to search
        List<List<String>> excelData = ExcelUtils.getDataByTagName(filePath, tagName);
        Object[][] data = new Object[excelData.size()][];         // Convert List<List<String>> to Object[][]
        for (int i = 0; i < excelData.size(); i++) {
            data[i] = new Object[] { excelData.get(i) };  // Each row is passed as an object array
        }
        return java.util.Arrays.asList(data).iterator();
    }
    
    @DataProvider(name = "AliasType_Options")
    public Iterator<Object[]> provideAliasTypeData() throws IOException {
        String filePath = "path/to/your/excel.xlsx";  // Update with your file path
        List<List<String>> excelData = ExcelUtils.getDataByTagName(filePath, "AliasType_Options");
        Object[][] data = new Object[excelData.size()][];         // Convert List<List<String>> to Object[][]
        for (int i = 0; i < excelData.size(); i++) {
            data[i] = new Object[] { excelData.get(i) };  // Each row is passed as an object array
        }
        return java.util.Arrays.asList(data).iterator();
    }
    
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "High"})
      public void TC_01_ReadIP_Valid(List<String> rowData)  {
    	  softAssert.assertEquals("Alias Name List", aliasNameListPage.getHeader(), "The strings do not match");
    	  aliasNameListTablePage.clickAddButton();
    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
    	  softAssert.assertEquals("Successfully created", message, "The messages do not match");
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_02_AddIP_InValidIP(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();
    	  aliasNameAddOrEditPage.setAddress("testingdevice");
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("Invalid ip", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_03_AddIP_InValidName(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();
    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName("testaliasname%");
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("Invalid name", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_04_AddIP_InvalidAliasType(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();
    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("Alias Type not Selected", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Low"})
      public void TC_05_AddIP_BackButton(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();
    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.clickBackButton();
    	  
    	  softAssert.assertFalse(driver.findElements(By.xpath("//*[name='messageBanner']")).size() > 0, "The element should not exist");
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_06_AddIP_ExistingAliasName(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();

    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("Alias name already Exists", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_07_AddIP_EmptyIP(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();

    	  aliasNameAddOrEditPage.setAddress(" ");
    	  aliasNameAddOrEditPage.setName(rowData.get(1));
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("address should not be empty", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      
      @Test(dataProvider = "Add_IP_DataProvider", groups = {"addTest", "regression", "Medium"})
      public void TC_08_AddIP_EmptyName(List<String> rowData) {
    	  aliasNameListTablePage.clickAddButton();

    	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
    	  aliasNameAddOrEditPage.setName("");
    	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
    	  aliasNameAddOrEditPage.clickSaveButton();
    	  
    	  String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
    	  softAssert.assertEquals("Name should not be empty", message, "The messages do not match");  
    	  aliasNameAddOrEditPage.clickCloseIcon();
      }
      	  
      @Test(dataProvider = "AliasType_Options", groups = {"addTest", "regression", "High"})
      public void TC_verifyOptionsinAliasTypeBox(List<String> rowData) {
    	  softAssert.assertEquals(rowData, aliasNameAddOrEditPage.getOptionsinAliasType(), "The arrays do not match");	
     }
      @AfterMethod
      public void assertAll() {
    	  softAssert.assertAll();
      }
         
   }
