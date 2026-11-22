package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class ProductPage extends BasePage {
    public ProductPage(Page page) { super(page); }

    public Locator productName() {
        return page.getByTestId("product-name");
    }

    public Locator productPrice() {
        return page.getByTestId("unit-price");
    }

    public void openFirstProduct() {
        page.getByTestId("product-name").first().click();
    }

    public void addToCart() {
        Locator addToCartBtn = page.getByTestId("add-to-cart");
        addToCartBtn.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        addToCartBtn.click();
    }
}