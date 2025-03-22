package org.Abc.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage {

    public loginPage(WebDriver driver) {
        super(driver);
    }

    //locators

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement eMailAddressEle;
    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordEle;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement btnloginEle;
    @FindBy(xpath = "//div[@class='mb-3']//a[contains(text(),'Forgotten Password')]")
    private WebElement forgottenPasswordele;

    //Actions
    public void seteMailAddress(String eMailAddress) {
        eMailAddressEle.sendKeys(eMailAddress);
    }

    public void setPassword(String password) {
        passwordEle.sendKeys(password);
    }

    public void clickOnLogin() {
        btnloginEle.click();
    }
    public void clickonforgotPasswordLink(){
        forgottenPasswordele.click();
    }
}
