package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {
    @Test
    void validCredentialsReachAccountPageWhenConfigured() {
        String email = System.getenv("E2E_USER_EMAIL");
        String password = System.getenv("E2E_USER_PASSWORD");
        org.junit.jupiter.api.Assumptions.assumeTrue(email != null && password != null,
            "Set E2E_USER_EMAIL and E2E_USER_PASSWORD for the valid-login scenario.");
        loginPage.open();
        loginPage.login(email, password);
        PlaywrightAssertions.assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/account.*"));
    }

    @Test
    void invalidCredentialsShowErrorAndDoNotSignIn() {
        loginPage.open();
        loginPage.login("invalid.user." + System.currentTimeMillis() + "@example.com", "WrongPassword@123");
        PlaywrightAssertions.assertThat(page.getByText("Invalid email or password", new com.microsoft.playwright.Page.GetByTextOptions().setExact(false)))
            .isVisible();
        PlaywrightAssertions.assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/auth/login.*"));
    }
}
