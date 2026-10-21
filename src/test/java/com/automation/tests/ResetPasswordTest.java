package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

class ResetPasswordTest extends BaseTest {
    @Test
    void knownEmailReceivesGenericConfirmation() {
        String knownEmail = System.getenv("E2E_USER_EMAIL");
        org.junit.jupiter.api.Assumptions.assumeTrue(knownEmail != null && !knownEmail.isBlank(),
            "Set E2E_USER_EMAIL to test password reset.");
        forgotPasswordPage.open();
        forgotPasswordPage.submit(knownEmail);
        PlaywrightAssertions.assertThat(page.getByText("reset", new com.microsoft.playwright.Page.GetByTextOptions().setExact(false))).isVisible();
    }

    @Test
    void malformedEmailIsRejected() {
        forgotPasswordPage.open();
        forgotPasswordPage.submit("not-an-email");
        PlaywrightAssertions.assertThat(page.getByText("valid email", new com.microsoft.playwright.Page.GetByTextOptions().setExact(false))).isVisible();
    }
}
