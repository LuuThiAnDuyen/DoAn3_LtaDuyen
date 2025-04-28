package com.anduyen.testcases;

import com.anduyen.helpers.ExcelHelpers;
import com.anduyen.helpers.PropertiesHelpers;
import org.testng.annotations.Test;
import com.anduyen.utils.LogUtils;

public class TestHandle {
    @Test
    public void testReadPropertiesFile() {
        PropertiesHelpers.loadAllFiles();
        System.out.println(PropertiesHelpers.getValue("BROWSER"));
    }
    @Test
    public void testReadExcelData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/resources/testData/Book1.xlsx","Login");
        System.out.println(excelHelpers.getCellData("EMAIL",1));
        System.out.println(excelHelpers.getCellData("PASSWORD",1));
    }
    @Test
    public void testLog4j2() {
        LogUtils.info("Cucumber TestNG");
    }

}
