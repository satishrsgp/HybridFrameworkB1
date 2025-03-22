package org.Abc.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class accountPage extends basePage {


    public accountPage(WebDriver driver) {
        super(driver);
    }

    //locators

    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    private WebElement myAccount;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    private WebElement logoutele;


    //actions
    public boolean isMyAccount() {
        try {
            return myAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    public void clickOnLogout(){
         Actions actions = new Actions(driver);
        actions.moveToElement(logoutele).click().perform();
    }
}
