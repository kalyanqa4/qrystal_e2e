package com.qrystal.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qrystal.utils.ExcelUtils;

public class AliasNameList_IP_ReadTest extends BaseTestClass{

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
    
    @Test(dataProvider = "Add_IP_DataProvider", groups = {"readTest", "regression", "High"})
    public void TC_01_ReadIP_Valid(List<String> rowData)  {
      aliasNameListPage.setSearchKey(rowData.get(0));
      aliasNameListPage.clickSearchButton();
      
      WebElement table=driver.findElement(By.id("tableId"));
      List<WebElement> rows = table.findElements(By.tagName("tr"));
      
      softAssert.assertTrue(rows.size() > 0, "Table does not contain any rows");
    }
    
    @Test(dataProvider = "Add_IP_DataProvider", groups = {"readTest", "regression", "Medium"})
    public void TC_02_ReadIP_InValid(List<String> rowData)  {
      aliasNameListPage.setSearchKey("Invalid%test");
      aliasNameListPage.clickSearchButton();
      
      String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
	  softAssert.assertEquals("Invalid search criteria", message, "The messages do not match");  
    }
    
    @Test(dataProvider = "Add_IP_DataProvider", groups = {"readTest", "regression", "Low"})
    public void TC_03_ReadIP_Empty(List<String> rowData)  {
      aliasNameListPage.setSearchKey("");
      aliasNameListPage.clickSearchButton();
      
      WebElement table=driver.findElement(By.id("tableId"));
      List<WebElement> rows = table.findElements(By.tagName("tr"));
      
      softAssert.assertTrue(rows.size() > 0, "Table does not contain any rows"); 
    }
    
    @Test(dataProvider = "Add_IP_DataProvider", groups = {"readTest", "regression", "Low"})
    public void TC_04_ReadIP_AliasType(List<String> rowData)  {
      aliasNameListPage.setSearchKey(rowData.get(1));
      aliasNameListPage.clickSearchButton();
      
      String message = driver.findElement(By.xpath("//*[name='errorBanner']")).getText();
	  softAssert.assertEquals("search criteria couldn't filter on AliasType", message, "The messages do not match");  
    }
    
}

