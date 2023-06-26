package br.com.alura.login;

import br.com.alura.lance.BidsPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        loginPage.submitLogin("fulano", "pass");

        String loggedUser = loginPage.getLoggedUser();
        Assert.assertEquals("fulano", loggedUser);
        Assert.assertFalse(loginPage.isLoginPage());
    }

    @Test
    public void itShouldNotLoginWithInvalidCredentials() {
        loginPage.submitLogin("invalid", "1233");

        Assert.assertNull(loginPage.getLoggedUser());
        Assert.assertTrue(loginPage.isLoginPage());
        Assert.assertTrue(loginPage.isInvalidLoginMessageVisible());
    }

    @Test
    public void itShouldNotAccessRestrictedPagesWithoutBeingLogged() {
        BidsPage bidsPage = new BidsPage();

        Assert.assertFalse(bidsPage.isBidsPage());
        Assert.assertFalse(bidsPage.isAuctionTitleVisible());
    }
}
