package br.com.alura.leiloes;

import br.com.alura.login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuctionTest {
    private AuctionPage auctionPage;
    private RegisterAuctionPage registerAuctionPage;

    @BeforeEach
    public void beforeEach() {
        LoginPage loginPage = new LoginPage();
        this.auctionPage = loginPage.submitLogin("fulano", "pass");
        this.registerAuctionPage = auctionPage.loadForm();
    }

    @AfterEach
    public void afterEach() {
        this.auctionPage.closeBrowser();
        this.registerAuctionPage.closeBrowser();
    }

    @Test
    public void itShouldRegisterNewAuction() {
        String actualDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "Leilao do dia " + actualDate;
        String auctionValue = "500.00";

        this.auctionPage = registerAuctionPage.registerAuction(auctionName, auctionValue, actualDate);
        Assert.assertTrue(auctionPage.isAuctionRegistered(auctionName, auctionValue, actualDate));
    }

    @Test
    public void itShouldValidateAuctionRegister() {
        this.auctionPage = registerAuctionPage.registerAuction("", "", "");

        Assert.assertFalse(registerAuctionPage.isRegisterAuctionPage());
        Assert.assertTrue(auctionPage.isAuctionPage());
        Assert.assertTrue(registerAuctionPage.isValidationMessageVisible());
    }
}
