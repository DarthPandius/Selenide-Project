package ge.tbc.itacademy.pages.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwagLabsLoginPage {
    public SelenideElement loginForm = $("#login_button_container form"),
            logInButton = loginForm.find(" input#login-button"),
            userName = loginForm.find("input#user-name"),
            password = loginForm.find("input#password"),
            errorMassageContainer = loginForm.find(".error-message-container"),
            errorMassageX = errorMassageContainer.find("button.error-button"),
            errorMessage = errorMassageContainer.find("h3");
    public ElementsCollection redXButtons = $$("svg");
}
