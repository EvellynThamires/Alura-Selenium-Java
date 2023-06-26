package br.com.alura.login;

import br.com.alura.PageObject;
import br.com.alura.leiloes.AuctionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    private void fillInUsernamePassword(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public AuctionPage submitLogin(String username, String password) {
        this.fillInUsernamePassword(username, password);
        browser.findElement(By.id("login-form")).submit();
        return new AuctionPage(browser);
    }

    public String getLoggedUser() {
        try {
            return browser.findElement(By.id("logged-user")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public boolean isInvalidLoginMessageVisible() {
        return browser.getPageSource().contains("Usuário e senha inválidos");
    }
}
