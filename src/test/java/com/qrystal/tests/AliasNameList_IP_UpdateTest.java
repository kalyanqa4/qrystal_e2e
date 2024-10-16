package com.qrystal.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qrystal.utils.ExcelUtils;

public class AliasNameList_IP_UpdateTest extends BaseTestClass{
	
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
   
     @Test(dataProvider = "Add_IP_DataProvider", groups = {"updateTest", "regression", "High"})
     public void TC_01_UpdateIP_Valid(List<String> rowData)  {
      aliasNameListTablePage.clickEditIcon(rowData.get(0));
   	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
   	  aliasNameAddOrEditPage.setName(rowData.get(1)+" edited");
   	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
   	  aliasNameAddOrEditPage.clickSaveButton();
   	  
   	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
   	  softAssert.assertEquals("Successfully updated", message, "The messages do not match");
	  aliasNameAddOrEditPage.clickCloseIcon();
     }
     
     @Test(dataProvider = "Add_IP_DataProvider", groups = {"updateTest", "regression", "Medium"})
     public void TC_02_UpdateIP_InValidAliasIP(List<String> rowData)  {
      aliasNameListTablePage.clickEditIcon(rowData.get(0));
   	  aliasNameAddOrEditPage.setAddress(" ");
   	  aliasNameAddOrEditPage.setName(rowData.get(1));
   	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
   	  aliasNameAddOrEditPage.clickSaveButton();
   	  
   	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
   	  softAssert.assertEquals("Successfully updated", message, "The messages do not match");
	  aliasNameAddOrEditPage.clickCloseIcon();
     }
     
     @Test(dataProvider = "Add_IP_DataProvider", groups = {"updateTest", "regression", "Medium"})
     public void TC_03_UpdateIP_InValidAliasName(List<String> rowData)  {
      aliasNameListTablePage.clickEditIcon(rowData.get(0));
   	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
   	  aliasNameAddOrEditPage.setName("Name%Test");
   	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
   	  aliasNameAddOrEditPage.clickSaveButton();
   	  
   	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
   	  softAssert.assertEquals("Successfully updated", message, "The messages do not match");
	  aliasNameAddOrEditPage.clickCloseIcon();
     }
     
//     @Test(dataProvider = "Add_IP_DataProvider")
//     public void TC_04_UpdateIP_EmptyAliasType(List<String> rowData)  {
//      aliasNameListTablePage.clickEditIcon(rowData.get(0), 0);
//   	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
//   	  aliasNameAddOrEditPage.setName(rowData.get(2));
//   	  aliasNameAddOrEditPage.selectAliasType(" ");
//   	  aliasNameAddOrEditPage.clickSaveButton();
//   	  
//   	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
//   	  softAssert.assertEquals("Successfully updated", message, "The messages do not match");
//	  aliasNameAddOrEditPage.clickCloseIcon();
//     } 
     
     @Test(dataProvider = "Add_IP_DataProvider", groups = {"updateTest", "regression", "Medium"})
     public void TC_05_UpdateIP_AliasNameExisitng(List<String> rowData)  {
      aliasNameListTablePage.clickEditIcon(rowData.get(0));
   	  aliasNameAddOrEditPage.setAddress(rowData.get(0));
   	  aliasNameAddOrEditPage.setName(rowData.get(1));
   	  aliasNameAddOrEditPage.selectAliasType(rowData.get(2));
   	  aliasNameAddOrEditPage.clickSaveButton();
   	  
   	  String message = driver.findElement(By.xpath("//*[name='messageBanner']")).getText();
   	  softAssert.assertEquals("Name already exists", message, "The messages do not match");
	  aliasNameAddOrEditPage.clickCloseIcon();
     }
     
   

}
