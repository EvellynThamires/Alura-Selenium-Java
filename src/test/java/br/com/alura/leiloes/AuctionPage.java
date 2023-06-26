package br.com.alura.leiloes;

import br.com.alura.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionPage extends PageObject  {

    private static final String URL_AUCTION = "http://localhost:8080/leiloes";
    private static final String URL_REGISTER_AUCTION = "http://localhost:8080/leiloes/new";

    public AuctionPage(WebDriver browser) {
        super(browser);
    }

    public RegisterAuctionPage loadForm() {
        this.browser.navigate().to(URL_REGISTER_AUCTION);
        return new RegisterAuctionPage(browser); //Stay in the same browser
    }

    public boolean isAuctionRegistered(String auctionName, String auctionValue, String actualDate) {
        WebElement lastLineOnAuctionTable = this.browser.findElement(By.cssSelector("#auction-table tbody tr:last-child"));
        WebElement auctionNameOnAuctionTable = lastLineOnAuctionTable.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement auctionActualDateOnAuctionTable = lastLineOnAuctionTable.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement auctionValueOnAuctionTable = lastLineOnAuctionTable.findElement(By.cssSelector("td:nth-child(3)"));

        return auctionNameOnAuctionTable.getText().equals(auctionName) &&
                auctionActualDateOnAuctionTable.getText().equals(actualDate) &&
                auctionValueOnAuctionTable.getText().equals(auctionValue);

    }

    public boolean isAuctionPage() {
        return this.browser.getCurrentUrl().equals(URL_AUCTION);
    }
}
