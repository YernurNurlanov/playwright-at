package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(Page page) { super(page); }
    public void open() { page.navigate("/auth/forgot-password"); }
    public void submit(String email) {
        page.getByLabel("Email address").fill(email);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send")).click();
    }
    public Locator feedback() { return page.getByRole(AriaRole.ALERT); }
}
