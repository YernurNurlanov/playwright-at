package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

class LoginTest extends BaseTest {
    @Test
    void validCredentialsReachAccountPageWhenConfigured() {
        String email = "customer2@practicesoftwaretesting.com";
        String password = "welcome01";
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
