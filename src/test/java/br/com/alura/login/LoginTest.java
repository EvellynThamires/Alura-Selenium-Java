package br.com.alura.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @Test
    public void itShouldLoginWithValidCredentials() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assert.assertEquals("fulano", browser.findElement(By.id("logged-user")).getText());
    }

    @Test
    public void itShouldNotLoginWithInvalidCredentials() {
        browser.findElement(By.id("username")).sendKeys("invalid username");
        browser.findElement(By.id("password")).sendKeys("invalid");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
        Assert.assertThrows(NoSuchElementException.class, () ->
                browser.findElement(By.id("logged-user")));
    }

    @Test
    public void itShouldNotAccessRestrictedPagesWithoutBeingLogged() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertFalse(browser.getPageSource().contains("Dados do leilão"));
    }
}