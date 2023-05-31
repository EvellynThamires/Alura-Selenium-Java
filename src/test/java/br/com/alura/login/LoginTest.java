package br.com.alura.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.closeBrowser();
    }

    @Test
    public void itShouldLoginWithValidCredentials() {
        loginPage.login("fulano", "pass");
        loginPage.submitLoginButton();

        Assert.assertFalse(loginPage.itIsLoginPage());
        Assert.assertEquals("fulano", loginPage.getLoggedUsername());
    }

    @Test
    public void itShouldNotLoginWithInvalidCredentials() {
        loginPage.login("invalid", "invalid");
        loginPage.submitLoginButton();

        Assert.assertTrue(loginPage.itIsNotLoginPage());
        Assert.assertTrue(loginPage.containsText("Usuário e senha inválidos"));
        Assert.assertNull(loginPage.getLoggedUsername());
    }

    @Test
    public void itShouldNotAccessRestrictedPagesWithoutBeingLogged() {
        loginPage.navigateToBidPage();

        Assert.assertTrue(loginPage.itIsLoginPage());
        Assert.assertFalse(loginPage.containsText("Dados do leilão"));
    }
}
