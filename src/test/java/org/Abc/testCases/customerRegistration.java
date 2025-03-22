package org.Abc.testCases;

import com.github.javafaker.Faker;
import org.Abc.pageObject.homePage;
import org.Abc.pageObject.registerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class customerRegistration extends baseClass {


    // you need to write your test
    @Test(groups = {"regression","master"})
    public void tc01RegiserCustomer() {
        try{
            logger.info("Starting testcase customer registration");
            Faker faker = new Faker();
            homePage hp = new homePage(driver);
            hp.clickOnMyAccount();
            logger.info("Clicked on My Account link");
            hp.clickOnRegister();
            logger.info("Clicked on Register link");

            registerPage rp = new registerPage(driver);
            rp.setFirstname(faker.name().firstName());
            logger.info("Entered first name");
            rp.setLastname(faker.name().lastName());
            logger.info("Entered last name");
            rp.setEmail(faker.internet().emailAddress());
            logger.info("Entered email address");
            rp.setPassword(faker.internet().password());
            logger.info("Entered Password");
            rp.clickOnAgree();
            logger.info("Clicked on agree");
            rp.clickOnContinue();
            logger.info("clicked on continue");

            //Validate the success message
            String ActualMessaage = rp.getMessage();
            Assert.assertEquals(ActualMessaage, "Your Account Has Been Created!");
            logger.error("Validation failed");
            logger.debug("Validation debug");
            logger.warn("Validation warn");

            logger.info("Finished testcase customer registration");

        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }



    }


}
