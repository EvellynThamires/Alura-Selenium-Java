package br.com.alura.leiloes;

import br.com.alura.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAuctionPage extends PageObject {
    private static final String URL_REGISTER_AUCTION = "http://localhost:8080/leiloes/new";

    public RegisterAuctionPage(WebDriver browser) {
        super(browser);
    }

    public AuctionPage registerAuction(String name, String value, String date) {
        this.browser.findElement(By.id("nome")).sendKeys(name);
        this.browser.findElement(By.id("valorInicial")).sendKeys(value);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(date);
        this.browser.findElement(By.id("button-submit")).click();

        return new AuctionPage(browser);
    }

    public boolean isValidationMessageVisible() {
        String pageSource = this.browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }

    public boolean isRegisterAuctionPage() {
        return this.browser.getCurrentUrl().equals(URL_REGISTER_AUCTION);
    }
}
