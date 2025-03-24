package org.Abc.testCases;

import com.github.javafaker.Faker;
import org.Abc.pageObject.homePage;
import org.Abc.pageObject.registerPage;
import org.Abc.utilities.DatabaseOperationsUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CustomerRegistrationTest extends baseClass{
    @Test(groups = {"regression","master"})
    public void testCustomerRegistration() {
        logger.info("Starting testcase testCustomerRegistration");
        homePage hp = new homePage(driver);
        hp.clickOnMyAccount();
        logger.info("Clicked on My Account link");
        hp.clickOnRegister();
        logger.info("Clicked on Register link");
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        registerPage rp = new registerPage(driver);
        rp.setFirstname(firstName);
        rp.setLastname(lastName);
        rp.setEmail(email);
        rp.setPassword(password);
        rp.clickOnAgree();
        rp.clickOnContinue();

        // Verify user is registered successfully
        String ActualMessaage = rp.getMessage();
        Assert.assertEquals(ActualMessaage, "Your Account Has Been Created!");

        // Verify the user exists in the database
        DatabaseOperationsUtil dbUtil = new DatabaseOperationsUtil();
        Assert.assertTrue(dbUtil.isUserRegistered(email), "User is not found in database");

        // Verify customer data in the database
        HashMap<String, String> expectedData = new HashMap<>();
        expectedData.put("firstname", firstName);
        expectedData.put("lastname", lastName);
        expectedData.put("email", email);
        logger.info("Expected data :"+expectedData);
        Assert.assertTrue(dbUtil.verifyCustomerData(email, expectedData), "Customer data mismatch in database");
    }
}
