package ge.tbc.itacademy.steps.saucedemo;

import ge.tbc.itacademy.pages.saucedemo.SwagLabsProductsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.clickable;

public class SwagLabsProductsSteps {
    SwagLabsProductsPage productsPage = new SwagLabsProductsPage();

    @Step("Expand Menu")
    public SwagLabsProductsSteps clickOnHamburgerMenu(){
        productsPage.hamburgerMenu
                .scrollTo()
                .shouldBe(clickable)
                .click();
        return this;
    }
    @Step("click on Log Out on expanded hamburger menu")
    public SwagLabsProductsSteps clickOnLogout(){
        productsPage.logoutOption
                .scrollTo()
                .shouldBe(clickable)
                .click();
        return this;
    }


}
