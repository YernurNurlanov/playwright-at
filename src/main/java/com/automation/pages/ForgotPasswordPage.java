package com.automation.pages;

import com.microsoft.playwright.Page;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(Page page) { super(page); }

    public void open() {
        page.navigate("/auth/forgot-password");
    }

    public void submit(String email) {
        page.getByTestId("email").fill(email);

        page.getByTestId("forgot-password-submit").click();
    }
}