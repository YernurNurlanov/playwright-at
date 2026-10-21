package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class HomePage extends BasePage {
    public HomePage(Page page) { super(page); }
    public void open() { page.navigate("/"); }
    public void openRegister() { page.getByRole(com.microsoft.playwright.options.AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click(); }
    public void openLogin() { page.getByRole(com.microsoft.playwright.options.AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click(); }
    public Locator productLinks() { return page.locator("a[data-test='product-name']"); }
    public Locator searchInput() { return page.getByPlaceholder("Search"); }
    public void search(String term) {
        searchInput().fill(term);
        Locator searchButton = page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"));
        searchButton.click();
    }
}
