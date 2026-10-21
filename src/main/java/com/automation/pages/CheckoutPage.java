package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutPage extends BasePage {
    public CheckoutPage(Page page) { super(page); }
    public Locator heading() { return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Checkout")); }
    public Locator nextButton() { return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")); }
    public Locator confirmation() { return page.getByText("Payment was successful", new Page.GetByTextOptions().setExact(false)); }
}
