package com.automation.tests;

import com.automation.BaseTest;
import com.automation.factory.UserFactory;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTest extends BaseTest {
    @Test
    void registersUniqueCustomer() {
        registerPage.open();
        var user = UserFactory.newUser();
        registerPage.register(user);

        // The application may sign the user in directly or redirect to the login page.
        PlaywrightAssertions.assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/(auth/login|account|$).*"));
        assertTrue(page.url().contains("/auth/login") || page.url().contains("/account") || page.url().equals(baseUrl() + "/"),
            "Expected registration to end on login, account, or home page; actual URL: " + page.url());
    }
}
