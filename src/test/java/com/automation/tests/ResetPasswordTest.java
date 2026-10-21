package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

class ResetPasswordTest extends BaseTest {
    @Test
    void knownEmailReceivesGenericConfirmation() {
        String knownEmail = "customer2@practicesoftwaretesting.com";
        forgotPasswordPage.open();
        forgotPasswordPage.submit(knownEmail);
        PlaywrightAssertions.assertThat(page.getByText("confirm", new com.microsoft.playwright.Page.GetByTextOptions().setExact(false))).isVisible();
    }

    @Test
    void malformedEmailIsRejected() {
        forgotPasswordPage.open();
        forgotPasswordPage.submit("not-an-email@gmail.com");
        PlaywrightAssertions.assertThat(page.getByText("email is invalid", new com.microsoft.playwright.Page.GetByTextOptions().setExact(false))).isVisible();
    }
}
