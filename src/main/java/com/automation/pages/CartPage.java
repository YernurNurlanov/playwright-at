package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class CartPage extends BasePage {
    public CartPage(Page page) { super(page); }
    public void open() { page.navigate("/checkout"); }
    public Locator cartRows() { return page.locator("[data-test='cart-item']"); }
    public Locator quantityInput() { return page.locator("input[data-test='quantity']"); }
    public Locator checkoutButton() { return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed to checkout")); }
}
