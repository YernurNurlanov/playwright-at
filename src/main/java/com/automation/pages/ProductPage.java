package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class ProductPage extends BasePage {
    public ProductPage(Page page) { super(page); }
    public Locator productName() { return page.locator("h1"); }
    public Locator productPrice() { return page.locator("[data-test='unit-price']"); }
    public void openFirstProduct() {
        page.locator("a[data-test='product-name']").first().click();
    }
    public void openProductByName(String name) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(name)).click();
    }
    public void addToCart() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
    }
}
