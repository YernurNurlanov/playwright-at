package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class AccountPage extends BasePage {
    public AccountPage(Page page) { super(page); }
    public Locator heading() { return page.getByRole(com.microsoft.playwright.options.AriaRole.HEADING, new Page.GetByRoleOptions().setName("My account")); }
}
