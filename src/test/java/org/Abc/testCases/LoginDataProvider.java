package org.Abc.testCases;

import org.Abc.pageObject.accountPage;
import org.Abc.pageObject.homePage;
import org.Abc.pageObject.loginPage;
import org.Abc.utilities.dataProviderUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDataProvider extends baseClass{

    @Test(dataProvider = "data",dataProviderClass = dataProviderUtilities.class,groups = {"regression","master","dataDriven"})
    public void Tc004_Verify_LoginDatadriven(String email,String Password,String expectedResult){

        try{
            logger.info("Starting testcase Tc004_Verify_LoginDatadriven");

            homePage hp = new homePage(driver);
            hp.clickOnMyAccount();
            logger.info("Clicked on My Account link");
            hp.clickOnLogin();
            logger.info("Clicked on login link");

            loginPage lp = new loginPage(driver);
            lp.seteMailAddress(email);
            logger.info("Entered username");
            lp.setPassword(Password);
            logger.info("Entered password");
            lp.clickOnLogin();
            logger.info("Clicked on login button");

            accountPage ap = new accountPage(driver);
            boolean Actualvalue = ap.isMyAccount();

            if (expectedResult.equalsIgnoreCase("valid")){
                if (Actualvalue){
                    Assert.assertTrue(true);
                    ap.clickOnLogout();
                }else{
                    Assert.assertTrue(false);
                }
            } else if (expectedResult.equalsIgnoreCase("invalid")) {
                if (Actualvalue){
                    Assert.assertTrue(false);
                    ap.clickOnLogout();
                }else {
                    Assert.assertTrue(true);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }



    }
}
