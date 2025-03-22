package org.Abc.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class dataProviderUtilities {


    @DataProvider(name = "data")
    public Object[][] testdatawithstatus(Method mathodname) {
        Object[][] dataset = new Object[0][];

        if (mathodname.getName().equals("Tc004_Verify_LoginDatadriven")) {
            String path = "testData/OpenCartTestData.xlsx";
            String Sheetname = "Sheet1";

            excelUtilities eu = new excelUtilities();
            try {
                int rowCount = eu.getRowCount(path,Sheetname);
                int columnCount = eu.getCellCount(path,Sheetname,0);
                dataset = new Object[rowCount][columnCount];

                for(int r = 1;r<=rowCount;r++){
                    for(int c=0;c<columnCount;c++){
                        dataset[r-1][c] = eu.getCellData(path,Sheetname,r,c);
                    }
                }

            } catch (Exception e) {
               e.printStackTrace();
            }

        } else if (mathodname.getName().equals("Tc005_Verify_regiserDatadriven")) {
            
        }

        return dataset;
    }
}
