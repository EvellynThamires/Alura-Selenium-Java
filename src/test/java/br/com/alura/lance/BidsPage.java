package br.com.alura.lance;

import br.com.alura.PageObject;

public class BidsPage extends PageObject {
    private static final String URL_BIDS = "http://localhost:8080/leilao/2";

    public BidsPage() {
        super(null);
        this.browser.navigate().to(URL_BIDS);
    }

    public boolean isBidsPage() {
        return browser.getCurrentUrl().contains(URL_BIDS);
    }

    public boolean isAuctionTitleVisible() {
        return browser.getPageSource().contains("Dados do leilão");
    }
}
