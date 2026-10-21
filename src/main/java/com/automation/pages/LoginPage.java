package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {
    public LoginPage(Page page) { super(page); }
    public void open() { page.navigate("/auth/login"); }
    public void login(String email, String password) {
        page.getByLabel("Email address").fill(email);
        page.getByLabel("Password").fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }
    public void openForgotPassword() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Forgot your password?")).click();
    }
}
