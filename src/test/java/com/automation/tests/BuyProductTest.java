package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

class BuyProductTest extends BaseTest {
    @Test
    void addProductAndVerifyCartBeforeCheckout() {
        homePage.open();
        productPage.openFirstProduct();
        String name = productPage.productName().innerText().trim();
        productPage.addToCart();

        page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
            new com.microsoft.playwright.Page.GetByRoleOptions().setName("Cart")).click();
        PlaywrightAssertions.assertThat(page.getByText(name).first()).isVisible();
        PlaywrightAssertions.assertThat(page).hasURL(java.util.regex.Pattern.compile(".*(checkout|cart).*"));
    }
}
