package org.Abc.testCases;

import org.Abc.pageObject.accountPage;
import org.Abc.pageObject.homePage;
import org.Abc.pageObject.loginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginTestCase extends baseClass{

    @Test(groups = {"sanity","master"})
    public void tc002_Verify_LoginTestcase(){

        try{
            logger.info("Starting testcase tc002_Verify_LoginTestcase");

            homePage hp = new homePage(driver);
            hp.clickOnMyAccount();
            logger.info("Clicked on My Account link");
            hp.clickOnLogin();
            logger.info("Clicked on login link");

            loginPage lp = new loginPage(driver);
            lp.seteMailAddress(pr.getProperty("username"));
            logger.info("Entered username");
            lp.setPassword(pr.getProperty("password"));
            logger.info("Entered password");
            lp.clickOnLogin();
            logger.info("Clicked on login button");

            accountPage ap = new accountPage(driver);
            boolean Actualvalue = ap.isMyAccount();
            Assert.assertTrue(Actualvalue);

            logger.info("Finished testcase tc002_Verify_LoginTestcase");
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }

}
