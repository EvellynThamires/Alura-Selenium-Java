package br.com.alura.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    //Constructor
    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    public void closeBrowser() {
        this.browser.quit();
    }

    public void login(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void submitLoginButton() {
        browser.findElement(By.id("login-form")).submit();
    }

    public boolean itIsLoginPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public Object getLoggedUsername() {
        try {
            return browser.findElement(By.id("logged-user")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean itIsNotLoginPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    public void navigateToBidPage() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean containsText(String text) {
        return this.browser.getPageSource().contains(text);
    }
}
