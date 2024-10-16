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

public class AliasNameList_IP_DeleteTest extends BaseTestClass{

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
   
   @Test(dataProvider = "Add_IP_DataProvider", groups = {"deleteTest", "regression", "High"})
   public void TC_01_DeleteIP_Success(List<String> rowData)  {
	      aliasNameListTablePage.clickDeleteIcon(rowData.get(0)); 
	      String actualdeleteText = aliasNameDeletePage.getAlertText();
	      String expectedDeleteText = "Do you really want to delete alias '"+ rowData.get(0) + "'?";
	      
	      softAssert.assertEquals(expectedDeleteText, actualdeleteText, "The messages do not match");  
	      aliasNameDeletePage.acceptAlert();
	      
	      String message = driver.findElement(By.name("successBanner")).getText();
	      softAssert.assertEquals("Successfully deleted", message, "The messages do not match");
   }
  
   @Test(dataProvider = "Add_IP_DataProvider", groups = {"deleteTest", "regression", "Medium"})
   public void TC_02_DeleteIP_CancelButton(List<String> rowData)  {
	   	  apiutils.createAliasNameviaAPI();
	      aliasNameListTablePage.clickDeleteIcon(rowData.get(0));
	      aliasNameDeletePage.cancelAlert();
   }
   
   @Test(dataProvider = "Add_IP_DataProvider", groups = {"deleteTest", "regression", "High"})
   public void TC_03_DeleteAll_IP_AllRows(List<String> rowData)  {
	      aliasNameListTablePage.clickDeleteAllButton();
	      
	      String actualdeleteText = aliasNameDeletePage.getAlertText();
	      String expectedDeleteText = "Do you really want to delete all rows ?";
	      softAssert.assertEquals(expectedDeleteText, actualdeleteText, "The messages do not match");  
	      
	      aliasNameDeletePage.acceptAlert();
	      String message = driver.findElement(By.name("successBanner")).getText();
	      softAssert.assertEquals("Successfully deleted", message, "The messages do not match");
   }
   
   @Test(dataProvider = "Add_IP_DataProvider", groups = {"deleteTest", "regression", "Medium"})
   public void TC_05_DeleteAll_IP_CancelButton(List<String> rowData)  {
	   	  apiutils.createAliasNameviaAPI();
	   	  aliasNameListTablePage.clickDeleteAllButton();
	      aliasNameDeletePage.cancelAlert();
   }
}
