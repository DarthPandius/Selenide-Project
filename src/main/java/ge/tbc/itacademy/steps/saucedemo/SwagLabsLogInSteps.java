package ge.tbc.itacademy.steps.saucedemo;

import com.codeborne.selenide.Condition;
import ge.tbc.itacademy.pages.saucedemo.SwagLabsLoginPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class SwagLabsLogInSteps {
    SwagLabsLoginPage loginPage = new SwagLabsLoginPage();

    @Step("Entering user name {0} that was obtained from the database")
    public SwagLabsLogInSteps enterUserName(String userName) {
        loginPage.userName
                .shouldBe(visible, clickable)
                .setValue(userName);
        return this;
    }
    @Step("Entering password {0} that was obtained from the database")
    public SwagLabsLogInSteps enterPassword(String password) {
        loginPage.password
                .shouldBe(visible, clickable)
                .setValue(password);
        return this;
    }
    @Step("Clicking on Log In button")
    public SwagLabsLogInSteps pressLoginButton() {
        loginPage.logInButton
                .shouldBe(visible, clickable)
                .click();
        return this;
    }

    @Step("Dismissing an error message")
    public SwagLabsLogInSteps pressXOnError() {
        loginPage.errorMassageX
                .shouldBe(clickable)
                .click();
        return this;
    }
    @Step("Obtaining an error message when the log in goes wrong")
    public String getErrorMessage() {
        return loginPage.errorMessage
                .shouldBe(visible).getText();
    }
    @Step("Checking that is the log in goes wrong all red Xs are displayed")
    public SwagLabsLogInSteps redXButtonsAreVisible() {
        loginPage.redXButtons.shouldHave(size(3));
        return this;
    }
    @Step("Checking that if the log in goes wrong the correct error message is displayed")
    public SwagLabsLogInSteps errorMessageIsDisplayed() {
        loginPage.errorMassageX.shouldBe(visible);
        return this;
    }
    @Step("After Logging out username field is empty on log in page")
    public SwagLabsLogInSteps userNameIsEmpty() {
        loginPage.userName.shouldBe(Condition.empty);
        return this;
    }

    @Step("After Logging out password field is empty on log in page")
    public SwagLabsLogInSteps userPasswordIsEmpty() {
        loginPage.password.shouldBe(empty);
        return this;
    }
}
